package com.apps.quantitymeasurement.repository;

import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.DatabaseException;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

/**
 * QuantityMeasurementDatabaseRepository
 *
 * JDBC-based repository implementation for UC16.
 *
 * Responsibilities:
 * - Persist quantity measurement entities into relational database
 * - Retrieve historical measurement records
 * - Query records by operation and measurement type
 * - Count stored records
 * - Clear repository data when needed for tests
 *
 * Design Notes:
 * - Uses parameterized SQL to prevent SQL injection
 * - Uses ConnectionPool for centralized connection management
 * - Converts JDBC ResultSet rows into QuantityMeasurementEntity objects
 */
public class QuantityMeasurementDatabaseRepository implements IQuantityMeasurementRepository {

    private static final Logger logger = Logger.getLogger(QuantityMeasurementDatabaseRepository.class.getName());

    private static final String INSERT_SQL =
            "INSERT INTO quantity_measurement_entity " +
            "(this_value, this_unit, this_measurement_type, that_value, that_unit, that_measurement_type, operation, result_string, created_at) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_ALL_SQL =
            "SELECT * FROM quantity_measurement_entity ORDER BY created_at DESC";

    private static final String SELECT_BY_OPERATION_SQL =
            "SELECT * FROM quantity_measurement_entity WHERE operation = ? ORDER BY created_at DESC";

    private static final String SELECT_BY_TYPE_SQL =
            "SELECT * FROM quantity_measurement_entity WHERE this_measurement_type = ? ORDER BY created_at DESC";

    private static final String COUNT_SQL =
            "SELECT COUNT(*) FROM quantity_measurement_entity";

    private static final String DELETE_ALL_SQL =
            "DELETE FROM quantity_measurement_entity";

    private final ConnectionPool connectionPool;

    public QuantityMeasurementDatabaseRepository() {
        this.connectionPool = ConnectionPool.getInstance();
        initializeSchema();
    }

    /**
     * Initializes schema if table does not already exist.
     */
    private void initializeSchema() {
        String createTableSql = """
                CREATE TABLE IF NOT EXISTS quantity_measurement_entity (
                    id BIGINT AUTO_INCREMENT PRIMARY KEY,
                    this_value DOUBLE NOT NULL,
                    this_unit VARCHAR(50) NOT NULL,
                    this_measurement_type VARCHAR(50) NOT NULL,
                    that_value DOUBLE,
                    that_unit VARCHAR(50),
                    that_measurement_type VARCHAR(50),
                    operation VARCHAR(20) NOT NULL,
                    result_string VARCHAR(255),
                    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
                )
                """;

        Connection connection = connectionPool.getConnection();
        try (Statement statement = connection.createStatement()) {
            statement.execute(createTableSql);
            logger.info("Database schema initialized successfully.");
        } catch (SQLException exception) {
            throw DatabaseException.queryFailed("Schema initialization", exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement =
                     connection.prepareStatement(INSERT_SQL, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setDouble(1, entity.getThisValue());
            preparedStatement.setString(2, entity.getThisUnit());
            preparedStatement.setString(3, entity.getThisMeasurementType());

            if (entity.getThatValue() != null) {
                preparedStatement.setDouble(4, entity.getThatValue());
            } else {
                preparedStatement.setNull(4, Types.DOUBLE);
            }

            preparedStatement.setString(5, entity.getThatUnit());
            preparedStatement.setString(6, entity.getThatMeasurementType());
            preparedStatement.setString(7, entity.getOperation());
            preparedStatement.setString(8, entity.getResultString());
            preparedStatement.setTimestamp(9, new Timestamp(System.currentTimeMillis()));

            preparedStatement.executeUpdate();

            try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    entity.setId(generatedKeys.getLong(1));
                }
            }

            entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
            logger.info("Measurement entity saved successfully with operation: " + entity.getOperation());
            return entity;
        } catch (SQLException exception) {
            throw DatabaseException.queryFailed("INSERT quantity_measurement_entity", exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return executeSelectQuery(SELECT_ALL_SQL, null);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return executeSelectQuery(SELECT_BY_OPERATION_SQL, operation);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        return executeSelectQuery(SELECT_BY_TYPE_SQL, measurementType);
    }

    @Override
    public int getTotalCount() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(COUNT_SQL);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
            return 0;
        } catch (SQLException exception) {
            throw DatabaseException.queryFailed("COUNT quantity_measurement_entity", exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    @Override
    public void deleteAll() {
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ALL_SQL)) {
            preparedStatement.executeUpdate();
            logger.info("All quantity measurement records deleted successfully.");
        } catch (SQLException exception) {
            throw DatabaseException.queryFailed("DELETE ALL quantity_measurement_entity", exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    /**
     * Common query execution logic for select operations.
     *
     * @param sql SQL query
     * @param parameter optional single parameter
     * @return list of entities
     */
    private List<QuantityMeasurementEntity> executeSelectQuery(String sql, String parameter) {
        List<QuantityMeasurementEntity> entities = new ArrayList<>();
        Connection connection = connectionPool.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            if (parameter != null) {
                preparedStatement.setString(1, parameter);
            }

            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    entities.add(mapRowToEntity(resultSet));
                }
            }

            return entities;
        } catch (SQLException exception) {
            throw DatabaseException.queryFailed(sql, exception);
        } finally {
            connectionPool.releaseConnection(connection);
        }
    }

    /**
     * Maps current ResultSet row to entity object.
     *
     * @param resultSet result set row
     * @return mapped entity
     * @throws SQLException when row access fails
     */
    private QuantityMeasurementEntity mapRowToEntity(ResultSet resultSet) throws SQLException {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setId(resultSet.getLong("id"));
        entity.setThisValue(resultSet.getDouble("this_value"));
        entity.setThisUnit(resultSet.getString("this_unit"));
        entity.setThisMeasurementType(resultSet.getString("this_measurement_type"));

        double thatValue = resultSet.getDouble("that_value");
        if (!resultSet.wasNull()) {
            entity.setThatValue(thatValue);
        }

        entity.setThatUnit(resultSet.getString("that_unit"));
        entity.setThatMeasurementType(resultSet.getString("that_measurement_type"));
        entity.setOperation(resultSet.getString("operation"));
        entity.setResultString(resultSet.getString("result_string"));
        entity.setCreatedAt(resultSet.getTimestamp("created_at"));
        return entity;
    }
}
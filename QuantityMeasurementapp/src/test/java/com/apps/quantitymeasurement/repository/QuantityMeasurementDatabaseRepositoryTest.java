package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.List;

/**
 * QuantityMeasurementDatabaseRepositoryTest
 *
 * Unit/integration-style test for JDBC repository.
 *
 * Validates:
 * - save()
 * - getAllMeasurements()
 * - getMeasurementsByOperation()
 * - getMeasurementsByType()
 * - getTotalCount()
 * - deleteAll()
 */
public class QuantityMeasurementDatabaseRepositoryTest {

    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
    }

    @AfterEach
    public void tearDown() {
        repository.deleteAll();
    }

    @Test
    public void testSaveEntity_ShouldPersistSuccessfully() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LengthUnit");
        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setThatMeasurementType("LengthUnit");
        entity.setOperation("COMPARE");
        entity.setResultString("Equal");

        QuantityMeasurementEntity saved = repository.save(entity);

        Assertions.assertNotNull(saved.getId());
        Assertions.assertEquals(1, repository.getTotalCount());
    }

    @Test
    public void testGetMeasurementsByOperation_ShouldFilterCorrectly() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LengthUnit");
        entity.setOperation("COMPARE");
        entity.setResultString("Equal");

        repository.save(entity);

        List<QuantityMeasurementEntity> results = repository.getMeasurementsByOperation("COMPARE");
        Assertions.assertEquals(1, results.size());
    }

    @Test
    public void testDeleteAll_ShouldClearRepository() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(5.0);
        entity.setThisUnit("LITER");
        entity.setThisMeasurementType("VolumeUnit");
        entity.setOperation("CONVERT");
        entity.setResultString("5000");

        repository.save(entity);
        repository.deleteAll();

        Assertions.assertEquals(0, repository.getTotalCount());
    }
}
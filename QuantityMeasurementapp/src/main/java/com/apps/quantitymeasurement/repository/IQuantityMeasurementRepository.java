package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.util.List;

/**
 * IQuantityMeasurementRepository
 *
 * Abstraction for persistence operations of quantity measurements.
 *
 * Both cache-based and database-based repositories implement this interface.
 * This enables repository switching via dependency injection/configuration.
 *
 * Responsibilities:
 * - Save measurement entities
 * - Fetch all saved entities
 * - Query by operation
 * - Query by measurement type
 * - Count records
 * - Delete all records
 */
public interface IQuantityMeasurementRepository {

    QuantityMeasurementEntity save(QuantityMeasurementEntity entity);

    List<QuantityMeasurementEntity> getAllMeasurements();

    List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation);

    List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType);

    int getTotalCount();

    void deleteAll();
}
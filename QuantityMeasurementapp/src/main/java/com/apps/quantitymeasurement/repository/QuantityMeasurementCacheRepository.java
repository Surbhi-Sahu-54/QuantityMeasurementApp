package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * QuantityMeasurementCacheRepository
 *
 * In-memory repository implementation.
 *
 * Responsibilities:
 * - Store measurement entities in memory
 * - Provide lightweight persistence for non-database mode
 * - Support repository abstraction for UC16
 *
 * Important Note:
 * This repository is useful for backward compatibility and fast local testing.
 * Data is lost when the application stops.
 */
public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private final List<QuantityMeasurementEntity> cache = new ArrayList<>();
    private long idCounter = 1L;

    @Override
    public QuantityMeasurementEntity save(QuantityMeasurementEntity entity) {
        entity.setId(idCounter++);
        entity.setCreatedAt(new Timestamp(System.currentTimeMillis()));
        cache.add(entity);
        return entity;
    }

    @Override
    public List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByOperation(String operation) {
        return cache.stream()
                .filter(entity -> operation.equalsIgnoreCase(entity.getOperation()))
                .collect(Collectors.toList());
    }

    @Override
    public List<QuantityMeasurementEntity> getMeasurementsByType(String measurementType) {
        return cache.stream()
                .filter(entity -> measurementType.equalsIgnoreCase(entity.getThisMeasurementType()))
                .collect(Collectors.toList());
    }

    @Override
    public int getTotalCount() {
        return cache.size();
    }

    @Override
    public void deleteAll() {
        cache.clear();
        idCounter = 1L;
    }
}
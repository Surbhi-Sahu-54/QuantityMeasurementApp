package com.apps.quantitymeasurement.repository;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class QuantityMeasurementCacheRepository implements IQuantityMeasurementRepository {

    private static final String FILE_NAME = "quantity_measurements_cache.ser";
    private static QuantityMeasurementCacheRepository instance;

    private final List<QuantityMeasurementEntity> cache;

    private QuantityMeasurementCacheRepository() {
        this.cache = loadFromDisk();
    }

    public static synchronized QuantityMeasurementCacheRepository getInstance() {
        if (instance == null) {
            instance = new QuantityMeasurementCacheRepository();
        }
        return instance;
    }

    @Override
    public synchronized void save(QuantityMeasurementEntity entity) {
        cache.add(entity);
        saveToDisk();
    }

    @Override
    public synchronized List<QuantityMeasurementEntity> getAllMeasurements() {
        return new ArrayList<>(cache);
    }

    @Override
    public synchronized void clear() {
        cache.clear();
        saveToDisk();
    }

    private void saveToDisk() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(cache);
        } catch (IOException ignored) {
        }
    }

    @SuppressWarnings("unchecked")
    private List<QuantityMeasurementEntity> loadFromDisk() {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            return new ArrayList<>();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            return (List<QuantityMeasurementEntity>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            return new ArrayList<>();
        }
    }
}
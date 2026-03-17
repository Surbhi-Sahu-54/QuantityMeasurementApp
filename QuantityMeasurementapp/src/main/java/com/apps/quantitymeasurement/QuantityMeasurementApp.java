package com.apps.quantitymeasurement;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import com.apps.quantitymeasurement.util.ApplicationConfig;
import com.apps.quantitymeasurement.util.ConnectionPool;

import java.util.logging.Logger;

/**
 * QuantityMeasurementApp
 *
 * Main application entry point for the Quantity Measurement Application.
 *
 * Responsibilities:
 * - Bootstrap the application
 * - Load configuration
 * - Initialize correct repository implementation based on configuration
 * - Wire repository, service, and controller layers
 * - Demonstrate sample UC16 functionality
 *
 * Repository Modes:
 * - cache
 * - database
 */
public class QuantityMeasurementApp {

    private static final Logger logger = Logger.getLogger(QuantityMeasurementApp.class.getName());

    public static void main(String[] args) {
        ApplicationConfig config = ApplicationConfig.getInstance();

        IQuantityMeasurementRepository repository;
        if ("database".equalsIgnoreCase(config.getRepositoryType())) {
            repository = new QuantityMeasurementDatabaseRepository();
            logger.info("Database repository selected.");
        } else {
            repository = new QuantityMeasurementCacheRepository();
            logger.info("Cache repository selected.");
        }

        IQuantityMeasurementService service = new QuantityMeasurementServiceImpl(repository);
        QuantityMeasurementController controller = new QuantityMeasurementController(service);

        QuantityDTO quantityOne = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO quantityTwo = new QuantityDTO(12.0, "INCH", "LengthUnit");

        boolean comparisonResult = controller.performComparison(quantityOne, quantityTwo);
        logger.info("Comparison Result: " + comparisonResult);

        QuantityDTO source = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCH", "LengthUnit");

        QuantityDTO convertedResult = controller.performConversion(source, target);
        logger.info("Converted Result: " + convertedResult);

        logger.info("Total Measurements Stored: " + controller.fetchMeasurementCount());
        logger.info("Measurement History: " + controller.fetchMeasurementHistory());

        if ("database".equalsIgnoreCase(config.getRepositoryType())) {
            ConnectionPool.getInstance().shutdown();
        }
    }
}
package com.apps.quantitymeasurement.integrationtests;
import com.apps.quantitymeasurement.controller.QuantityMeasurementController;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementDatabaseRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * QuantityMeasurementIntegrationTest
 *
 * End-to-end integration test across:
 * Controller -> Service -> JDBC Repository -> Database
 */
public class QuantityMeasurementIntegrationTest {

    private QuantityMeasurementController controller;
    private QuantityMeasurementDatabaseRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new QuantityMeasurementDatabaseRepository();
        repository.deleteAll();
        controller = new QuantityMeasurementController(
                new QuantityMeasurementServiceImpl(repository)
        );
    }

    @Test
    public void testEndToEndComparison_ShouldPersistMeasurement() {
        QuantityDTO dto1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO dto2 = new QuantityDTO(12.0, "INCH", "LengthUnit");

        boolean result = controller.performComparison(dto1, dto2);

        Assertions.assertTrue(result);
        Assertions.assertEquals(1, controller.fetchMeasurementCount());
    }

    @Test
    public void testEndToEndConversion_ShouldPersistMeasurement() {
        QuantityDTO source = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCH", "LengthUnit");

        QuantityDTO result = controller.performConversion(source, target);

        Assertions.assertEquals(12.0, result.getValue(), 0.0001);
        Assertions.assertEquals(1, controller.fetchMeasurementCount());
    }
}
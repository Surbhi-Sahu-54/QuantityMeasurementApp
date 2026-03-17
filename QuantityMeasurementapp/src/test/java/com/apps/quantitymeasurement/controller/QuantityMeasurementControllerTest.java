package com.apps.quantitymeasurement.controller;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import com.apps.quantitymeasurement.service.QuantityMeasurementServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * QuantityMeasurementControllerTest
 *
 * Verifies that controller properly delegates to service layer.
 */
public class QuantityMeasurementControllerTest {

    private QuantityMeasurementController controller;

    @BeforeEach
    public void setUp() {
        controller = new QuantityMeasurementController(
                new QuantityMeasurementServiceImpl(new QuantityMeasurementCacheRepository())
        );
    }

    @Test
    public void testPerformComparison_ShouldReturnTrue() {
        QuantityDTO dto1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO dto2 = new QuantityDTO(12.0, "INCH", "LengthUnit");

        boolean result = controller.performComparison(dto1, dto2);

        Assertions.assertTrue(result);
    }

    @Test
    public void testPerformConversion_ShouldReturnConvertedDTO() {
        QuantityDTO source = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCH", "LengthUnit");

        QuantityDTO result = controller.performConversion(source, target);

        Assertions.assertEquals(12.0, result.getValue(), 0.0001);
    }
}
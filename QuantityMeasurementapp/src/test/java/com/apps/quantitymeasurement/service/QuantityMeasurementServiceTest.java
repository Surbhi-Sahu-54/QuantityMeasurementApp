package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QuantityMeasurementServiceTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;

    @BeforeEach
    void setUp() {
        repository = QuantityMeasurementCacheRepository.getInstance();
        repository.clear();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.hasError());
        assertTrue(entity.getComparisonResult());
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.hasError());
        assertTrue(entity.getComparisonResult());
    }

    @Test
    void testService_CompareEquality_CrossCategory_Error() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(1.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.compare(q1, q2);

        assertFalse(entity.hasError());
        assertFalse(entity.getComparisonResult());
    }

    @Test
    void testService_Convert_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");

        QuantityMeasurementEntity entity = service.convert(q1, "INCHES");

        assertFalse(entity.hasError());
        assertNotNull(entity.getResult());
        assertEquals("INCHES", entity.getResult().getUnit());
        assertEquals(12.0, entity.getResult().getValue(), 0.0001);
    }

    @Test
    void testService_Add_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.add(q1, q2);

        assertFalse(entity.hasError());
        assertNotNull(entity.getResult());
        assertEquals("FEET", entity.getResult().getUnit());
        assertEquals(2.0, entity.getResult().getValue(), 0.0001);
    }

    @Test
    void testService_Add_UnsupportedOperation_Error() {
        QuantityDTO q1 = new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE");
        QuantityDTO q2 = new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE");

        QuantityMeasurementEntity entity = service.add(q1, q2);

        assertTrue(entity.hasError());
        assertNotNull(entity.getErrorMessage());
    }

    @Test
    void testService_Subtract_Success() {
        QuantityDTO q1 = new QuantityDTO(10.0, "FEET", "LENGTH");
        QuantityDTO q2 = new QuantityDTO(6.0, "INCHES", "LENGTH");

        QuantityMeasurementEntity entity = service.subtract(q1, q2);

        assertFalse(entity.hasError());
        assertNotNull(entity.getResult());
    }

    @Test
    void testService_Divide_Success() {
        QuantityDTO q1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO q2 = new QuantityDTO(5.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.divide(q1, q2);

        assertFalse(entity.hasError());
        assertEquals(2.0, entity.getScalarResult(), 0.000001);
    }

    @Test
    void testService_Divide_ByZero_Error() {
        QuantityDTO q1 = new QuantityDTO(10.0, "KILOGRAM", "WEIGHT");
        QuantityDTO q2 = new QuantityDTO(0.0, "KILOGRAM", "WEIGHT");

        QuantityMeasurementEntity entity = service.divide(q1, q2);

        assertTrue(entity.hasError());
    }

    @Test
    void testService_AllMeasurementCategories() {
        assertFalse(service.compare(
                new QuantityDTO(1.0, "FEET", "LENGTH"),
                new QuantityDTO(12.0, "INCHES", "LENGTH")
        ).hasError());

        assertFalse(service.compare(
                new QuantityDTO(1.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(1000.0, "GRAM", "WEIGHT")
        ).hasError());

        assertFalse(service.compare(
                new QuantityDTO(1.0, "LITRE", "VOLUME"),
                new QuantityDTO(1000.0, "MILLILITRE", "VOLUME")
        ).hasError());

        assertFalse(service.compare(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(32.0, "FAHRENHEIT", "TEMPERATURE")
        ).hasError());
    }

    @Test
    void testService_ValidationConsistency() {
        QuantityMeasurementEntity addResult = service.add(null, null);
        QuantityMeasurementEntity divideResult = service.divide(null, null);

        assertTrue(addResult.hasError());
        assertTrue(divideResult.hasError());
    }

    @Test
    void testService_ExceptionHandling_AllOperations() {
        QuantityMeasurementEntity addError = service.add(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"),
                new QuantityDTO(5.0, "CELSIUS", "TEMPERATURE")
        );

        QuantityMeasurementEntity divideError = service.divide(
                new QuantityDTO(10.0, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(0.0, "KILOGRAM", "WEIGHT")
        );

        assertTrue(addError.hasError());
        assertTrue(divideError.hasError());
    }

    @Test
    void testService_NullEntity_Rejection() {
        QuantityMeasurementEntity entity = service.convert(null, "INCHES");
        assertTrue(entity.hasError());
    }

    @Test
    void testService_AllUnitImplementations() {
        assertFalse(service.convert(
                new QuantityDTO(1.0, "YARDS", "LENGTH"), "INCHES"
        ).hasError());

        assertFalse(service.convert(
                new QuantityDTO(1.0, "POUND", "WEIGHT"), "GRAM"
        ).hasError());

        assertFalse(service.convert(
                new QuantityDTO(1.0, "GALLON", "VOLUME"), "LITRE"
        ).hasError());

        assertFalse(service.convert(
                new QuantityDTO(0.0, "CELSIUS", "TEMPERATURE"), "KELVIN"
        ).hasError());
    }

    @Test
    void testLayerSeparation_ServiceIndependence() {
        assertNotNull(service);
    }
}
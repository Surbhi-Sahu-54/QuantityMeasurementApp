package com.apps.quantitymeasurement.service;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * QuantityMeasurementServiceTest
 *
 * Tests service layer behavior through the service interface for UC16.
 * This class validates comparison, conversion, history tracking,
 * and repository reset behavior.
 */
class QuantityMeasurementServiceTest {

    private IQuantityMeasurementRepository repository;
    private IQuantityMeasurementService service;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementCacheRepository();
        repository.deleteAll();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testService_CompareEquality_SameUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(1.0, "FEET", "LengthUnit");

        boolean result = service.compare(q1, q2);

        assertTrue(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testService_CompareEquality_DifferentUnit_Success() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(12.0, "INCH", "LengthUnit");

        boolean result = service.compare(q1, q2);

        assertTrue(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testService_CompareEquality_CrossCategory_ShouldReturnFalse() {
        QuantityDTO q1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO q2 = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");

        boolean result = service.compare(q1, q2);

        assertFalse(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testService_Convert_Length_Success() {
        QuantityDTO source = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCH", "LengthUnit");

        QuantityDTO result = service.convert(source, target);

        assertNotNull(result);
        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals("INCH", result.getUnitName());
        assertEquals("LENGTH", result.getMeasurementType());
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testService_Convert_Weight_Success() {
        QuantityDTO source = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO target = new QuantityDTO(0.0, "GRAM", "WeightUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals("GRAM", result.getUnitName());
        assertEquals("WEIGHT", result.getMeasurementType());
    }

    @Test
    void testService_Convert_Volume_Success() {
        QuantityDTO source = new QuantityDTO(1.0, "LITER", "VolumeUnit");
        QuantityDTO target = new QuantityDTO(0.0, "MILLILITER", "VolumeUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals("MILLILITER", result.getUnitName());
        assertEquals("VOLUME", result.getMeasurementType());
    }

    @Test
    void testService_Convert_Temperature_Success() {
        QuantityDTO source = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO target = new QuantityDTO(0.0, "KELVIN", "TemperatureUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(273.15, result.getValue(), 0.0001);
        assertEquals("KELVIN", result.getUnitName());
        assertEquals("TEMPERATURE", result.getMeasurementType());
    }

    @Test
    void testService_AllMeasurementCategories() {
        assertTrue(service.compare(
                new QuantityDTO(1.0, "FEET", "LengthUnit"),
                new QuantityDTO(12.0, "INCH", "LengthUnit")
        ));

        assertTrue(service.compare(
                new QuantityDTO(1.0, "KILOGRAM", "WeightUnit"),
                new QuantityDTO(1000.0, "GRAM", "WeightUnit")
        ));

        assertTrue(service.compare(
                new QuantityDTO(1.0, "LITER", "VolumeUnit"),
                new QuantityDTO(1000.0, "MILLILITER", "VolumeUnit")
        ));

        assertTrue(service.compare(
                new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit"),
                new QuantityDTO(32.0, "FAHRENHEIT", "TemperatureUnit")
        ));
    }

    @Test
    void testService_GetMeasurementHistory_ShouldStoreOperations() {
        service.compare(
                new QuantityDTO(1.0, "FEET", "LengthUnit"),
                new QuantityDTO(12.0, "INCH", "LengthUnit")
        );

        service.convert(
                new QuantityDTO(1.0, "LITER", "VolumeUnit"),
                new QuantityDTO(0.0, "MILLILITER", "VolumeUnit")
        );

        assertEquals(2, service.getMeasurementCount());
        assertEquals(2, service.getAllMeasurementHistory().size());
    }

    @Test
    void testService_DeleteAllMeasurements_ShouldClearAllRecords() {
        service.compare(
                new QuantityDTO(1.0, "FEET", "LengthUnit"),
                new QuantityDTO(12.0, "INCH", "LengthUnit")
        );

        assertEquals(1, service.getMeasurementCount());

        service.deleteAllMeasurements();

        assertEquals(0, service.getMeasurementCount());
        assertTrue(service.getAllMeasurementHistory().isEmpty());
    }

    @Test
    void testLayerSeparation_ServiceIndependence() {
        assertNotNull(service);
        assertNotNull(repository);
    }
}
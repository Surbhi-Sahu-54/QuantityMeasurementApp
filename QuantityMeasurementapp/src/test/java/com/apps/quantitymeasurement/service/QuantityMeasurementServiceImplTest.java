package com.apps.quantitymeasurement.service;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.repository.QuantityMeasurementCacheRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * QuantityMeasurementServiceImplTest
 *
 * Tests the concrete service implementation for UC16.
 * These tests validate comparison, conversion, history retrieval,
 * and repository clearing behavior.
 */
class QuantityMeasurementServiceImplTest {

    private IQuantityMeasurementRepository repository;
    private QuantityMeasurementServiceImpl service;

    @BeforeEach
    void setUp() {
        repository = new QuantityMeasurementCacheRepository();
        repository.deleteAll();
        service = new QuantityMeasurementServiceImpl(repository);
    }

    @Test
    void testCompare_SameUnit_ShouldReturnTrue() {
        QuantityDTO dto1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO dto2 = new QuantityDTO(1.0, "FEET", "LengthUnit");

        boolean result = service.compare(dto1, dto2);

        assertTrue(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testCompare_DifferentEquivalentUnits_ShouldReturnTrue() {
        QuantityDTO dto1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO dto2 = new QuantityDTO(12.0, "INCH", "LengthUnit");

        boolean result = service.compare(dto1, dto2);

        assertTrue(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testCompare_DifferentValues_ShouldReturnFalse() {
        QuantityDTO dto1 = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO dto2 = new QuantityDTO(10.0, "INCH", "LengthUnit");

        boolean result = service.compare(dto1, dto2);

        assertFalse(result);
        assertEquals(1, service.getMeasurementCount());
    }

    @Test
    void testConvert_Length_ShouldConvertFeetToInch() {
        QuantityDTO source = new QuantityDTO(1.0, "FEET", "LengthUnit");
        QuantityDTO target = new QuantityDTO(0.0, "INCH", "LengthUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(12.0, result.getValue(), 0.0001);
        assertEquals("INCH", result.getUnitName());
        assertEquals("LENGTH", result.getMeasurementType());
    }

    @Test
    void testConvert_Weight_ShouldConvertKilogramToGram() {
        QuantityDTO source = new QuantityDTO(1.0, "KILOGRAM", "WeightUnit");
        QuantityDTO target = new QuantityDTO(0.0, "GRAM", "WeightUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals("GRAM", result.getUnitName());
        assertEquals("WEIGHT", result.getMeasurementType());
    }

    @Test
    void testConvert_Volume_ShouldConvertLiterToMilliliter() {
        QuantityDTO source = new QuantityDTO(1.0, "LITER", "VolumeUnit");
        QuantityDTO target = new QuantityDTO(0.0, "MILLILITER", "VolumeUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(1000.0, result.getValue(), 0.0001);
        assertEquals("MILLILITER", result.getUnitName());
        assertEquals("VOLUME", result.getMeasurementType());
    }

    @Test
    void testConvert_Temperature_ShouldConvertCelsiusToKelvin() {
        QuantityDTO source = new QuantityDTO(0.0, "CELSIUS", "TemperatureUnit");
        QuantityDTO target = new QuantityDTO(0.0, "KELVIN", "TemperatureUnit");

        QuantityDTO result = service.convert(source, target);

        assertEquals(273.15, result.getValue(), 0.0001);
        assertEquals("KELVIN", result.getUnitName());
        assertEquals("TEMPERATURE", result.getMeasurementType());
    }

    @Test
    void testGetMeasurementHistory_ShouldReturnStoredData() {
        service.compare(
                new QuantityDTO(1.0, "FEET", "LengthUnit"),
                new QuantityDTO(12.0, "INCH", "LengthUnit")
        );

        assertEquals(1, service.getAllMeasurementHistory().size());
    }

    @Test
    void testDeleteAllMeasurements_ShouldClearRepository() {
        service.compare(
                new QuantityDTO(1.0, "FEET", "LengthUnit"),
                new QuantityDTO(12.0, "INCH", "LengthUnit")
        );

        assertEquals(1, service.getMeasurementCount());

        service.deleteAllMeasurements();

        assertEquals(0, service.getMeasurementCount());
    }
}
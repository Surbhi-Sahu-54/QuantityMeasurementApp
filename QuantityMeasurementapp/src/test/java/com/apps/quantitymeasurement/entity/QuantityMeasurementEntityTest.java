package com.apps.quantitymeasurement.entity;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

/**
 * QuantityMeasurementEntityTest
 *
 * Tests the persistence entity used in UC16.
 */
class QuantityMeasurementEntityTest {

    @Test
    void testDefaultConstructorAndSetters() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();

        Timestamp now = new Timestamp(System.currentTimeMillis());

        entity.setId(1L);
        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LENGTH");
        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setThatMeasurementType("LENGTH");
        entity.setOperation("COMPARE");
        entity.setResultString("Equal");
        entity.setCreatedAt(now);

        assertEquals(1L, entity.getId());
        assertEquals(1.0, entity.getThisValue());
        assertEquals("FEET", entity.getThisUnit());
        assertEquals("LENGTH", entity.getThisMeasurementType());
        assertEquals(12.0, entity.getThatValue());
        assertEquals("INCHES", entity.getThatUnit());
        assertEquals("LENGTH", entity.getThatMeasurementType());
        assertEquals("COMPARE", entity.getOperation());
        assertEquals("Equal", entity.getResultString());
        assertEquals(now, entity.getCreatedAt());
    }

    @Test
    void testUnaryStyleConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("CONVERT", 1.0, "FEET", "LENGTH",
                        12.0, "INCHES", "LENGTH");

        assertEquals("CONVERT", entity.getOperation());
        assertEquals(1.0, entity.getThisValue());
        assertEquals("FEET", entity.getThisUnit());
        assertEquals("LENGTH", entity.getThisMeasurementType());
        assertEquals(12.0, entity.getThatValue());
        assertEquals("INCHES", entity.getThatUnit());
        assertEquals("LENGTH", entity.getThatMeasurementType());
        assertEquals("12.0", entity.getResultString());
    }

    @Test
    void testBinaryStyleConstructor() {
        QuantityMeasurementEntity entity =
                new QuantityMeasurementEntity("ADD", 1.0, "FEET", "LENGTH",
                        12.0, "INCHES", "LENGTH", "2.0 FEET");

        assertEquals("ADD", entity.getOperation());
        assertEquals(1.0, entity.getThisValue());
        assertEquals("FEET", entity.getThisUnit());
        assertEquals("LENGTH", entity.getThisMeasurementType());
        assertEquals(12.0, entity.getThatValue());
        assertEquals("INCHES", entity.getThatUnit());
        assertEquals("LENGTH", entity.getThatMeasurementType());
        assertEquals("2.0 FEET", entity.getResultString());
    }

    @Test
    void testToStringShouldContainImportantFields() {
        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setId(10L);
        entity.setThisValue(1.0);
        entity.setThisUnit("FEET");
        entity.setThisMeasurementType("LENGTH");
        entity.setThatValue(12.0);
        entity.setThatUnit("INCHES");
        entity.setThatMeasurementType("LENGTH");
        entity.setOperation("COMPARE");
        entity.setResultString("Equal");

        String output = entity.toString();

        assertTrue(output.contains("id=10"));
        assertTrue(output.contains("thisValue=1.0"));
        assertTrue(output.contains("thisUnit='FEET'"));
        assertTrue(output.contains("operation='COMPARE'"));
        assertTrue(output.contains("resultString='Equal'"));
    }
}
package com.apps.quantitymeasurement;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class VolumeMeasurementTest {

    private final double EPSILON = 0.001;

    @Test
    void testEquality_LitreToMillilitre_EquivalentValue() {
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        assertEquals(oneLitre, thousandMl);
    }

    @Test
    void testEquality_LitreToGallon_EquivalentValue() {
        Quantity<VolumeUnit> oneGallon = new Quantity<>(1.0, VolumeUnit.GALLON);
        Quantity<VolumeUnit> litreEquiv = new Quantity<>(3.78541, VolumeUnit.LITRE);
        assertEquals(oneGallon, litreEquiv);
    }

    @Test
    void testConversion_MillilitreToGallon() {
        Quantity<VolumeUnit> ml = new Quantity<>(3785.41, VolumeUnit.MILLILITRE);
        Quantity<VolumeUnit> result = ml.convertTo(VolumeUnit.GALLON);
        assertEquals(1.0, result.getValue(), EPSILON);
    }

    @Test
    void testAddition_LitrePlusMillilitre_ResultInLitre() {
        Quantity<VolumeUnit> l1 = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> l2 = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        
        // 1L + 1000mL = 2L
        Quantity<VolumeUnit> sum = l1.add(l2, VolumeUnit.LITRE);
        assertEquals(2.0, sum.getValue(), EPSILON);
    }

    @Test
    void testCrossCategory_VolumeVsLength_ShouldNotBeEqual() {
        Quantity<VolumeUnit> volume = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<LengthUnit> length = new Quantity<>(1.0, LengthUnit.FEET);
        
        // This validates the getCategory() check in equals()
        assertNotEquals(volume, length);
    }
}
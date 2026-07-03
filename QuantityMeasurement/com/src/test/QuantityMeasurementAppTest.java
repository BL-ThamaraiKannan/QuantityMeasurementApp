package com.src.test;
import com.src.main.LengthUnit;
import com.src.main.Quantity;
import com.src.main.WeightUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testGenericQuantity_LengthEquality() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testGenericQuantity_WeightEquality() {

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        assertTrue(kilogram.equals(gram));
    }

    @Test
    public void testGenericQuantity_LengthConversion() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_WeightConversion() {

        Quantity<WeightUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.GRAM
                );

        assertEquals(
                1000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_LengthAddition() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                12.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testGenericQuantity_WeightAddition() {

        Quantity<WeightUnit> result =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new Quantity<>(
                                1000.0,
                                WeightUnit.GRAM
                        ),
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testCrossCategoryPrevention() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertFalse(feet.equals(kilogram));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidation_NullUnit() {

        new Quantity<>(
                1.0,
                null
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorValidation_InvalidValue() {

        new Quantity<>(
                Double.NaN,
                LengthUnit.FEET
        );
    }

    @Test
    public void testHashCodeConsistency() {

        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        assertEquals(
                feet.hashCode(),
                inches.hashCode()
        );
    }
}
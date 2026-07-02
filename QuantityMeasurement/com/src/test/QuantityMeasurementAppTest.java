package com.src.test;

import com.src.main.LengthUnit;
import com.src.main.QuantityLength;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testLengthUnitEnum_FeetConstant() {

        assertEquals(
                1.0,
                LengthUnit.FEET.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testLengthUnitEnum_InchesConstant() {

        assertEquals(
                1.0 / 12.0,
                LengthUnit.INCHES.getConversionFactor(),
                EPSILON
        );
    }

    @Test
    public void testConvertToBaseUnit_InchesToFeet() {

        assertEquals(
                1.0,
                LengthUnit.INCHES
                        .convertToBaseUnit(12.0),
                EPSILON
        );
    }

    @Test
    public void testConvertFromBaseUnit_FeetToInches() {

        assertEquals(
                12.0,
                LengthUnit.INCHES
                        .convertFromBaseUnit(1.0),
                EPSILON
        );
    }

    @Test
    public void testQuantityLengthRefactored_Equality() {

        QuantityLength feet =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityLength inches =
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testQuantityLengthRefactored_ConvertTo() {

        QuantityLength feet =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        QuantityLength result =
                feet.convertTo(
                        LengthUnit.INCHES
                );

        assertEquals(
                12.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testQuantityLengthRefactored_Add() {

        QuantityLength result =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new QuantityLength(
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
    public void testQuantityLengthRefactored_AddWithTargetUnit() {

        QuantityLength result =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new QuantityLength(
                                12.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.YARDS
                );

        assertEquals(
                0.6667,
                result.getValue(),
                EPSILON
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLengthRefactored_NullUnit() {

        new QuantityLength(
                1.0,
                null
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testQuantityLengthRefactored_InvalidValue() {

        new QuantityLength(
                Double.NaN,
                LengthUnit.FEET
        );
    }

    @Test
    public void testRoundTripConversion_RefactoredDesign() {

        QuantityLength original =
                new QuantityLength(
                        5.0,
                        LengthUnit.FEET
                );

        QuantityLength converted =
                original.convertTo(
                        LengthUnit.INCHES
                );

        QuantityLength result =
                converted.convertTo(
                        LengthUnit.FEET
                );

        assertEquals(
                original.getValue(),
                result.getValue(),
                EPSILON
        );
    }
}
package com.src.test;


import com.src.main.QuantityMeasurementApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testConversion_FeetToInches() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(12.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToFeet() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        24.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_YardsToInches() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(36.0, result, EPSILON);
    }

    @Test
    public void testConversion_InchesToYards() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        72.0,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_CentimetersToInches() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(1.0, result, EPSILON);
    }

    @Test
    public void testConversion_FeetToYard() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(2.0, result, EPSILON);
    }

    @Test
    public void testConversion_RoundTrip_PreservesValue() {

        double original = 5.0;

        double converted =
                QuantityMeasurementApp.QuantityLength.convert(
                        original,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        converted,
                        QuantityMeasurementApp.LengthUnit.INCHES,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(original, result, EPSILON);
    }

    @Test
    public void testConversion_ZeroValue() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        0.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(0.0, result, EPSILON);
    }

    @Test
    public void testConversion_NegativeValue() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        -1.0,
                        QuantityMeasurementApp.LengthUnit.FEET,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(-12.0, result, EPSILON);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_InvalidUnit_Throws() {

        QuantityMeasurementApp.QuantityLength.convert(
                1.0,
                null,
                QuantityMeasurementApp.LengthUnit.FEET
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConversion_NaNOrInfinite_Throws() {

        QuantityMeasurementApp.QuantityLength.convert(
                Double.NaN,
                QuantityMeasurementApp.LengthUnit.FEET,
                QuantityMeasurementApp.LengthUnit.INCHES
        );
    }

    @Test
    public void testConversion_PrecisionTolerance() {

        double result =
                QuantityMeasurementApp.QuantityLength.convert(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(0.393701, result, EPSILON);
    }
}
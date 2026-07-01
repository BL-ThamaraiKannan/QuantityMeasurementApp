package com.src.test;


import com.src.main.QuantityMeasurementApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_FeetToFeet_SameValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_SameValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_FeetToInch_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        assertTrue(feet.equals(inches));
    }

    @Test
    public void testEquality_InchToFeet_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertTrue(inches.equals(feet));
    }

    @Test
    public void testEquality_FeetToFeet_DifferentValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_InchToInch_DifferentValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.INCH
                );

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_NullComparison() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertFalse(q1.equals(null));
    }

    @Test
    public void testEquality_SameReference() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertTrue(q1.equals(q1));
    }

    @Test
    public void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1.0, null)
        );
    }
}
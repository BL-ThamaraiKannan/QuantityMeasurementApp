package com.src.test;


import com.src.main.QuantityMeasurementApp;

import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    @Test
    public void testEquality_YardToYard_SameValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertTrue(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToYard_DifferentValue() {

        QuantityMeasurementApp.QuantityLength q1 =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength q2 =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertFalse(q1.equals(q2));
    }

    @Test
    public void testEquality_YardToFeet_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertTrue(yard.equals(feet));
    }

    @Test
    public void testEquality_FeetToYard_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertTrue(feet.equals(yard));
    }

    @Test
    public void testEquality_YardToInches_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        36.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_CentimetersToInches_EquivalentValue() {

        QuantityMeasurementApp.QuantityLength cm =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        QuantityMeasurementApp.QuantityLength inch =
                new QuantityMeasurementApp.QuantityLength(
                        0.393701,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertTrue(cm.equals(inch));
    }

    @Test
    public void testEquality_CentimetersToFeet_NonEquivalentValue() {

        QuantityMeasurementApp.QuantityLength cm =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertFalse(cm.equals(feet));
    }

    @Test
    public void testEquality_MultiUnit_TransitiveProperty() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        3.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        36.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertTrue(yard.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yard.equals(inches));
    }

    @Test
    public void testEquality_NullUnit() {

        assertThrows(
                IllegalArgumentException.class,
                () -> new QuantityMeasurementApp.QuantityLength(1.0,null)
        );
    }

    @Test
    public void testEquality_SameReference() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertTrue(yard.equals(yard));
    }

    @Test
    public void testEquality_NullComparison() {

        QuantityMeasurementApp.QuantityLength yard =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertFalse(yard.equals(null));
    }

    @Test
    public void testEquality_AllUnits_ComplexScenario() {

        QuantityMeasurementApp.QuantityLength yards =
                new QuantityMeasurementApp.QuantityLength(
                        2.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength feet =
                new QuantityMeasurementApp.QuantityLength(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        QuantityMeasurementApp.QuantityLength inches =
                new QuantityMeasurementApp.QuantityLength(
                        72.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertTrue(yards.equals(feet));
        assertTrue(feet.equals(inches));
        assertTrue(yards.equals(inches));
    }
}
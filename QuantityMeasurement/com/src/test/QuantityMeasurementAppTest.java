package com.src.test;


import com.src.main.QuantityMeasurementApp;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuantityMeasurementAppTest {

    // Feet Test Cases
    @Test
    public void testFeetEquality_SameValue() {

        assertTrue(
                QuantityMeasurementApp.compareFeet(1.0, 1.0)
        );
    }

    @Test
    public void testFeetEquality_DifferentValue() {

        assertFalse(
                QuantityMeasurementApp.compareFeet(1.0, 2.0)
        );
    }

    @Test
    public void testFeetEquality_NullComparison() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null));
    }

    @Test
    public void testFeetEquality_NonNumericInput() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        String value = "ABC";

        assertFalse(feet.equals(value));
    }

    @Test
    public void testFeetEquality_SameReference() {

        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet));
    }

    // Inches Test Cases

    @Test
    public void testInchesEquality_SameValue() {

        assertTrue(
                QuantityMeasurementApp.compareInches(1.0, 1.0)
        );
    }

    @Test
    public void testInchesEquality_DifferentValue() {

        assertFalse(
                QuantityMeasurementApp.compareInches(1.0, 2.0)
        );
    }

    @Test
    public void testInchesEquality_NullComparison() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertFalse(inches.equals(null));
    }

    @Test
    public void testInchesEquality_NonNumericInput() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        String value = "ABC";

        assertFalse(inches.equals(value));
    }

    @Test
    public void testInchesEquality_SameReference() {

        QuantityMeasurementApp.Inches inches =
                new QuantityMeasurementApp.Inches(1.0);

        assertTrue(inches.equals(inches));
    }
}
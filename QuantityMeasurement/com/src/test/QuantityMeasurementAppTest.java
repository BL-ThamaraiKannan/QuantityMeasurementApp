package com.src.test;


import com.src.main.QuantityMeasurementApp;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class QuantityMeasurementAppTest {


    @Test
   public void testEquality_SameValue() {
        QuantityMeasurementApp.Feet feet1 =
                new QuantityMeasurementApp.Feet(1.0);

        QuantityMeasurementApp.Feet feet2 =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet1.equals(feet2));
    }

    @Test
    public void testEquality_DifferentValue() {
        QuantityMeasurementApp.Feet feet1 =
                new QuantityMeasurementApp.Feet(1.0);

        QuantityMeasurementApp.Feet feet2 =
                new QuantityMeasurementApp.Feet(2.0);

        assertFalse(feet1.equals(feet2));
    }

    @Test
    public void testEquality_NullComparison() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertFalse(feet.equals(null));
    }

    @Test
    public void testEquality_NonNumericInput() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        String value = "ABC";

        assertFalse(feet.equals(value));
    }

    @Test
    public void testEquality_SameReference() {
        QuantityMeasurementApp.Feet feet =
                new QuantityMeasurementApp.Feet(1.0);

        assertTrue(feet.equals(feet));
    }
}
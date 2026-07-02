package com.src.test;
import com.src.main.QuantityMeasurementApp;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testAddition_ExplicitTargetUnit_Feet() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.FEET
                );

        assertEquals(
                2.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Inches() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                24.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Yards() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.6667,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Centimeters() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                );

        assertEquals(
                5.08,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Commutativity() {

        QuantityMeasurementApp.QuantityLength result1 =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        QuantityMeasurementApp.QuantityLength result2 =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertTrue(result1.equals(result2));
    }

    @Test
    public void testAddition_ExplicitTargetUnit_WithZero() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                5.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                0.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                1.6667,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_NegativeValues() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                5.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                -2.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                36.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).value,
                EPSILON
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_ExplicitTargetUnit_NullTargetUnit() {

        QuantityMeasurementApp.QuantityLength.add(
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ),
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ),
                null
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_LargeToSmallScale() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                1000.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                500.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        ),
                        QuantityMeasurementApp.LengthUnit.INCHES
                );

        assertEquals(
                18000.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_SmallToLargeScale() {

        QuantityMeasurementApp.QuantityLength result =
                QuantityMeasurementApp.QuantityLength.add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        ),
                        QuantityMeasurementApp.LengthUnit.YARDS
                );

        assertEquals(
                0.6667,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).value,
                EPSILON
        );
    }
}
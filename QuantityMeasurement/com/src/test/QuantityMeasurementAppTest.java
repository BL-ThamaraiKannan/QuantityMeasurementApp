package com.src.test;

import com.src.main.QuantityMeasurementApp;
import org.junit.Test;
import static org.junit.Assert.*;


public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testAddition_SameUnit_FeetPlusFeet() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                2.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
                );

        assertEquals(
                3.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_SameUnit_InchPlusInch() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        6.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                6.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        )
                );

        assertEquals(
                12.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_FeetPlusInches() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                12.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        )
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
    public void testAddition_CrossUnit_InchPlusFeet() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        12.0,
                        QuantityMeasurementApp.LengthUnit.INCHES
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
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
    public void testAddition_CrossUnit_YardPlusFeet() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1.0,
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                3.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
                );

        assertEquals(
                2.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.YARDS
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_CentimeterPlusInch() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        2.54,
                        QuantityMeasurementApp.LengthUnit.CENTIMETERS
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                1.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        )
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
    public void testAddition_WithZero() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                0.0,
                                QuantityMeasurementApp.LengthUnit.INCHES
                        )
                );

        assertEquals(
                5.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_NegativeValues() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        5.0,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                -2.0,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
                );

        assertEquals(
                3.0,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddition_NullSecondOperand() {

        new QuantityMeasurementApp.QuantityLength(
                1.0,
                QuantityMeasurementApp.LengthUnit.FEET
        ).add(null);
    }

    @Test
    public void testAddition_LargeValues() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        1e6,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                1e6,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
                );

        assertEquals(
                2e6,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }

    @Test
    public void testAddition_SmallValues() {

        QuantityMeasurementApp.QuantityLength result =
                new QuantityMeasurementApp.QuantityLength(
                        0.001,
                        QuantityMeasurementApp.LengthUnit.FEET
                ).add(
                        new QuantityMeasurementApp.QuantityLength(
                                0.002,
                                QuantityMeasurementApp.LengthUnit.FEET
                        )
                );

        assertEquals(
                0.003,
                result.convertTo(
                        QuantityMeasurementApp.LengthUnit.FEET
                ).value,
                EPSILON
        );
    }
}
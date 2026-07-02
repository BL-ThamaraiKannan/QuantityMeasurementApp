package com.src.test;

import com.src.main.QuantityWeight;
import com.src.main.WeightUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityMeasurementAppTest {

    private static final double EPSILON = 0.0001;

    @Test
    public void testEquality_KilogramToKilogram_SameValue() {

        assertTrue(
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).equals(
                        new QuantityWeight(
                                1.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }

    @Test
    public void testEquality_KilogramToGram_EquivalentValue() {

        assertTrue(
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).equals(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.GRAM
                        )
                )
        );
    }

    @Test
    public void testEquality_GramToKilogram_EquivalentValue() {

        assertTrue(
                new QuantityWeight(
                        1000.0,
                        WeightUnit.GRAM
                ).equals(
                        new QuantityWeight(
                                1.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }

    @Test
    public void testEquality_NullComparison() {

        assertFalse(
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).equals(null)
        );
    }

    @Test
    public void testEquality_SameReference() {

        QuantityWeight weight =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        assertTrue(weight.equals(weight));
    }

    @Test
    public void testConversion_KilogramToPound() {

        QuantityWeight result =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.POUND
                );

        assertEquals(
                2.20462,
                result.getValue(),
                0.01
        );
    }

    @Test
    public void testConversion_PoundToKilogram() {

        QuantityWeight result =
                new QuantityWeight(
                        2.20462,
                        WeightUnit.POUND
                ).convertTo(
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                1.0,
                result.getValue(),
                0.01
        );
    }

    @Test
    public void testConversion_RoundTrip() {

        QuantityWeight original =
                new QuantityWeight(
                        1.5,
                        WeightUnit.KILOGRAM
                );

        QuantityWeight result =
                original.convertTo(
                        WeightUnit.GRAM
                ).convertTo(
                        WeightUnit.KILOGRAM
                );

        assertEquals(
                original.getValue(),
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_SameUnit_KilogramPlusKilogram() {

        QuantityWeight result =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                2.0,
                                WeightUnit.KILOGRAM
                        )
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_KilogramPlusGram() {

        QuantityWeight result =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.GRAM
                        )
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_CrossUnit_PoundPlusKilogram() {

        QuantityWeight result =
                new QuantityWeight(
                        2.20462,
                        WeightUnit.POUND
                ).add(
                        new QuantityWeight(
                                1.0,
                                WeightUnit.KILOGRAM
                        )
                );

        assertEquals(
                4.40924,
                result.getValue(),
                0.01
        );
    }

    @Test
    public void testAddition_ExplicitTargetUnit_Kilogram() {

        QuantityWeight result =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.GRAM
                        ),
                        WeightUnit.GRAM
                );

        assertEquals(
                2000.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_WithZero() {

        QuantityWeight result =
                new QuantityWeight(
                        5.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                0.0,
                                WeightUnit.GRAM
                        )
                );

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testAddition_NegativeValues() {

        QuantityWeight result =
                new QuantityWeight(
                        5.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                -2000.0,
                                WeightUnit.GRAM
                        )
                );

        assertEquals(
                3.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testEquality_NullUnit() {

        new QuantityWeight(
                1.0,
                null
        );
    }

    @Test
    public void testEquality_LargeWeightValue() {

        assertTrue(
                new QuantityWeight(
                        1000000.0,
                        WeightUnit.GRAM
                ).equals(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.KILOGRAM
                        )
                )
        );
    }
}
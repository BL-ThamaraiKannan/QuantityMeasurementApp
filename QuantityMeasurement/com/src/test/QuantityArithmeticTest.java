package com.src.test;

import com.src.main.LengthUnit;
import com.src.main.Quantity;
import com.src.main.VolumeUnit;
import com.src.main.WeightUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityArithmeticTest {

    private static final double EPSILON = 0.0001;

    // ---------------- SUBTRACTION ----------------

    @Test
    public void testSubtraction_SameUnit() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                5.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_CrossUnit() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                6.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                9.5,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ExplicitTargetUnit() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                6.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.INCHES
                );

        assertEquals(
                114.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ResultingInNegative() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                10.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                -5.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_ResultingInZero() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                120.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                0.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSubtraction_NullOperand() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).subtract(null);
    }

    // ---------------- DIVISION ----------------

    @Test
    public void testDivision_SameUnit() {

        double result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).divide(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                5.0,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_CrossUnit() {

        double result =
                new Quantity<>(
                        24.0,
                        LengthUnit.INCHES
                ).divide(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                1.0,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_RatioGreaterThanOne() {

        double result =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ).divide(
                        new Quantity<>(
                                5.0,
                                WeightUnit.KILOGRAM
                        )
                );

        assertEquals(
                2.0,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_RatioLessThanOne() {

        double result =
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ).divide(
                        new Quantity<>(
                                10.0,
                                VolumeUnit.LITRE
                        )
                );

        assertEquals(
                0.5,
                result,
                EPSILON
        );
    }

    @Test
    public void testDivision_RatioEqualToOne() {

        double result =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                ).divide(
                        new Quantity<>(
                                1.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                1.0,
                result,
                EPSILON
        );
    }

    @Test(expected = ArithmeticException.class)
    public void testDivision_ByZero() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).divide(
                new Quantity<>(
                        0.0,
                        LengthUnit.FEET
                )
        );
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDivision_NullOperand() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).divide(null);
    }

    @Test
    public void testSubtraction_Immutability() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        original.subtract(
                new Quantity<>(
                        5.0,
                        LengthUnit.FEET
                )
        );

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }

    @Test
    public void testDivision_Immutability() {

        Quantity<WeightUnit> original =
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                );

        original.divide(
                new Quantity<>(
                        5.0,
                        WeightUnit.KILOGRAM
                )
        );

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }
}
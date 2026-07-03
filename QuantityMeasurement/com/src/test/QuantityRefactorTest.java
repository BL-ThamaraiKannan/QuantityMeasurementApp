package com.src.test;
import com.src.main.LengthUnit;
import com.src.main.Quantity;
import com.src.main.WeightUnit;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuantityRefactorTest {

    private static final double EPSILON = 0.0001;

    // ---------------- ENUM OPERATION TESTS ----------------

    @Test
    public void testAddition_BehaviorPreserved() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                12.0,
                                LengthUnit.INCHES
                        )
                );

        assertEquals(
                2.0,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testSubtraction_BehaviorPreserved() {

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
    public void testDivision_BehaviorPreserved() {

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

    // ---------------- VALIDATION CONSISTENCY ----------------

    @Test(expected = IllegalArgumentException.class)
    public void testValidation_NullOperand_Add() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidation_NullOperand_Subtract() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).subtract(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidation_NullOperand_Divide() {

        new Quantity<>(
                10.0,
                LengthUnit.FEET
        ).divide(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidation_CrossCategory() {

        Quantity<LengthUnit> length =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        Quantity weight =
                new Quantity<>(
                        5.0,
                        WeightUnit.KILOGRAM
                );

        length.add(weight);
    }

    // ---------------- DIVISION BY ZERO ----------------

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

    // ---------------- ROUNDING ----------------

    @Test
    public void testRounding_Addition() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        1.111,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                1.111,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                2.22,
                result.getValue(),
                EPSILON
        );
    }

    @Test
    public void testRounding_Subtraction() {

        Quantity<LengthUnit> result =
                new Quantity<>(
                        5.555,
                        LengthUnit.FEET
                ).subtract(
                        new Quantity<>(
                                1.111,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                4.44,
                result.getValue(),
                EPSILON
        );
    }

    // ---------------- IMMUTABILITY ----------------

    @Test
    public void testImmutability_Addition() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        original.add(
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
    public void testImmutability_Subtraction() {

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
    public void testImmutability_Division() {

        Quantity<LengthUnit> original =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                );

        original.divide(
                new Quantity<>(
                        2.0,
                        LengthUnit.FEET
                )
        );

        assertEquals(
                10.0,
                original.getValue(),
                EPSILON
        );
    }

    // ---------------- CHAIN OPERATIONS ----------------

    @Test
    public void testChainOperations() {

        double result =
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ).add(
                        new Quantity<>(
                                2.0,
                                LengthUnit.FEET
                        )
                ).subtract(
                        new Quantity<>(
                                1.0,
                                LengthUnit.FEET
                        )
                ).divide(
                        new Quantity<>(
                                11.0,
                                LengthUnit.FEET
                        )
                );

        assertEquals(
                1.0,
                result,
                EPSILON
        );
    }
}

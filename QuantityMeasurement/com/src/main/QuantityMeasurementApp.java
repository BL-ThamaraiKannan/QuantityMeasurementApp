package com.src.main;
public class QuantityMeasurementApp {

    // Subtraction Demo
    public static <U extends IMeasurable>
    void demonstrateSubtraction(
            Quantity<U> first,
            Quantity<U> second,
            U targetUnit
    ) {

        System.out.println(
                first + ".subtract(" +
                        second + ", " +
                        targetUnit + ") => " +
                        first.subtract(
                                second,
                                targetUnit
                        )
        );

        System.out.println();
    }

    // Division Demo
    public static <U extends IMeasurable>
    void demonstrateDivision(
            Quantity<U> first,
            Quantity<U> second
    ) {

        System.out.println(
                first + ".divide(" +
                        second + ") => " +
                        first.divide(second)
        );

        System.out.println();
    }

    public static void main(String[] args) {

        // Length Subtraction
        demonstrateSubtraction(
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ),
                new Quantity<>(
                        6.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.FEET
        );

        // Weight Subtraction
        demonstrateSubtraction(
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ),
                new Quantity<>(
                        5000.0,
                        WeightUnit.GRAM
                ),
                WeightUnit.KILOGRAM
        );

        // Volume Subtraction
        demonstrateSubtraction(
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ),
                new Quantity<>(
                        500.0,
                        VolumeUnit.MILLILITRE
                ),
                VolumeUnit.LITRE
        );

        // Division Operations
        demonstrateDivision(
                new Quantity<>(
                        10.0,
                        LengthUnit.FEET
                ),
                new Quantity<>(
                        2.0,
                        LengthUnit.FEET
                )
        );

        demonstrateDivision(
                new Quantity<>(
                        24.0,
                        LengthUnit.INCHES
                ),
                new Quantity<>(
                        2.0,
                        LengthUnit.FEET
                )
        );

        demonstrateDivision(
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ),
                new Quantity<>(
                        5.0,
                        WeightUnit.KILOGRAM
                )
        );
    }
}
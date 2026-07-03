package com.src.main;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable>
    void demonstrateAddition(
            Quantity<U> first,
            Quantity<U> second,
            U targetUnit
    ) {

        System.out.println(
                first + ".add(" +
                        second + ", " +
                        targetUnit + ") => " +
                        first.add(
                                second,
                                targetUnit
                        )
        );

        System.out.println();
    }

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

        // Addition
        demonstrateAddition(
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                ),
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.FEET
        );

        // Subtraction
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

        // Division
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

        // Weight Example
        demonstrateAddition(
                new Quantity<>(
                        10.0,
                        WeightUnit.KILOGRAM
                ),
                new Quantity<>(
                        5000.0,
                        WeightUnit.GRAM
                ),
                WeightUnit.GRAM
        );

        // Volume Example
        demonstrateSubtraction(
                new Quantity<>(
                        5.0,
                        VolumeUnit.LITRE
                ),
                new Quantity<>(
                        2.0,
                        VolumeUnit.LITRE
                ),
                VolumeUnit.MILLILITRE
        );
    }
}
package com.src.main;

public class QuantityMeasurementApp {

    // Generic Equality Demonstration
    public static <U extends IMeasurable>
    void demonstrateEquality(
            Quantity<U> first,
            Quantity<U> second
    ) {

        System.out.println(
                first + ".equals(" +
                        second + ") => " +
                        first.equals(second)
        );

        System.out.println();
    }

    // Generic Conversion Demonstration
    public static <U extends IMeasurable>
    void demonstrateConversion(
            Quantity<U> quantity,
            U targetUnit
    ) {

        System.out.println(
                quantity + ".convertTo(" +
                        targetUnit + ") => " +
                        quantity.convertTo(targetUnit)
        );

        System.out.println();
    }

    // Generic Addition Demonstration
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
                        first.add(second, targetUnit)
        );

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        // Length
        Quantity<LengthUnit> feet =
                new Quantity<>(
                        1.0,
                        LengthUnit.FEET
                );

        Quantity<LengthUnit> inches =
                new Quantity<>(
                        12.0,
                        LengthUnit.INCHES
                );

        demonstrateEquality(feet, inches);

        demonstrateConversion(
                feet,
                LengthUnit.INCHES
        );

        demonstrateAddition(
                feet,
                inches,
                LengthUnit.FEET
        );

        // Weight
        Quantity<WeightUnit> kilogram =
                new Quantity<>(
                        1.0,
                        WeightUnit.KILOGRAM
                );

        Quantity<WeightUnit> gram =
                new Quantity<>(
                        1000.0,
                        WeightUnit.GRAM
                );

        demonstrateEquality(
                kilogram,
                gram
        );

        demonstrateConversion(
                kilogram,
                WeightUnit.GRAM
        );

        demonstrateAddition(
                kilogram,
                gram,
                WeightUnit.KILOGRAM
        );
    }
}
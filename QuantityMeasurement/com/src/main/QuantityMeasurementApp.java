package com.src.main;

public class QuantityMeasurementApp {

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

    public static void main(String[] args) {

        // Volume Quantities
        Quantity<VolumeUnit> litre =
                new Quantity<>(
                        1.0,
                        VolumeUnit.LITRE
                );

        Quantity<VolumeUnit> millilitre =
                new Quantity<>(
                        1000.0,
                        VolumeUnit.MILLILITRE
                );

        Quantity<VolumeUnit> gallon =
                new Quantity<>(
                        1.0,
                        VolumeUnit.GALLON
                );

        // Equality
        demonstrateEquality(
                litre,
                millilitre
        );

        demonstrateEquality(
                gallon,
                new Quantity<>(
                        3.78541,
                        VolumeUnit.LITRE
                )
        );

        // Conversion
        demonstrateConversion(
                litre,
                VolumeUnit.MILLILITRE
        );

        demonstrateConversion(
                gallon,
                VolumeUnit.LITRE
        );

        // Addition
        demonstrateAddition(
                litre,
                millilitre,
                VolumeUnit.LITRE
        );

        demonstrateAddition(
                gallon,
                litre,
                VolumeUnit.GALLON
        );
    }
}
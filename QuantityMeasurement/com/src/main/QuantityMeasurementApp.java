package com.src.main;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Equality
        System.out.println(
                "Input: Quantity(1.0, KILOGRAM).equals(Quantity(1000.0, GRAM))"
        );

        System.out.println(
                "Output: " +
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

        System.out.println();

        // Conversion
        QuantityWeight converted =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).convertTo(
                        WeightUnit.GRAM
                );

        System.out.println(
                "Input: Quantity(1.0, KILOGRAM).convertTo(GRAM)"
        );

        System.out.println(
                "Output: " + converted
        );

        System.out.println();

        // Addition
        QuantityWeight addition =
                new QuantityWeight(
                        1.0,
                        WeightUnit.KILOGRAM
                ).add(
                        new QuantityWeight(
                                1000.0,
                                WeightUnit.GRAM
                        )
                );

        System.out.println(
                "Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM))"
        );

        System.out.println(
                "Output: " + addition
        );

        System.out.println();

        // Addition with target unit
        QuantityWeight targetAddition =
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

        System.out.println(
                "Input: Quantity(1.0, KILOGRAM).add(Quantity(1000.0, GRAM), GRAM)"
        );

        System.out.println(
                "Output: " + targetAddition
        );
    }
}
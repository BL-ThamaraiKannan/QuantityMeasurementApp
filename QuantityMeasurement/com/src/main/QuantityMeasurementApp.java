package com.src.main;
public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Conversion
        QuantityLength feet =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                );

        System.out.println(
                "Input: Quantity(1.0, FEET).convertTo(INCHES)"
        );

        System.out.println(
                "Output: " +
                        feet.convertTo(
                                LengthUnit.INCHES
                        )
        );

        System.out.println();

        // Addition
        QuantityLength result =
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ).add(
                        new QuantityLength(
                                12.0,
                                LengthUnit.INCHES
                        ),
                        LengthUnit.FEET
                );

        System.out.println(
                "Input: Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)"
        );

        System.out.println(
                "Output: " + result
        );

        System.out.println();

        // Equality
        boolean equality =
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                ).equals(
                        new QuantityLength(
                                1.0,
                                LengthUnit.YARDS
                        )
                );

        System.out.println(
                "Input: Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))"
        );

        System.out.println(
                "Output: " + equality
        );

        System.out.println();

        // Base Unit Conversion
        System.out.println(
                "Input: LengthUnit.INCHES.convertToBaseUnit(12.0)"
        );

        System.out.println(
                "Output: " +
                        LengthUnit.INCHES
                                .convertToBaseUnit(12.0)
        );
    }
}
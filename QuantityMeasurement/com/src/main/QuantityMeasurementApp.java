package com.src.main;

public class QuantityMeasurementApp {

    // Enum for Length Units
    public enum LengthUnit {

        FEET(1.0),
        INCHES(1.0 / 12.0),
        YARDS(3.0),
        CENTIMETERS(0.0328084);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    // Generic Quantity Length Class
    public static class QuantityLength {

        private static final double EPSILON = 0.0001;

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value, LengthUnit unit) {

            validateValue(value);

            if (unit == null) {
                throw new IllegalArgumentException("Unit cannot be null");
            }

            this.value = value;
            this.unit = unit;
        }

        // Convert value to base unit (Feet)
        public double toFeet() {
            return value * unit.getConversionFactor();
        }

        // Convert to target unit
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            double valueInFeet = toFeet();

            double convertedValue =
                    valueInFeet / targetUnit.getConversionFactor();

            return new QuantityLength(
                    convertedValue,
                    targetUnit
            );
        }

        // Static conversion API
        public static double convert(
                double value,
                LengthUnit sourceUnit,
                LengthUnit targetUnit
        ) {

            validateValue(value);

            if (sourceUnit == null || targetUnit == null) {
                throw new IllegalArgumentException(
                        "Units cannot be null"
                );
            }

            double valueInFeet =
                    value * sourceUnit.getConversionFactor();

            return valueInFeet / targetUnit.getConversionFactor();
        }

        // Validation
        private static void validateValue(double value) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite"
                );
            }
        }

        // Equality
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            return Math.abs(
                    this.toFeet() - other.toFeet()
            ) < EPSILON;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Method Overloading Example 1
    public static void demonstrateLengthConversion(
            double value,
            LengthUnit fromUnit,
            LengthUnit toUnit
    ) {

        double result =
                QuantityLength.convert(
                        value,
                        fromUnit,
                        toUnit
                );

        System.out.println(
                "Input: convert(" +
                        value + ", " +
                        fromUnit + ", " +
                        toUnit + ")"
        );

        System.out.println(
                "Output: " + result
        );

        System.out.println();
    }

    // Method Overloading Example 2
    public static void demonstrateLengthConversion(
            QuantityLength quantity,
            LengthUnit targetUnit
    ) {

        QuantityLength converted =
                quantity.convertTo(targetUnit);

        System.out.println(
                quantity + " converted to " +
                        targetUnit + " = " +
                        converted
        );

        System.out.println();
    }

    // Equality Demonstration
    public static void demonstrateLengthEquality(
            QuantityLength q1,
            QuantityLength q2
    ) {

        System.out.println(
                q1 + " equals " + q2 +
                        " => " + q1.equals(q2)
        );

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        demonstrateLengthConversion(
                1.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                3.0,
                LengthUnit.YARDS,
                LengthUnit.FEET
        );

        demonstrateLengthConversion(
                36.0,
                LengthUnit.INCHES,
                LengthUnit.YARDS
        );

        demonstrateLengthConversion(
                1.0,
                LengthUnit.CENTIMETERS,
                LengthUnit.INCHES
        );

        demonstrateLengthConversion(
                0.0,
                LengthUnit.FEET,
                LengthUnit.INCHES
        );

        QuantityLength yard =
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                );

        QuantityLength feet =
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET
                );

        demonstrateLengthEquality(yard, feet);
    }
}
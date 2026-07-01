package com.src.main;

public class QuantityMeasurementApp {

    // Enum for supported units
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

        private final double value;
        private final LengthUnit unit;

        // Constructor
        public QuantityLength(double value, LengthUnit unit) {

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

        // equals() method
        @Override
        public boolean equals(Object obj) {

            // Same reference check
            if (this == obj) {
                return true;
            }

            // Null and type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other = (QuantityLength) obj;

            // Tolerance for floating point precision
            double EPSILON = 0.0001;

            return Math.abs(this.toFeet() - other.toFeet()) < EPSILON;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Main Method
    public static void main(String[] args) {

        QuantityLength yard =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength feet =
                new QuantityLength(3.0, LengthUnit.FEET);

        System.out.println(
                "Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)"
        );

        System.out.println(
                "Output: Equal (" + yard.equals(feet) + ")"
        );

        System.out.println();

        QuantityLength yardToInch =
                new QuantityLength(1.0, LengthUnit.YARDS);

        QuantityLength inches =
                new QuantityLength(36.0, LengthUnit.INCHES);

        System.out.println(
                "Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)"
        );

        System.out.println(
                "Output: Equal (" + yardToInch.equals(inches) + ")"
        );

        System.out.println();

        QuantityLength cm1 =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        QuantityLength cm2 =
                new QuantityLength(2.0, LengthUnit.CENTIMETERS);

        System.out.println(
                "Input: Quantity(2.0, CENTIMETERS) and Quantity(2.0, CENTIMETERS)"
        );

        System.out.println(
                "Output: Equal (" + cm1.equals(cm2) + ")"
        );

        System.out.println();

        QuantityLength cm =
                new QuantityLength(1.0, LengthUnit.CENTIMETERS);

        QuantityLength inch =
                new QuantityLength(0.393701, LengthUnit.INCHES);

        System.out.println(
                "Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)"
        );

        System.out.println("Output: Equal (" + cm.equals(inch) + ")");
    }
}
package com.src.main;

public class QuantityMeasurementApp {

    // Enum for supported units
    public enum LengthUnit {

        FEET(1.0),
        INCH(1.0 / 12.0);

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

            // Compare converted values
            return Double.compare(this.toFeet(), other.toFeet()) == 0;
        }

        @Override
        public String toString() {
            return "Quantity(" + value + ", " + unit + ")";
        }
    }

    // Main Method
    public static void main(String[] args) {

        // 1 feet == 12 inches
        QuantityLength feet =
                new QuantityLength(1.0, LengthUnit.FEET);

        QuantityLength inches =
                new QuantityLength(12.0, LengthUnit.INCH);

        boolean result1 = feet.equals(inches);

        System.out.println(
                "Input: Quantity(1.0, \"feet\") and Quantity(12.0, \"inches\")"
        );

        System.out.println("Output: Equal (" + result1 + ")");

        System.out.println();

        // Same inch comparison
        QuantityLength inch1 =
                new QuantityLength(1.0, LengthUnit.INCH);

        QuantityLength inch2 =
                new QuantityLength(1.0, LengthUnit.INCH);

        boolean result2 = inch1.equals(inch2);

        System.out.println(
                "Input: Quantity(1.0, \"inch\") and Quantity(1.0, \"inch\")"
        );

        System.out.println("Output: Equal (" + result2 + ")");
    }
}
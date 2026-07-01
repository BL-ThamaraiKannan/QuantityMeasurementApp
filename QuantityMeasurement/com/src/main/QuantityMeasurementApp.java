package com.src.main;
public class QuantityMeasurementApp {

    // Feet class
    public static class Feet {

        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

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

            Feet feet = (Feet) obj;

            // Safe double comparison
            return Double.compare(this.value, feet.value) == 0;
        }
    }

    // Inches class
    public static class Inches {

        private final double value;

        public Inches(double value) {
            this.value = value;
        }

        public double getValue() {
            return value;
        }

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

            Inches inches = (Inches) obj;

            // Safe double comparison
            return Double.compare(this.value, inches.value) == 0;
        }
    }

    // Static method for Feet equality check
    public static boolean compareFeet(double value1, double value2) {

        Feet feet1 = new Feet(value1);
        Feet feet2 = new Feet(value2);

        return feet1.equals(feet2);
    }

    // Static method for Inches equality check
    public static boolean compareInches(double value1, double value2) {

        Inches inches1 = new Inches(value1);
        Inches inches2 = new Inches(value2);

        return inches1.equals(inches2);
    }

    // Main method
    public static void main(String[] args) {

        // Inches comparison
        boolean inchResult = compareInches(1.0, 1.0);

        System.out.println("Input: 1.0 inch and 1.0 inch");
        System.out.println("Output: Equal (" + inchResult + ")");

        System.out.println();

        // Feet comparison
        boolean feetResult = compareFeet(1.0, 1.0);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feetResult + ")");
    }
}
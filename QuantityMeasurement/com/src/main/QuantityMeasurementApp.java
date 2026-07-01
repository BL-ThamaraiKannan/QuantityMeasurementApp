package com.src.main;

public class QuantityMeasurementApp {

    // Inner class representing Feet measurement
    public static class Feet {

        // Immutable value field
        private final double value;

        // Constructor
        public Feet(double value) {
            this.value = value;
        }

        // Getter method
        public double getValue() {
            return value;
        }

        // Overriding equals() method
        @Override
        public boolean equals(Object obj) {

            // Same reference check
            if (this == obj) {
                return true;
            }

            // Null check and type check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // Safe casting
            Feet feet = (Feet) obj;

            // Compare double values safely
            return Double.compare(this.value, feet.value) == 0;
        }
    }

    // Main method
    public static void main(String[] args) {

        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);

        boolean result = feet1.equals(feet2);

        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + result + ")");
    }
}
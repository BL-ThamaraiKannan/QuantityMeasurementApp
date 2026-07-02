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

    // QuantityLength Class
    public static class QuantityLength {

        private static final double EPSILON = 0.0001;

        public final double value;
        private final LengthUnit unit;

        public QuantityLength(double value, LengthUnit unit) {

            validateValue(value);

            if (unit == null) {
                throw new IllegalArgumentException(
                        "Unit cannot be null"
                );
            }

            this.value = value;
            this.unit = unit;
        }

        // Validation
        private static void validateValue(double value) {

            if (!Double.isFinite(value)) {
                throw new IllegalArgumentException(
                        "Value must be finite"
                );
            }
        }

        // Convert current value to feet
        private double toFeet() {
            return value * unit.getConversionFactor();
        }

        // Convert to target unit
        public QuantityLength convertTo(LengthUnit targetUnit) {

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            double convertedValue =
                    this.toFeet() /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    convertedValue,
                    targetUnit
            );
        }

        // UC6 Addition (default first operand unit)
        public QuantityLength add(QuantityLength other) {

            return add(other, this.unit);
        }

        // UC7 Addition with Explicit Target Unit
        public QuantityLength add(
                QuantityLength other,
                LengthUnit targetUnit
        ) {

            if (other == null) {
                throw new IllegalArgumentException(
                        "Second operand cannot be null"
                );
            }

            if (targetUnit == null) {
                throw new IllegalArgumentException(
                        "Target unit cannot be null"
                );
            }

            double totalFeet =
                    this.toFeet() + other.toFeet();

            double resultValue =
                    totalFeet /
                            targetUnit.getConversionFactor();

            return new QuantityLength(
                    resultValue,
                    targetUnit
            );
        }

        // Static add method
        public static QuantityLength add(
                QuantityLength first,
                QuantityLength second,
                LengthUnit targetUnit
        ) {

            if (first == null || second == null) {
                throw new IllegalArgumentException(
                        "Operands cannot be null"
                );
            }

            return first.add(second, targetUnit);
        }

        // Equality
        @Override
        public boolean equals(Object obj) {

            if (this == obj) {
                return true;
            }

            if (obj == null ||
                    getClass() != obj.getClass()) {
                return false;
            }

            QuantityLength other =
                    (QuantityLength) obj;

            return Math.abs(
                    this.toFeet() - other.toFeet()
            ) < EPSILON;
        }

        @Override
        public String toString() {

            return "Quantity(" +
                    value +
                    ", " +
                    unit +
                    ")";
        }
    }

    // Demonstration Method
    public static void demonstrateAddition(
            QuantityLength first,
            QuantityLength second,
            LengthUnit targetUnit
    ) {

        QuantityLength result =
                first.add(second, targetUnit);

        System.out.println(
                "Input: add(" +
                        first + ", " +
                        second + ", " +
                        targetUnit + ")"
        );

        System.out.println(
                "Output: " + result
        );

        System.out.println();
    }

    // Main Method
    public static void main(String[] args) {

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.FEET
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.INCHES
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.FEET
                ),
                new QuantityLength(
                        12.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.YARDS
        );

        demonstrateAddition(
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                ),
                new QuantityLength(
                        3.0,
                        LengthUnit.FEET
                ),
                LengthUnit.YARDS
        );

        demonstrateAddition(
                new QuantityLength(
                        36.0,
                        LengthUnit.INCHES
                ),
                new QuantityLength(
                        1.0,
                        LengthUnit.YARDS
                ),
                LengthUnit.FEET
        );

        demonstrateAddition(
                new QuantityLength(
                        2.54,
                        LengthUnit.CENTIMETERS
                ),
                new QuantityLength(
                        1.0,
                        LengthUnit.INCHES
                ),
                LengthUnit.CENTIMETERS
        );
    }
}
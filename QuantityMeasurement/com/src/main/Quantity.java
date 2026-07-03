package com.src.main;
public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        validateValue(value);

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    private void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }
    }

    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // Convert
    public Quantity<U> convertTo(U targetUnit) {

        validateTargetUnit(targetUnit);

        double baseValue =
                unit.convertToBaseUnit(value);

        double convertedValue =
                targetUnit.convertFromBaseUnit(
                        baseValue
                );

        return new Quantity<>(
                round(convertedValue),
                targetUnit
        );
    }

    // Addition
    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(other, this.unit);
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateOperand(other);
        validateTargetUnit(targetUnit);

        double result =
                unit.convertToBaseUnit(value)
                        + other.unit.convertToBaseUnit(
                        other.value
                );

        double converted =
                targetUnit.convertFromBaseUnit(
                        result
                );

        return new Quantity<>(
                round(converted),
                targetUnit
        );
    }

    // UC12 Subtraction
    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateOperand(other);
        validateTargetUnit(targetUnit);

        double result =
                unit.convertToBaseUnit(value)
                        - other.unit.convertToBaseUnit(
                        other.value
                );

        double converted =
                targetUnit.convertFromBaseUnit(
                        result
                );

        return new Quantity<>(
                round(converted),
                targetUnit
        );
    }

    // UC12 Division
    public double divide(
            Quantity<U> other
    ) {

        validateOperand(other);

        double divisor =
                other.unit.convertToBaseUnit(
                        other.value
                );

        if (Math.abs(divisor) < EPSILON) {
            throw new ArithmeticException(
                    "Division by zero"
            );
        }

        double dividend =
                unit.convertToBaseUnit(value);

        return dividend / divisor;
    }

    // Validation
    private void validateOperand(
            Quantity<U> other
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Operand cannot be null"
            );
        }

        if (unit.getClass()
                != other.unit.getClass()) {

            throw new IllegalArgumentException(
                    "Cross-category operation not allowed"
            );
        }
    }

    private void validateTargetUnit(
            U targetUnit
    ) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }
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

        Quantity<?> other =
                (Quantity<?>) obj;

        if (unit.getClass()
                != other.unit.getClass()) {
            return false;
        }

        double thisBase =
                unit.convertToBaseUnit(value);

        double otherBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return Math.abs(
                thisBase - otherBase
        ) < EPSILON;
    }

    @Override
    public int hashCode() {

        double base =
                unit.convertToBaseUnit(value);

        return Double.hashCode(
                round(base)
        );
    }

    private double round(double value) {

        return Math.round(value * 100.0)
                / 100.0;
    }

    @Override
    public String toString() {

        return "Quantity(" +
                value +
                ", " +
                unit.getUnitName() +
                ")";
    }
}
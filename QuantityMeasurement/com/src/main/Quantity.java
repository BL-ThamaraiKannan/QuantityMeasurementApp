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

    // Convert to target unit
    public Quantity<U> convertTo(U targetUnit) {

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

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

    // Add with default target unit
    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(other, this.unit);
    }

    // Add with explicit target unit
    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        if (other == null) {
            throw new IllegalArgumentException(
                    "Other quantity cannot be null"
            );
        }

        if (targetUnit == null) {
            throw new IllegalArgumentException(
                    "Target unit cannot be null"
            );
        }

        double firstBase =
                unit.convertToBaseUnit(value);

        double secondBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        double totalBase =
                firstBase + secondBase;

        double result =
                targetUnit.convertFromBaseUnit(
                        totalBase
                );

        return new Quantity<>(
                round(result),
                targetUnit
        );
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

        // Prevent cross-category comparison
        if (this.unit.getClass() !=
                other.unit.getClass()) {
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

        double baseValue =
                unit.convertToBaseUnit(value);

        return Double.hashCode(
                round(baseValue)
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

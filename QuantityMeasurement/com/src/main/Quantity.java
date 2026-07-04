package com.src.main;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {

    private static final double EPSILON = 0.0001;

    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {

        validateFinite(value);

        if (unit == null) {
            throw new IllegalArgumentException(
                    "Unit cannot be null"
            );
        }

        this.value = value;
        this.unit = unit;
    }

    // Arithmetic Operations Enum
    private enum ArithmeticOperation {

        ADD((a, b) -> a + b),

        SUBTRACT((a, b) -> a - b),

        DIVIDE((a, b) -> {

            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException(
                        "Division by zero"
                );
            }

            return a / b;
        });

        private final DoubleBinaryOperator operator;

        ArithmeticOperation(
                DoubleBinaryOperator operator
        ) {

            this.operator = operator;
        }

        public double compute(
                double left,
                double right
        ) {

            return operator.applyAsDouble(
                    left,
                    right
            );
        }
    }

    // Getters
    public double getValue() {
        return value;
    }

    public U getUnit() {
        return unit;
    }

    // ---------------- CONVERSION ----------------

    public Quantity<U> convertTo(
            U targetUnit
    ) {

        validateTargetUnit(targetUnit);

        double baseValue =
                unit.convertToBaseUnit(value);

        double converted =
                targetUnit.convertFromBaseUnit(
                        baseValue
                );

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit
        );
    }

    // ---------------- ADDITION ----------------

    public Quantity<U> add(
            Quantity<U> other
    ) {

        return add(other, this.unit);
    }

    public Quantity<U> add(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double baseResult =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.ADD
                );

        double converted =
                targetUnit.convertFromBaseUnit(
                        baseResult
                );

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit
        );
    }

    // ---------------- SUBTRACTION ----------------

    public Quantity<U> subtract(
            Quantity<U> other
    ) {

        return subtract(other, this.unit);
    }

    public Quantity<U> subtract(
            Quantity<U> other,
            U targetUnit
    ) {

        validateArithmeticOperands(
                other,
                targetUnit,
                true
        );

        double baseResult =
                performBaseArithmetic(
                        other,
                        ArithmeticOperation.SUBTRACT
                );

        double converted =
                targetUnit.convertFromBaseUnit(
                        baseResult
                );

        return new Quantity<>(
                roundToTwoDecimals(converted),
                targetUnit
        );
    }

    // ---------------- DIVISION ----------------

    public double divide(
            Quantity<U> other
    ) {

        validateArithmeticOperands(
                other,
                null,
                false
        );

        return performBaseArithmetic(
                other,
                ArithmeticOperation.DIVIDE
        );
    }

    // ---------------- CENTRALIZED HELPER ----------------

    private double performBaseArithmetic(
            Quantity<U> other,
            ArithmeticOperation operation
    ) {

        double firstBase =
                unit.convertToBaseUnit(value);

        double secondBase =
                other.unit.convertToBaseUnit(
                        other.value
                );

        return operation.compute(
                firstBase,
                secondBase
        );
    }

    // ---------------- VALIDATION ----------------

    private void validateArithmeticOperands(
            Quantity<U> other,
            U targetUnit,
            boolean targetUnitRequired
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

        validateFinite(this.value);
        validateFinite(other.value);

        if (targetUnitRequired) {
            validateTargetUnit(targetUnit);
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

    private void validateFinite(
            double number
    ) {

        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }
    }

    // ---------------- EQUALITY ----------------

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

        double baseValue =
                unit.convertToBaseUnit(value);

        return Double.hashCode(
                roundToTwoDecimals(baseValue)
        );
    }

    // ---------------- ROUNDING ----------------

    private double roundToTwoDecimals(
            double value
    ) {

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
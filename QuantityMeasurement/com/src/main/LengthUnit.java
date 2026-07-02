package com.src.main;

public enum LengthUnit {

    FEET(1.0),
    INCHES(1.0 / 12.0),
    YARDS(3.0),
    CENTIMETERS(1.0 / 30.48);

    // Conversion factor relative to FEET (base unit)
    private final double conversionFactor;

    LengthUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert current unit value to base unit (FEET)
    public double convertToBaseUnit(double value) {

        validateValue(value);

        return value * conversionFactor;
    }

    // Convert base unit (FEET) value to current unit
    public double convertFromBaseUnit(double baseValue) {

        validateValue(baseValue);

        return baseValue / conversionFactor;
    }

    // Validation
    private void validateValue(double value) {

        if (!Double.isFinite(value)) {
            throw new IllegalArgumentException(
                    "Value must be finite"
            );
        }
    }
}

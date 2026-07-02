package com.src.main;

public enum WeightUnit {

    KILOGRAM(1.0),
    GRAM(0.001),
    POUND(0.453592);

    // Conversion factor relative to KILOGRAM
    private final double conversionFactor;

    WeightUnit(double conversionFactor) {
        this.conversionFactor = conversionFactor;
    }

    public double getConversionFactor() {
        return conversionFactor;
    }

    // Convert to base unit (KILOGRAM)
    public double convertToBaseUnit(double value) {

        validateValue(value);

        return value * conversionFactor;
    }

    // Convert from base unit (KILOGRAM)
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
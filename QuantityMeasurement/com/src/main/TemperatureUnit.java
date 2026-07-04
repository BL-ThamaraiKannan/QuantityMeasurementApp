package com.src.main;

import java.util.function.Function;

public enum TemperatureUnit implements IMeasurable {

    CELSIUS(
            c -> c, // to base (Celsius)
            c -> c  // from base
    ),

    FAHRENHEIT(
            f -> (f - 32) * 5 / 9,        // to Celsius
            c -> (c * 9 / 5) + 32         // from Celsius
    );

    private final Function<Double, Double> toBase;
    private final Function<Double, Double> fromBase;

    // Temperature DOES NOT support arithmetic
    private final SupportsArithmetic supportsArithmetic = () -> false;

    TemperatureUnit(
            Function<Double, Double> toBase,
            Function<Double, Double> fromBase
    ) {
        this.toBase = toBase;
        this.fromBase = fromBase;
    }

    @Override
    public double getConversionFactor() {
        return 1.0; // Not meaningful for temperature
    }

    @Override
    public double convertToBaseUnit(double value) {
        return toBase.apply(value);
    }

    @Override
    public double convertFromBaseUnit(double baseValue) {
        return fromBase.apply(baseValue);
    }

    @Override
    public String getUnitName() {
        return name();
    }

    @Override
    public boolean supportsArithmetic() {
        return supportsArithmetic.isSupported();
    }

    @Override
    public void validateOperationSupport(String operation) {
        throw new UnsupportedOperationException(
                "Temperature does not support " + operation +
                        " operation (non-linear scale)"
        );
    }
}
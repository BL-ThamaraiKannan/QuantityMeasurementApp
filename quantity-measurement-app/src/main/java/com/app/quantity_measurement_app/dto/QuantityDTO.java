package com.app.quantity_measurement_app.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class QuantityDTO {

    @NotNull(message = "Value cannot be null")
    private Double value;

    @NotEmpty(message = "Unit name cannot be empty")
    private String unit;

    @NotEmpty(message = "Measurement type cannot be empty")
    @Pattern(regexp = "^(LengthUnit|VolumeUnit|WeightUnit|TemperatureUnit)$",
            message = "Invalid measurement type specified")
    private String measurementType;
}
package com.app.quantity_measurement_app.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class QuantityInputDTO {

    @Valid
    @NotNull(message = "First quantity context is required")
    private QuantityDTO thisQuantityDTO;

    @Valid
    @NotNull(message = "Second quantity context is required")
    private QuantityDTO thatQuantityDTO;
}
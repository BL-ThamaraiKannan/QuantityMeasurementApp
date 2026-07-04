package com.src.main;

import com.src.main.controller.QuantityMeasurementController;
import com.src.main.dto.QuantityDTO;
import com.src.main.repository.IQuantityMeasurementRepository;
import com.src.main.repository.QuantityMeasurementCacheRepository;
import com.src.main.service.IQuantityMeasurementService;
import com.src.main.service.QuantityMeasurementServiceImpl;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        // Factory + DI
        IQuantityMeasurementRepository repo =
                QuantityMeasurementCacheRepository.getInstance();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        // Example Usage
        controller.performComparison(
                new QuantityDTO(1, "FEET", "LENGTH"),
                new QuantityDTO(12, "INCHES", "LENGTH")
        );

        controller.performConversion(
                new QuantityDTO(100, "CELSIUS", "TEMPERATURE"),
                "FAHRENHEIT"
        );

        controller.performAddition(
                new QuantityDTO(1, "KILOGRAM", "WEIGHT"),
                new QuantityDTO(1000, "GRAM", "WEIGHT")
        );
    }
}
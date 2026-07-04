package com.src.main;

import com.src.main.controller.QuantityMeasurementController;
import com.src.main.dto.QuantityDTO;
import com.src.main.repository.IQuantityMeasurementRepository;
import com.src.main.repository.QuantityMeasurementDatabaseRepository;
import com.src.main.service.IQuantityMeasurementService;
import com.src.main.service.QuantityMeasurementServiceImpl;
import com.src.main.util.DatabaseInitializer;

public class QuantityMeasurementApp {

    public static void main(String[] args) {

        //  Initialize DB
        DatabaseInitializer.init();

        IQuantityMeasurementRepository repo =
                new QuantityMeasurementDatabaseRepository();

        IQuantityMeasurementService service =
                new QuantityMeasurementServiceImpl(repo);

        QuantityMeasurementController controller =
                new QuantityMeasurementController(service);

        controller.performComparison(
                new QuantityDTO(1, "FEET", "LENGTH"),
                new QuantityDTO(12, "INCHES", "LENGTH")
        );
    }
}
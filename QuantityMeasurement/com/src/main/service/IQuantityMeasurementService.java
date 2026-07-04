package com.src.main.service;

import com.src.main.dto.QuantityDTO;
import com.src.main.entity.QuantityMeasurementEntity;

public interface IQuantityMeasurementService {

    QuantityMeasurementEntity compare(
            QuantityDTO q1,
            QuantityDTO q2
    );

    QuantityMeasurementEntity convert(
            QuantityDTO q,
            String targetUnit
    );

    QuantityMeasurementEntity add(
            QuantityDTO q1,
            QuantityDTO q2
    );
}
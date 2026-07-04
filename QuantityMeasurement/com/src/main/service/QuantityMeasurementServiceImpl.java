package com.src.main.service;

import com.src.main.*;
import com.src.main.dto.QuantityDTO;
import com.src.main.entity.QuantityMeasurementEntity;
import com.src.main.exception.QuantityMeasurementException;
import com.src.main.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl
        implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(
            IQuantityMeasurementRepository repository
    ) {
        this.repository = repository;
    }

    @Override
    public QuantityMeasurementEntity compare(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        try {
            Quantity<?> a = mapToQuantity(q1);
            Quantity<?> b = mapToQuantity(q2);

            boolean result = a.equals(b);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            "COMPARE", q1, q2, result
                    );

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return new QuantityMeasurementEntity(e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity convert(
            QuantityDTO q,
            String targetUnit
    ) {

        try {
            Quantity quantity = mapToQuantity(q);

            IMeasurable target =
                    resolveUnit(q.getType(), targetUnit);

            Quantity result = quantity.convertTo(target);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            "CONVERT", q, null, result
                    );

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return new QuantityMeasurementEntity(e.getMessage());
        }
    }

    @Override
    public QuantityMeasurementEntity add(
            QuantityDTO q1,
            QuantityDTO q2
    ) {

        try {
            Quantity a = mapToQuantity(q1);
            Quantity b = mapToQuantity(q2);

            Quantity result = a.add(b);

            QuantityMeasurementEntity entity =
                    new QuantityMeasurementEntity(
                            "ADD", q1, q2, result
                    );

            repository.save(entity);
            return entity;

        } catch (Exception e) {
            return new QuantityMeasurementEntity(e.getMessage());
        }
    }

    // ---------------- Helper Methods ----------------

    private Quantity<?> mapToQuantity(QuantityDTO dto) {

        IMeasurable unit =
                resolveUnit(dto.getType(), dto.getUnit());

        return new Quantity<>(dto.getValue(), unit);
    }

    private IMeasurable resolveUnit(
            String type,
            String unit
    ) {

        switch (type) {
            case "LENGTH":
                return LengthUnit.valueOf(unit);

            case "WEIGHT":
                return WeightUnit.valueOf(unit);

            case "VOLUME":
                return VolumeUnit.valueOf(unit);

            case "TEMPERATURE":
                return TemperatureUnit.valueOf(unit);

            default:
                throw new QuantityMeasurementException(
                        "Invalid type"
                );
        }
    }
}

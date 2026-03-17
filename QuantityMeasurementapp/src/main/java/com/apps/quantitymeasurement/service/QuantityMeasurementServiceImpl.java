package com.apps.quantitymeasurement.service;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.model.QuantityModel;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.quantity.Quantity;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;
import com.apps.quantitymeasurement.unit.*;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 * QuantityMeasurementServiceImpl
 *
 * Concrete service implementation for quantity measurement operations.
 *
 * Architectural Role:
 * - Belongs to Service Layer in N-Tier architecture
 * - Contains business logic
 * - Converts DTOs to domain/business objects
 * - Delegates persistence responsibility to repository layer
 *
 * Important Rule:
 * - Controller must not contain business logic
 * - Repository must not perform business validation
 * - This service acts as the proper orchestration layer
 */
public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private static final Logger logger = Logger.getLogger(QuantityMeasurementServiceImpl.class.getName());

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        this.repository = repository;
        logger.info("QuantityMeasurementServiceImpl initialized with repository: "
                + repository.getClass().getSimpleName());
    }

    @Override
    public boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        QuantityModel first = mapToModel(thisQuantityDTO);
        QuantityModel second = mapToModel(thatQuantityDTO);

        Quantity quantityOne = new Quantity(first.getValue(), first.getUnit());
        Quantity quantityTwo = new Quantity(second.getValue(), second.getUnit());

        boolean result = quantityOne.equivalentTo(quantityTwo);

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(thisQuantityDTO.getValue());
        entity.setThisUnit(thisQuantityDTO.getUnitName());
        entity.setThisMeasurementType(thisQuantityDTO.getMeasurementType());
        entity.setThatValue(thatQuantityDTO.getValue());
        entity.setThatUnit(thatQuantityDTO.getUnitName());
        entity.setThatMeasurementType(thatQuantityDTO.getMeasurementType());
        entity.setOperation("COMPARE");
        entity.setResultString(result ? "Equal" : "Not Equal");

        repository.save(entity);

        logger.info("Compare operation completed. Result: " + result);
        return result;
    }

    @Override
    public QuantityDTO convert(QuantityDTO sourceQuantityDTO, QuantityDTO targetUnitDTO) {
        QuantityModel sourceModel = mapToModel(sourceQuantityDTO);
        IMeasurable targetUnit = resolveUnit(
                targetUnitDTO.getMeasurementType(),
                targetUnitDTO.getUnitName()
        );

        Quantity sourceQuantity = new Quantity(sourceModel.getValue(), sourceModel.getUnit());
        Quantity convertedQuantity = sourceQuantity.convertTo(targetUnit);

        QuantityDTO resultDTO = new QuantityDTO(
                convertedQuantity.getValue(),
                targetUnit.getUnitName(),
                targetUnit.getMeasurementType()
        );

        QuantityMeasurementEntity entity = new QuantityMeasurementEntity();
        entity.setThisValue(sourceQuantityDTO.getValue());
        entity.setThisUnit(sourceQuantityDTO.getUnitName());
        entity.setThisMeasurementType(sourceQuantityDTO.getMeasurementType());
        entity.setThatValue(resultDTO.getValue());
        entity.setThatUnit(resultDTO.getUnitName());
        entity.setThatMeasurementType(resultDTO.getMeasurementType());
        entity.setOperation("CONVERT");
        entity.setResultString(String.valueOf(resultDTO.getValue()));

        repository.save(entity);

        logger.info("Convert operation completed. Result: " + resultDTO);
        return resultDTO;
    }

    @Override
    public List<String> getAllMeasurementHistory() {
        return repository.getAllMeasurements()
                .stream()
                .map(QuantityMeasurementEntity::toString)
                .collect(Collectors.toList());
    }

    @Override
    public int getMeasurementCount() {
        return repository.getTotalCount();
    }

    @Override
    public void deleteAllMeasurements() {
        repository.deleteAll();
        logger.info("All measurements deleted through service layer.");
    }

    /**
     * Converts DTO into service-layer model.
     *
     * @param quantityDTO input DTO
     * @return mapped model
     */
    private QuantityModel mapToModel(QuantityDTO quantityDTO) {
        IMeasurable measurable = resolveUnit(quantityDTO.getMeasurementType(), quantityDTO.getUnitName());
        return new QuantityModel(quantityDTO.getValue(), measurable);
    }

    /**
     * Resolves enum unit from measurementType and unitName.
     *
     * @param measurementType category
     * @param unitName unit name
     * @return measurable unit enum
     */
    private IMeasurable resolveUnit(String measurementType, String unitName) {
        return switch (measurementType) {
            case "LengthUnit" -> LengthUnit.valueOf(unitName);
            case "WeightUnit" -> WeightUnit.valueOf(unitName);
            case "VolumeUnit" -> VolumeUnit.valueOf(unitName);
            case "TemperatureUnit" -> TemperatureUnit.valueOf(unitName);
            default -> throw new QuantityMeasurementException("Unsupported measurement type: " + measurementType);
        };
    }
}
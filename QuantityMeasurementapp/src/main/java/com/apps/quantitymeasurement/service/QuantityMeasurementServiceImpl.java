package com.apps.quantitymeasurement.service;

import com.apps.quantitymeasurement.IMeasurable;
import com.apps.quantitymeasurement.LengthUnit;
import com.apps.quantitymeasurement.Quantity;
import com.apps.quantitymeasurement.TemperatureUnit;
import com.apps.quantitymeasurement.VolumeUnit;
import com.apps.quantitymeasurement.WeightUnit;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.entity.QuantityMeasurementEntity;
import com.apps.quantitymeasurement.exception.QuantityMeasurementException;
import com.apps.quantitymeasurement.model.QuantityModel;
import com.apps.quantitymeasurement.repository.IQuantityMeasurementRepository;

public class QuantityMeasurementServiceImpl implements IQuantityMeasurementService {

    private final IQuantityMeasurementRepository repository;

    public QuantityMeasurementServiceImpl(IQuantityMeasurementRepository repository) {
        if (repository == null) {
            throw new IllegalArgumentException("Repository cannot be null");
        }
        this.repository = repository;
    }

    private QuantityMeasurementEntity saveAndReturn(QuantityMeasurementEntity entity) {
        repository.save(entity);
        return entity;
    }

    private QuantityMeasurementEntity error(String operation, QuantityDTO q1, QuantityDTO q2, Exception e) {
        return saveAndReturn(new QuantityMeasurementEntity(operation, q1, q2, e.getMessage()));
    }

    private QuantityMeasurementEntity error(String operation, QuantityDTO q1, Exception e) {
        return saveAndReturn(new QuantityMeasurementEntity(operation, q1, e.getMessage()));
    }

    private void validateDTO(QuantityDTO dto) {
        if (dto == null) {
            throw new QuantityMeasurementException("QuantityDTO cannot be null");
        }
        if (!Double.isFinite(dto.getValue())) {
            throw new QuantityMeasurementException("Quantity value must be finite");
        }
        if (dto.getUnit() == null || dto.getMeasurementType() == null) {
            throw new QuantityMeasurementException("Unit and measurement type cannot be null");
        }
    }

    private <U extends IMeasurable> QuantityModel<U> toModel(QuantityDTO dto) {
        validateDTO(dto);

        @SuppressWarnings("unchecked")
        U unit = (U) IMeasurable.resolve(dto.getMeasurementType(), dto.getUnit());

        return new QuantityModel<>(dto.getValue(), unit);
    }

    private <U extends IMeasurable> Quantity<U> toQuantity(QuantityDTO dto) {
        QuantityModel<U> model = toModel(dto);
        return new Quantity<>(model.getValue(), model.getUnit());
    }

    private <U extends IMeasurable> QuantityDTO toDTO(Quantity<U> quantity) {
        return new QuantityDTO(
                quantity.getValue(),
                quantity.getUnit().getUnitName(),
                quantity.getUnit().getMeasurementType()
        );
    }

    @Override
    public QuantityMeasurementEntity compare(QuantityDTO operand1, QuantityDTO operand2) {
        try {
            Quantity<? extends IMeasurable> q1 = toQuantity(operand1);
            Quantity<? extends IMeasurable> q2 = toQuantity(operand2);

            boolean result = q1.equals(q2);
            return saveAndReturn(new QuantityMeasurementEntity("COMPARE", operand1, operand2, result));

        } catch (Exception e) {
            return error("COMPARE", operand1, operand2, e);
        }
    }

    @Override
    public QuantityMeasurementEntity convert(QuantityDTO operand1, String targetUnit) {
        try {
            validateDTO(operand1);

            String type = operand1.getMeasurementType().toUpperCase();

            switch (type) {
                case "LENGTH": {
                    Quantity<LengthUnit> q1 = this.<LengthUnit>toQuantity(operand1);
                    Quantity<LengthUnit> result = q1.convertTo(LengthUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("CONVERT", operand1, toDTO(result)));
                }

                case "WEIGHT": {
                    Quantity<WeightUnit> q1 = this.<WeightUnit>toQuantity(operand1);
                    Quantity<WeightUnit> result = q1.convertTo(WeightUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("CONVERT", operand1, toDTO(result)));
                }

                case "VOLUME": {
                    Quantity<VolumeUnit> q1 = this.<VolumeUnit>toQuantity(operand1);
                    Quantity<VolumeUnit> result = q1.convertTo(VolumeUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("CONVERT", operand1, toDTO(result)));
                }

                case "TEMPERATURE": {
                    Quantity<TemperatureUnit> q1 = this.<TemperatureUnit>toQuantity(operand1);
                    Quantity<TemperatureUnit> result = q1.convertTo(TemperatureUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("CONVERT", operand1, toDTO(result)));
                }

                default:
                    throw new QuantityMeasurementException("Unsupported measurement type");
            }

        } catch (Exception e) {
            return error("CONVERT", operand1, e);
        }
    }

    @Override
    public QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2) {
        return add(operand1, operand2, operand1 != null ? operand1.getUnit() : null);
    }

    @Override
    public QuantityMeasurementEntity add(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
        try {
            validateDTO(operand1);
            validateDTO(operand2);

            if (!operand1.getMeasurementType().equalsIgnoreCase(operand2.getMeasurementType())) {
                throw new QuantityMeasurementException("Cross-category addition is not allowed");
            }

            String type = operand1.getMeasurementType().toUpperCase();

            switch (type) {
                case "LENGTH": {
                    Quantity<LengthUnit> q1 = this.<LengthUnit>toQuantity(operand1);
                    Quantity<LengthUnit> q2 = this.<LengthUnit>toQuantity(operand2);
                    Quantity<LengthUnit> result = q1.add(q2, LengthUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("ADD", operand1, operand2, toDTO(result)));
                }

                case "WEIGHT": {
                    Quantity<WeightUnit> q1 = this.<WeightUnit>toQuantity(operand1);
                    Quantity<WeightUnit> q2 = this.<WeightUnit>toQuantity(operand2);
                    Quantity<WeightUnit> result = q1.add(q2, WeightUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("ADD", operand1, operand2, toDTO(result)));
                }

                case "VOLUME": {
                    Quantity<VolumeUnit> q1 = this.<VolumeUnit>toQuantity(operand1);
                    Quantity<VolumeUnit> q2 = this.<VolumeUnit>toQuantity(operand2);
                    Quantity<VolumeUnit> result = q1.add(q2, VolumeUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("ADD", operand1, operand2, toDTO(result)));
                }

                case "TEMPERATURE":
                    throw new UnsupportedOperationException("Addition not supported for absolute temperatures");

                default:
                    throw new QuantityMeasurementException("Unsupported measurement type");
            }

        } catch (Exception e) {
            return error("ADD", operand1, operand2, e);
        }
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2) {
        return subtract(operand1, operand2, operand1 != null ? operand1.getUnit() : null);
    }

    @Override
    public QuantityMeasurementEntity subtract(QuantityDTO operand1, QuantityDTO operand2, String targetUnit) {
        try {
            validateDTO(operand1);
            validateDTO(operand2);

            if (!operand1.getMeasurementType().equalsIgnoreCase(operand2.getMeasurementType())) {
                throw new QuantityMeasurementException("Cross-category subtraction is not allowed");
            }

            String type = operand1.getMeasurementType().toUpperCase();

            switch (type) {
                case "LENGTH": {
                    Quantity<LengthUnit> q1 = this.<LengthUnit>toQuantity(operand1);
                    Quantity<LengthUnit> q2 = this.<LengthUnit>toQuantity(operand2);
                    Quantity<LengthUnit> result = q1.subtract(q2, LengthUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("SUBTRACT", operand1, operand2, toDTO(result)));
                }

                case "WEIGHT": {
                    Quantity<WeightUnit> q1 = this.<WeightUnit>toQuantity(operand1);
                    Quantity<WeightUnit> q2 = this.<WeightUnit>toQuantity(operand2);
                    Quantity<WeightUnit> result = q1.subtract(q2, WeightUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("SUBTRACT", operand1, operand2, toDTO(result)));
                }

                case "VOLUME": {
                    Quantity<VolumeUnit> q1 = this.<VolumeUnit>toQuantity(operand1);
                    Quantity<VolumeUnit> q2 = this.<VolumeUnit>toQuantity(operand2);
                    Quantity<VolumeUnit> result = q1.subtract(q2, VolumeUnit.valueOf(targetUnit.toUpperCase()));
                    return saveAndReturn(new QuantityMeasurementEntity("SUBTRACT", operand1, operand2, toDTO(result)));
                }

                case "TEMPERATURE":
                    throw new UnsupportedOperationException("Subtraction not supported for absolute temperatures");

                default:
                    throw new QuantityMeasurementException("Unsupported measurement type");
            }

        } catch (Exception e) {
            return error("SUBTRACT", operand1, operand2, e);
        }
    }

    @Override
    public QuantityMeasurementEntity divide(QuantityDTO operand1, QuantityDTO operand2) {
        try {
            validateDTO(operand1);
            validateDTO(operand2);

            if (!operand1.getMeasurementType().equalsIgnoreCase(operand2.getMeasurementType())) {
                throw new QuantityMeasurementException("Cross-category division is not allowed");
            }

            String type = operand1.getMeasurementType().toUpperCase();
            double result;

            switch (type) {
                case "LENGTH": {
                    Quantity<LengthUnit> q1 = this.<LengthUnit>toQuantity(operand1);
                    Quantity<LengthUnit> q2 = this.<LengthUnit>toQuantity(operand2);
                    result = q1.divide(q2);
                    break;
                }

                case "WEIGHT": {
                    Quantity<WeightUnit> q1 = this.<WeightUnit>toQuantity(operand1);
                    Quantity<WeightUnit> q2 = this.<WeightUnit>toQuantity(operand2);
                    result = q1.divide(q2);
                    break;
                }

                case "VOLUME": {
                    Quantity<VolumeUnit> q1 = this.<VolumeUnit>toQuantity(operand1);
                    Quantity<VolumeUnit> q2 = this.<VolumeUnit>toQuantity(operand2);
                    result = q1.divide(q2);
                    break;
                }

                case "TEMPERATURE":
                    throw new UnsupportedOperationException("Division not supported for temperature");

                default:
                    throw new QuantityMeasurementException("Unsupported measurement type");
            }

            return saveAndReturn(new QuantityMeasurementEntity("DIVIDE", operand1, operand2, result));

        } catch (Exception e) {
            return error("DIVIDE", operand1, operand2, e);
        }
    }
}
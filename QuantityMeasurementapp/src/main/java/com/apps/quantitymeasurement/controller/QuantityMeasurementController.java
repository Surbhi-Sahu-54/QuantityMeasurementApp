package com.apps.quantitymeasurement.controller;
import com.apps.quantitymeasurement.dto.QuantityDTO;
import com.apps.quantitymeasurement.service.IQuantityMeasurementService;

import java.util.List;
import java.util.logging.Logger;
import com.apps.quantitymeasurement.dto.QuantityDTO;
/**
 * QuantityMeasurementController
 *
 * Acts as the entry point between user interaction and the service layer.
 * Belongs to the Controller Layer in the N-Tier architecture.
 *
 * The controller is responsible for receiving input requests from the Application Layer,
 * performing minimal validation, and delegating all business logic to the Service Layer.
 * It must contain NO business logic of its own.
 *
 * Responsibilities include:
 * - Accepting QuantityDTO input from application layer
 * - Forwarding compare/convert requests to the service layer
 * - Returning standardized responses back to the caller
 * - Acting as an intermediary between UI/Application and business logic
 */
public class QuantityMeasurementController {

    private static final Logger logger = Logger.getLogger(QuantityMeasurementController.class.getName());

    private final IQuantityMeasurementService quantityMeasurementService;

    public QuantityMeasurementController(IQuantityMeasurementService quantityMeasurementService) {
        this.quantityMeasurementService = quantityMeasurementService;
        logger.info("QuantityMeasurementController initialized with service: "
                + quantityMeasurementService.getClass().getSimpleName());
    }

    /**
     * Performs equality comparison between two quantities.
     *
     * The controller forwards the request to the service layer
     * which performs the actual comparison logic.
     *
     * @param thisQuantityDTO first quantity
     * @param thatQuantityDTO second quantity
     * @return true if both quantities are equal, otherwise false
     */
    public boolean performComparison(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO) {
        return quantityMeasurementService.compare(thisQuantityDTO, thatQuantityDTO);
    }

    /**
     * Performs unit conversion.
     *
     * The controller delegates the conversion request
     * to the service layer.
     *
     * @param sourceQuantityDTO source quantity DTO
     * @param targetUnitDTO target unit DTO
     * @return converted quantity DTO
     */
    public QuantityDTO performConversion(QuantityDTO sourceQuantityDTO, QuantityDTO targetUnitDTO) {
        return quantityMeasurementService.convert(sourceQuantityDTO, targetUnitDTO);
    }

    /*
     * Returns saved measurement history.
     *
     * @return list of stored measurement records
     */
    public List<String> fetchMeasurementHistory() {
        return quantityMeasurementService.getAllMeasurementHistory();
    }

    /**
     * Returns total number of saved measurement records.
     *
     * @return total record count
     */
    public int fetchMeasurementCount() {
        return quantityMeasurementService.getMeasurementCount();
    }

    /**
     * Deletes all measurements.
     *
     * Useful for testing and reset scenarios.
     */
    public void deleteAllMeasurements() {
        quantityMeasurementService.deleteAllMeasurements();
    }
}
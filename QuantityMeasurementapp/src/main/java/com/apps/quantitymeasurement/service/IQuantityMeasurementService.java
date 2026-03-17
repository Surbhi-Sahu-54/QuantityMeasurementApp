package com.apps.quantitymeasurement.service;
import com.apps.quantitymeasurement.dto.QuantityDTO;

import java.util.List;

/**
 * IQuantityMeasurementService
 *
 * Service contract for quantity measurement business operations.
 *
 * Responsibilities:
 * - Compare quantities
 * - Convert quantities
 * - Expose historical persisted measurements
 * - Keep controller independent of repository implementation
 */
public interface IQuantityMeasurementService {

    boolean compare(QuantityDTO thisQuantityDTO, QuantityDTO thatQuantityDTO);

    QuantityDTO convert(QuantityDTO sourceQuantityDTO, QuantityDTO targetUnitDTO);

    List<String> getAllMeasurementHistory();

    int getMeasurementCount();

    void deleteAllMeasurements();
}
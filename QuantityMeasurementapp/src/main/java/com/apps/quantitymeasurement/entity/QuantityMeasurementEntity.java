package com.apps.quantitymeasurement.entity;
import java.sql.Timestamp;

/**
 * QuantityMeasurementEntity
 *
 * Persistence entity used by repository layer.
 *
 * This class represents a stored measurement operation.
 * It is used for database persistence in UC16.
 *
 * Fields stored:
 * - id
 * - thisValue / thisUnit / thisMeasurementType
 * - thatValue / thatUnit / thatMeasurementType
 * - operation performed
 * - result string
 * - created timestamp
 */
public class QuantityMeasurementEntity {

    private Long id;

    private Double thisValue;
    private String thisUnit;
    private String thisMeasurementType;

    private Double thatValue;
    private String thatUnit;
    private String thatMeasurementType;

    private String operation;
    private String resultString;

    private Timestamp createdAt;

    /**
     * Default constructor required for repository mapping.
     */
    public QuantityMeasurementEntity() {
    }

    /**
     * Constructor for unary operation.
     */
    public QuantityMeasurementEntity(String operation,
                                     Double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     Double resultValue,
                                     String resultUnit,
                                     String resultMeasurementType) {

        this.operation = operation;
        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;

        this.thatValue = resultValue;
        this.thatUnit = resultUnit;
        this.thatMeasurementType = resultMeasurementType;

        this.resultString = String.valueOf(resultValue);
    }

    /**
     * Constructor for binary operation.
     */
    public QuantityMeasurementEntity(String operation,
                                     Double thisValue,
                                     String thisUnit,
                                     String thisMeasurementType,
                                     Double thatValue,
                                     String thatUnit,
                                     String thatMeasurementType,
                                     String resultString) {

        this.operation = operation;

        this.thisValue = thisValue;
        this.thisUnit = thisUnit;
        this.thisMeasurementType = thisMeasurementType;

        this.thatValue = thatValue;
        this.thatUnit = thatUnit;
        this.thatMeasurementType = thatMeasurementType;

        this.resultString = resultString;
    }

    /* ================= GETTERS ================= */

    public Long getId() {
        return id;
    }

    public Double getThisValue() {
        return thisValue;
    }

    public String getThisUnit() {
        return thisUnit;
    }

    public String getThisMeasurementType() {
        return thisMeasurementType;
    }

    public Double getThatValue() {
        return thatValue;
    }

    public String getThatUnit() {
        return thatUnit;
    }

    public String getThatMeasurementType() {
        return thatMeasurementType;
    }

    public String getOperation() {
        return operation;
    }

    public String getResultString() {
        return resultString;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    /* ================= SETTERS ================= */

    public void setId(Long id) {
        this.id = id;
    }

    public void setThisValue(Double thisValue) {
        this.thisValue = thisValue;
    }

    public void setThisUnit(String thisUnit) {
        this.thisUnit = thisUnit;
    }

    public void setThisMeasurementType(String thisMeasurementType) {
        this.thisMeasurementType = thisMeasurementType;
    }

    public void setThatValue(Double thatValue) {
        this.thatValue = thatValue;
    }

    public void setThatUnit(String thatUnit) {
        this.thatUnit = thatUnit;
    }

    public void setThatMeasurementType(String thatMeasurementType) {
        this.thatMeasurementType = thatMeasurementType;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setResultString(String resultString) {
        this.resultString = resultString;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }

    /* ================= toString ================= */

    @Override
    public String toString() {
        return "QuantityMeasurementEntity{" +
                "id=" + id +
                ", thisValue=" + thisValue +
                ", thisUnit='" + thisUnit + '\'' +
                ", thisMeasurementType='" + thisMeasurementType + '\'' +
                ", thatValue=" + thatValue +
                ", thatUnit='" + thatUnit + '\'' +
                ", thatMeasurementType='" + thatMeasurementType + '\'' +
                ", operation='" + operation + '\'' +
                ", resultString='" + resultString + '\'' +
                ", createdAt=" + createdAt +
                '}';
    }
}
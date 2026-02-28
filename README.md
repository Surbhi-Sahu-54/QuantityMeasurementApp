### Quantity Measurement App
---
### UC11 – Volume Measurement Equality, Conversion, and Addition

### (Litre, Millilitre, Gallon)
**(Date: 25 Feb 2026)**

---
### Overview

UC11 extends the Quantity Measurement App to support a third measurement category: Volume, alongside Length and Weight.

Thanks to the generic architecture introduced in UC10, volume support is added without modifying:
- Quantity<U>
- IMeasurable
- Existing Length or Weight logic
- Existing tests for UC1–UC10
  
UC11 validates that the system is truly scalable, generic, and future-proof.

---
### UC11 Objectives
- Introduce Volume as a new measurement category
- Support:
  - Equality comparison
  - Unit conversion
  - Addition (implicit & explicit target unit)
- Ensure volume remains non-interoperable with length and weight
- Prove UC10’s generic design works without refactoring
- Maintain full backward compatibility (UC1–UC10)

---
## Supported Volume Units

| Unit        | Symbol | Conversion to Base (LITRE) |
|------------|--------|----------------------------|
| Litre      | L      | 1.0                        |
| Millilitre | mL     | 0.001                      |
| Gallon (US)| gal    | 3.78541                    |

Base Unit: LITRE

---

### Preconditions
- UC10 generic Quantity<U extends IMeasurable> is fully implemented
- IMeasurable interface is stable
- LengthUnit and WeightUnit already implement IMeasurable
- All UC1–UC10 tests are passing
- No category-specific Quantity classes exist

--- 
### Design Changes in UC11
### Added
 ### VolumeUnit (Enum)
 - Implements IMeasurable
 - Holds only conversion factors
 - Base-unit normalization via LITRE
 - Parsing helper for console input

---
### Not Added (Intentionally)
- No QuantityVolume class
- No changes to Quantity<U>
- No changes to IMeasurable
- No changes to Length or Weight logic
This confirms UC10 scalability.

---
### Main Flow
- User selects Volume category
- User enters values and volume units
- Application creates:
  - Quantity<VolumeUnit>
- Operations performed:
  - Equality
  - Conversion
  - Addition
All logic is handled by the generic Quantity class.

---
### Supported Operations (UC11)
### Equality
- Same-unit equality
  - 1 L == 1 L
- Cross-unit equality
  - 1 L == 1000 mL
- Transitive, symmetric, reflexive
- Cross-category comparison returns false

---
### Unit Conversion

- Any volume unit → any volume unit
- Uses LITRE as base unit
- Round-trip precision preserved within epsilon

---
### Addition
 ### Implicit Target Unit
 - Result unit = first operand’s unit
 
 ### Explicit Target Unit
 - Result unit specified by caller

All operations return new immutable Quantity objects.

---
### Category Safety
- Quantity<VolumeUnit> cannot be compared with:

 - Quantity<LengthUnit>

 - Quantity<WeightUnit>

- Compile-time safety via generics

- Runtime check in equals() ensures isolation

--- 
### Testing Strategy

### Test Coverage Includes:
- Litre–Litre equality
- Litre–Millilitre equality
- Litre–Gallon equality
- Conversion across all unit pairs
- Addition with same and different units
- Explicit target-unit addition
- Volume vs Length incompatibility
- Volume vs Weight incompatibility
- Zero, negative, and large values
- Immutability validation
- Precision and rounding behavior

All UC1–UC11 tests pass without modifying existing test logic.

---
### Concepts Reinforced in UC11
- Generic Scalability
- Open–Closed Principle
- DRY Principle
- Type Safety via Generics
- Immutability
- Architecture Validation

UC10 design is proven correct in real extension.

---
### Conclusion
UC11 confirms that the Quantity Measurement App is now:

- Fully generic
- Easily extensible
- Architecturally sound
- Production-ready

The system scales linearly, not exponentially, with new measurement categories.

---
### 📌 Repository Updates
- UC11 implemented
- Volume category added
- Zero refactoring required
- All tests passing
- Architecture validated
- Committed and pushed the UC11 implementation to the repository.

Code Link: [UC11-VolumeMeasurement](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC11-VolumeMeasurement)

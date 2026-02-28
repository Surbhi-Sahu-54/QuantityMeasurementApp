### Quantity Measurement App
---
### UC9 – Weight Measurement Equality, Conversion, and Addition
(Kilogram, Gram, Pound)
**(Date: 24 Feb 2026)**

---
### Overview

UC9 extends the Quantity Measurement App by introducing a new measurement category: Weight.

Building on the scalable architecture from UC8, this use case demonstrates that the system can support multiple independent measurement categories without modifying existing code.

Weight measurements operate independently from length measurements and support:
 - Equality comparison
 - Unit conversion
 - Arithmetic addition
All weight-related logic follows the same design principles used for length measurements, ensuring architectural consistency, scalability, and maintainability.

---
### UC9 Objectives
- Introduce Weight as a new measurement category
- Support multiple weight units with accurate conversion
- Enable cross-unit equality comparison
- Support arithmetic addition
- Enforce category type safety (Length ≠ Weight)
- Reuse UC8’s enum-based conversion architecture
- Preserve UC1–UC8 functionality without regression

---
### Preconditions
- UC1–UC8 fully implemented and passing
- Enum-based unit conversion architecture in place

## Supported Weight Units

| Unit     | Symbol | Conversion        |
|----------|--------|------------------|
| Kilogram | kg     | Base Unit        |
| Gram     | g      | 1 kg = 1000 g    |
| Pound    | lb     | 1 lb = 0.453592 kg |

All conversion factors are defined relative to the base unit (Kilogram).

---
### Design Additions in UC9
### WeightUnit (Enum)

Responsible for:
 - Defining weight units
 - Holding conversion factors
 - Converting to base unit (kg)
 - Converting from base unit
Mirrors the structure of LengthUnit.

---
### QuantityWeight (Immutable Value Object)

### Encapsulates:
 - Numeric value
 - WeightUnit

### Supports:
 - Equality comparison
 - Unit conversion
 - Addition
All conversion logic is delegated to WeightUnit.

## Architectural Responsibility

| Component        | Responsibility                          |
|------------------|------------------------------------------|
| WeightUnit       | Weight unit conversion logic             |
| QuantityWeight   | Weight behavior (equals, add, convert)   |
| LengthUnit       | Length conversion (unchanged)            |
| QuantityLength   | Length behavior (unchanged)              |
| EqualityService  | Category-safe comparison                 |

---
### Main Flow

### Equality Comparison
- Convert both values to base unit (kg)
- Compare normalized values
- Return true / false

### Unit Conversion
- Convert source value to base unit
- Convert base unit to target unit
- Return new immutable object

### Addition
- Convert both operands to base unit
- Add values
- Convert result to:
  - First operand’s unit (default)
  - Or explicitly specified target unit
- Return new immutable object

---
### Supported Operations
---

### Equality
- Same-unit equality
- Cross-unit equality (kg ↔ g ↔ lb)
- Reflexive, symmetric, transitive behavior

### Conversion
- Explicit unit-to-unit conversion
- Base-unit normalization
- Floating-point precision handling

### Addition
- Same-unit addition
- Cross-unit addition
- Explicit target unit support
- Handles zero and negative values
- Prevents logical and mathematical errors

---
### Testing Strategy
All UC1–UC8 tests remain untouched and passing.

### Test Coverage Includes:
- Kilogram-to-kilogram equality
- Gram-to-gram equality
- Pound-to-pound equality
- Cross-unit equality:
  - Kilogram ↔ Gram
  - Kilogram ↔ Pound
- Conversion accuracy
- Addition (same and different units)
- Explicit target unit conversion
- Zero and negative values
- Immutability validation
- Length vs Weight incompatibility
- HashCode consistency
All tests pass without modifying earlier use cases.

---
### Concepts Reinforced

- Multiple Measurement Categories
- Enum-Based Responsibility Assignment
- Base Unit Normalization
- Category Type Safety
- Immutability
- Thread-Safe Design
- Scalable Architecture
---
### Forward Compatibility

With UC9 implemented:
 - New weight units can be added easily
 - New measurement categories (Temperature, Volume, Time) can be introduced
 - Existing functionality remains unchanged
 - Architecture scales cleanly

---
### Conclusion
UC9 proves that the Quantity Measurement App has evolved into a multi-domain, scalable measurement system.

It demonstrates:
 - Clean separation of concerns
 - Strong object-oriented modeling
 - Extensible architecture
 - Real-world refactoring maturity
This implementation lays the foundation for future enhancements and domain expansion.

---
### 📌Repository Updates
Committed and pushed the UC9 implementation to the repository.

Code Link:[UC9-WeightMeasurement](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC9-WeightMeasurement)



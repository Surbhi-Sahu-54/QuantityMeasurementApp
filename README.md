
### Quantity Measurement App

---

UC3 – Generic Quantity Class (Applying DRY Principle)

---

### Overview
UC3 refactors the Quantity Measurement App to eliminate code duplication introduced in UC1 and UC2.

Previously, separate classes such as Feet and Inches were used to represent different units of length. While functional, this design violated the DRY (Don’t Repeat Yourself) principle because both classes contained nearly identical logic for:

  - Construction
  - Validation
  - Equality comparison UC3 introduces a single generic QuantityLength class backed by a LengthUnit enum, preserving all existing functionality while significantly improving maintainability and scalability.

---
UC3 Objectives

- Eliminate duplication caused by unit-specific classes
- Introduce a unified representation for length measurements
- Enable cross-unit equality (e.g., 1 foot == 12 inches)
- Preserve all behavior from UC1 and UC2
- Prepare the codebase for easy future extensions

  ---
Changes Introduced in UC3
- Removed
- Feet class
- Inches class
Added
 - QuantityLength – Generic class representing any length measurement
 - LengthUnit – Enum defining supported units and conversion factors

  ---
 - Design Improvements in UC3
  ---
 Generic Quantity Model
 
QuantityLength encapsulates:
 - Numeric value
 - Unit type
 - Conversion logic
 - Equality comparison
 - Each object represents value + unit together, ensuring correctness and type safety.

--- 

Enum-Based Unit Handling

--- 
The LengthUnit enum:

 - Defines supported units (FEET, INCH)
 - Stores conversion factors
 - Converts values to a common base unit
Enums eliminate magic strings and prevent invalid units at compile time.

--- 
Common Base Unit Conversion
All comparisons are performed by:

 1. Converting values to a common base unit (inches)
2. Comparing the converted values using value-based equality This enables accurate and consistent cross-unit equality.
 
 ---
 Supported Functionality
✔ Feet ↔ Feet equality
✔ Inches ↔ Inches equality
✔ Feet ↔ Inches equality
✔ Same-reference equality
✔ Null-safe comparison
✔ Type-safe equality checks

--- 
### Application Flow

---
 - User inputs two numeric values and their respective units.
 - Unit input is converted into a LengthUnit enum.
 - QuantityLength objects are created.
 - Values are internally converted to the base unit.
 - Equality is evaluated using value-based comparison.
 - Result (true / false) is displayed to the user.
---
### Testing Strategy
Test cases from UC1 and UC2 are conceptually preserved and adapted to the generic model.

### Tests Validate:

 - Same-unit equality
 - Cross-unit equality
 - Inequality for different values
 - Null handling
 - Same-reference equality
 - Different-class comparison
 This confirms that refactoring did not break existing behavior.

---
### Key Concepts Applied

### DRY Principle
 - Eliminates duplicate logic and centralizes comparison behavior.

### Polymorphism
 - Single class handles multiple unit types via enum.

### Encapsulation
 - Value and unit are bundled together.

### Abstraction
 - Conversion logic is hidden from client code.

---

### Equality Contract

- Reflexive
- Symmetric
- Transitive
- Consistent
- Null-safe

 ### Scalability
Adding a new unit requires only adding a new enum constant — no changes to equality logic.

---

### Forward Compatibility
 UC3 prepares the codebase for:
  - Adding new units with minimal changes
  - Implementing quantity arithmetic
  - Extending comparison logic
  - Scaling the system cleanly

---
📌 Repository Updates
Committed and pushed the UC3 implementation to the repository.
Code:[UC2-InchEquality](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC2-InchEquality)


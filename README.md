### Quantity Measurement App
---
### UC4 – Extended Unit Support (Yards & Centimeters)
**(Date: 20 Feb 2026)**

### Overview

- UC4 extends the generic design introduced in UC3 by adding support for two additional length units: YARDS and CENTIMETERS.
- Since UC3 introduced a unified QuantityLength class backed by the LengthUnit enum, new units can now be added without modifying the core equality logic. This demonstrates the scalability, maintainability, and extensibility of the refactored architecture.

- The system now supports seamless equality comparison across:
- Feet
- Inches
- Yards
- Centimeters

---
### UC4 Objectives
- Extend support for YARDS and CENTIMETERS
- Maintain zero code duplication
- Preserve all functionality from UC1, UC2, and UC3
- Enable full cross-unit equality comparison
- Validate scalability of enum-based design

---
### Conversion Rules Introduced
- 1 Yard = 3 Feet
- 1 Yard = 36 Inches
- 1 Centimeter = 0.393701 Inches
All conversions are internally handled using a common base unit (inches).

---
### Changes Introduced in UC4
- No Core Logic Modified
- No changes to QuantityLength
- No changes to equals() implementation
  
### Updated
LengthUnit enum extended with:
- YARDS
- CENTIMETERS
This confirms the system follows the Open-Closed Principle (open for extension, closed for modification).
---

### Supported Functionality
- Yard ↔ Yard equality
- Centimeter ↔ Centimeter equality
- Yard ↔ Feet equality
- Yard ↔ Inches equality
- Centimeter ↔ Inches equality
- Centimeter ↔ Feet equality
- Multi-unit transitive comparisons
- Same-reference equality
- Null-safe comparison
- Type-safe unit validation

---
### Application Flow
- User inputs two numeric values and their respective units.
- Unit input is converted into a LengthUnit enum.
- QuantityLength objects are created.
- Values are converted internally to the base unit (inches).
- Equality is evaluated using value-based comparison.
- Result (true / false) is displayed to the user.

---
### Example Execution
Input: Quantity(1.0, YARDS) and Quantity(3.0, FEET)
Output: Equal (true)

Input: Quantity(1.0, YARDS) and Quantity(36.0, INCHES)
Output: Equal (true)

Input: Quantity(1.0, CENTIMETERS) and Quantity(0.393701, INCHES)
Output: Equal (true)

Input: Quantity(2.0, YARDS) and Quantity(2.0, YARDS)
Output: Equal (true)

---
### Testing Strategy
All previous UC1–UC3 test cases continue to pass.

### Additional Test Coverage Includes:
- Yard-to-yard equality
- Yard-to-feet equivalence
- Yard-to-inches equivalence
- Centimeter-to-inches equivalence
- Multi-unit transitive property validation
- Invalid unit rejection
- Null unit handling
- Same-reference comparison
This confirms that the system remains stable and backward compatible.

---
### Key Concepts Applied
- Scalability
 Adding new units requires only enum modification.

- DRY Principle Validation
  No separate class created for Yards or Centimeters.

- Enum Extensibility
  Type-safe addition of new measurement units.

- Mathematical Accuracy
  Precise conversion factors ensure reliable cross-unit equality.

- Backward Compatibility
  Listing functionality remains unaffected.

- Transitive Property
  If A = B and B = C, then A = C.

- Example:
  1 Yard = 3 Feet
  3 Feet = 36 Inches
  Therefore → 1 Yard = 36 Inches

---
### Forward Compatibility
 UC4 prepares the system for:

### Adding more units (meters, kilometers, etc.)
- Implementing arithmetic operations
- Extending the measurement domain
- Scaling without architectural changes

📌 Repository Updates
Committed and pushed the UC-4 implementation to the repository.
Code Link:[UC4 – Yard Equality](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC4-YardEquality)

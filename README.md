### Quantity Measurement App
---
### UC5 – Unit-to-Unit Conversion (Same Measurement Type)
**(Date: 21 Feb 2026)**

---
### Overview
- UC5 extends the Quantity Measurement App by introducing explicit unit-to-unit conversion functionality for length measurements.
- Until UC4, the application supported value-based equality comparison across multiple units such as feet, inches, yards, and centimeters.
- UC5 enhances this design by exposing a public conversion API that allows converting a length value from a source unit to a target unit and returning the converted   numeric result.
- This use case preserves all previous functionality from UC1–UC4 while adding a robust, reusable, and validated conversion mechanism.

---
### UC5 Objectives
- Enable explicit unit-to-unit conversion
- Support conversions across:
 - Feet ↔ Inches
 - Yards ↔ Feet / Inches
 - Centimeters ↔ Inches / Feet / Yards
- Preserve mathematical correctness
- Validate invalid inputs (null, NaN, infinity)
- Maintain immutability and DRY principles
- Ensure backward compatibility with UC1–UC4

### Preconditions
- QuantityLength class exists (from UC3/UC4)
- LengthUnit enum defines conversion factors
- common base unit (INCHES) is used
- Input value is numeric and finite
- Source and target units are valid

---
### Main Flow
1. Client requests conversion using public API:
   static double convert(double value, LengthUnit source, LengthUnit target)
2. Iputs are validated:
- value must be finite
- units must be non-null
3. Value is converted to the base unit (inches)
4. Base unit value is converted to the target unit
5. Precision handling is applied
6. Converted numeric value is returned

---
### Postconditions
- Converted numeric value is returned in the target unit
- Invalid inputs throw documented exceptions
- Mathematical equivalence preserved within precision limits
- Equality logic from UC1–UC4 remains unaffected

 ---
 ### Design Enhancements in UC5
 
1. Explicit Conversion API
UC5 introduces a dedicated conversion method:
- static double convert(double value, LengthUnit source, LengthUnit target)
- This provides a clean and reusable interface for conversion.

2. Base Unit Normalization
- All conversions follow a two-step process:
   - Source unit → base unit (inches)
   - Base unit → target unit
 This guarantees consistency and simplifies extensibility.

3. Immutability & Value Semantics
- QuantityLength objects are immutable
- Conversion returns new values or objects
- Original instances remain unchanged

4. Precision Handling
- Floating-point rounding handled via epsilon tolerance
- Prevents flaky tests and rounding errors
- Ensures consistent numerical results

---
### Testing Strategy
UC5 introduces conversion-focused test cases in addition to equality tests.

### Test Scenarios Covered
 -Feet → Inches
 - Inches → Feet
 - Yards → Inches
 - Inches → Yards
 - Centimeters → Inches
 - Feet → Yards
 - Round-trip conversions (A → B → A)
 - Zero value conversion
 - Negative value conversion
 - Precision tolerance validation
 - Invalid unit handling
 -NaN and Infinity input handling
These tests validate correctness, symmetry, and robustness.

### Backward Compatibility
- UC1: Feet equality
- UC2: Feet & Inches equality
- UC3: Generic Quantity model
- UC4: Extended units (yards, centimeters)

No existing functionality is broken in UC5.

---
### Summary
- UC5 evolves the Quantity Measurement App into a complete unit conversion engine while preserving clean architecture and backward compatibility.
- It demonstrates how to safely extend functionality in a production-quality, test-driven manner.

📌 Repository Updates
Committed and pushed the UC-5 implementation to the repository.

Code Link:[UC5-Unit Conversion](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC5-UnitConversion)


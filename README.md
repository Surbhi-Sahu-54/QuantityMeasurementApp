---
QuantityMeasurementApp
---

### UC1 – Feet Equality
- This branch contains the implementation of the Feet class.
- It demonstrates how a single unit of measurement is compared with itself before comparing with other units.

---
**(Date: 17 Feb 2026)**
- Feature 1 – Define Single Unit (FEET)
- Worked on defining a single unit of measurement [FEET] and implemented equality logic.
---

### Implementation Details
- Developed a Feet class.
- Overrode equals() method from the Object class.
- Checked if both objects refer to the same memory reference (this == obj).
- Checked if the object is null or of a different type.
- Safely cast the object to Feet type.
- Compared double values using Double.compare() instead of == operator.
- Followed clean coding principles.

---
### Testing
- Wrote test cases to model real-world scenarios.
- Modified implementation based on test case results.
- Ensured correctness and reliability of equality behavior.

📌 Repository Updates
Committed and pushed the UC1 implementation to the repository.

Code:
[UC1-Feet Equality](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC1-FeetEquality)

---

### UC2 – Feet and Inches Measurement Equality

---
This use case extends UC1 to support equality checks for both Feet and Inches.
The comparison is performed separately for each unit type. This use case does not compare feet with inches — they are treated independently.

**(Date: 18 Feb 2026)***

### Objective
 - Extend equality validation to include Inches.
 - Maintain clean object-oriented equality logic.
 - Ensure full test coverage for edge cases.
---
   
### Preconditions
 - QuantityMeasurementApp class is instantiated.
 - Two numerical values (feet or inches) are hard-coded for comparison.

---
### Main Flow
 - The main method calls a static method to validate two numerical values in feet.
 - The main method calls a static method to validate two numerical values in inches.
 - These static methods internally:
    - Instantiate Feet or Inches objects.
    - Call the overridden equals() method.
- Both classes:
  - Validate input values.
  - Ensure values are numeric.
  - Compare measurements using Double.compare().
- The equality result (true / false) is returned to the user.

---
### Implementation Details

- Created a separate Inches class similar to Feet.
- Overrode equals() method in both classes.
- Implemented:
  - Reference check (this == obj)
  - null validation
  - Type checking
  - Safe casting
  - Floating-point comparison using Double.compare()
- Reduced dependency on the main method by defining separate validation methods.

---
### Example Execution

Input: 1.0 inch and 1.0 inch
Output: Equal (true)

Input: 1.0 ft and 1.0 ft
Output: Equal (true)

---
### Test Cases Implemented

- testEquality_SameValue()
- testEquality_DifferentValue()
- testEquality_NullComparison()
- testEquality_NonNumericInput()
- testEquality_SameReference()
  
Test cases ensure:
- Value-based equality
- Null safety
- Type safety
- Proper handling of floating-point values
---
### Concepts Learned
- Object Equality Contract
- Floating-point comparison best practices
- Null checking and type safety
- Encapsulation of measurement values
- Value-based equality design

  ---
  
### Design Limitation (Current Approach)
The current implementation violates the DRY (Don't Repeat Yourself) principle because:

- Feet and Inches classes contain nearly identical logic.
- Same constructor structure.
- Same equals() implementation.
- Same value validation logic. This duplication increases maintenance effort and risk of inconsistency.

---
### Suggested Improvement
A better design would involve:

- Creating a generic Quantity class.
- Introducing a unit type parameter (e.g., LengthUnit enum).
- Centralizing equality logic in one reusable implementation. This would improve scalability and maintainability for future unit conversions.
📌 Repository Updates
Committed and pushed the UC2 implementation to the repository.

Code:[UC2 – Feet and Inches Measurement Equality](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/blob/feature/UC2-InchEquality/README.md)

---

UC3 – Generic Quantity Class (Applying DRY Principle)

---
**(19-feb-20026)**
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
### 📌 Repository Updates
Committed and pushed the UC3 implementation to the repository.
Code:[UC2-InchEquality](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC2-InchEquality)

---

### Quantity Measurement App
---
### UC4 – Extended Unit Support (Yards & Centimeters)
---
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
  No Core Logic Modified
  - No changes to QuantityLength
  - No changes to equals() implementation
  Updated
   - LengthUnit enum extended with:
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

---
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
---

### Scalability
  Adding new units requires only enum modification.

### DRY Principle Validation
 No separate class created for Yards or Centimeters.

### Enum Extensibility
Type-safe addition of new measurement units.

### Mathematical Accuracy
Precise conversion factors ensure reliable cross-unit equality.

### Backward Compatibility
Existing functionality remains unaffected.

### Transitive Property
If A = B and B = C, then A = C.

### Example:
1 Yard = 3 Feet
3 Feet = 36 Inches
Therefore → 1 Yard = 36 Inches

---

### Forward Compatibility
UC4 prepares the system for:
 - Adding more units (meters, kilometers, etc.)
 - Implementing arithmetic operations
 - Extending the measurement domain
 - Scaling without architectural changes
---

📌 Repository Updates
Committed and pushed the UC-4 implementation to the repository.
Code:[UC4 – Extended Unit Support](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/blob/feature/UC4-YardEquality)

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

### 📌 Repository Updates
Committed and pushed the UC-5 implementation to the repository.

Code Link:[UC5-Unit Conversion](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC5-UnitConversion)

---
### UC6 – Addition of Two Length Units (Same Category)
**(Date: 22 Feb 2026)**

---
### Overview

UC6 extends the Quantity Measurement App by introducing addition operations between two length measurements belonging to the same measurement category (Length).

This use case builds directly on:

 - UC3 – Generic Quantity model (DRY principle)
 - UC4 – Extended unit support (Feet, Inches, Yards, Centimeters)
 - UC5 – Explicit unit-to-unit conversion API
 - UC6 enables arithmetic addition of two QuantityLength objects, even when they are expressed in different units, and returns the result in the unit of the first operand.

---
### UC6 Objectives

- Enable arithmetic addition for length quantities
- Support addition across different length units
- Preserve immutability of value objects
- Reuse centralized conversion logic (no duplication)
- Maintain mathematical correctness and precision
- Validate inputs and handle edge cases safely
---

### Supported Units

- Feet (ft)
- Inches (in)
- Yards (yd)
- Centimeters (cm)
All units belong to the same measurement category: Length.

---
### Design Principles Applied

1. Value Object Pattern
- QuantityLength represents a physical length
- Immutable: operations return new instances
- Encapsulates value + unit + behavior

2. DRY (Don’t Repeat Yourself)
- All conversions normalize via a single base unit (inches)
- UC6 reuses UC5 conversion infrastructure

3. Abstraction & Encapsulation
- Conversion logic hidden inside domain objects
- Clients interact only through clean APIs (add(), convertTo())

4. Immutability
- Original operands remain unchanged
- Prevents side effects and improves predictability

 ---
### Addition Logic (How UC6 Works)

1. Validate inputs:
- Operands must not be null
- Values must be finite numbers
- Units must be valid length units

2.Convert both operands to the base unit (inches)

3. Add the converted values

4. Convert the sum back to the unit of the first operand

5. Return a new QuantityLength object

---
## Example Scenarios

| Input            | Output |
|------------------|--------|
| 1 ft + 2 ft      | 3 ft   |
| 1 ft + 12 in     | 2 ft   |
| 12 in + 1 ft     | 24 in  |
| 1 yd + 3 ft      | 2 yd   |
| 36 in + 1 yd     | 72 in  |
| 5 ft + 0 in      | 5 ft   |
| 5 ft + (-2 ft)   | 3 ft   |

---
### Application Flow
- User provides two values with units
- Quantities are created using QuantityLength
- Addition is performed using add()
- Result is returned in the unit of the first operand
- Equality checks (UC3+) continue to work unchanged
---
  
###Test Coverage (UC6)
 The UC6 test suite validates:

### Same-Unit Addition
 - Feet + Feet
 - Inches + Inches
 
### Cross-Unit Addition
 - Feet + Inches
 - Inches + Feet
 - Yards + Feet
 - Centimeters + Inches
### Mathematical Properties
 - Commutativity: a + b = b + a
 - Identity element: adding zero
 - Negative value handling
### Precision & Accuracy
 - Floating-point tolerance (epsilon based)
 - Round-trip conversion safety
### Validation & Error Handling
 - Null operand rejection
 - Invalid input handling
 - Large and small value addition

---
### Concepts Learned in UC6

- Arithmetic operations on value objects
- Unit normalization before arithmetic
- Immutability in domain models
- Conversion reuse across features
- Floating-point precision handling
- Mathematical property validation (commutativity)
- Clean API design for domain operations

---
### Forward Compatibility
With UC6 implemented, the system is now ready for:

 - Subtraction, multiplication, division (UC7+)
 - Support for additional measurement categories
 - Mixed-category validation (length vs weight, etc.)
 - Enhanced arithmetic APIs

---
### Summary
UC6 completes the transition from simple equality checks to full-fledged arithmetic operations on quantities.

The Quantity Measurement App is now:

 - Generic
 - Scalable
 - Mathematically correct
 - Cleanly designed
 - Fully test-driven
 
 ---
### Repository Updates
Committed and pushed the UC6 implementation to the repository.

Code Link:[UC6-UnitAddition](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC6-UnitAddition)

---
### UC7 – Addition with Explicit Target Unit Specification
**(Date: 23 Feb 2026)**

---
### Overview
- UC7 extends the Quantity Measurement App by enhancing the addition operation between two length quantities.

- Unlike UC6—where the result unit defaults to the unit of the first operand—UC7 allows the caller to explicitly specify the target unit in which the addition result should be expressed.

- This improvement provides greater flexibility, clarity, and control over result representation, especially in scenarios where the desired unit differs from both operands.

---
### Example
- Adding 1 foot + 12 inches:
- Target = FEET → 2.0 FEET
- Target = INCHES → 24.0 INCHES
- Target = YARDS → ~0.667 YARDS

---
### UC7 Objectives
- Allow explicit specification of the result unit during addition
- Preserve immutability of QuantityLength objects
- Maintain backward compatibility with UC6 implicit addition
- Ensure mathematical correctness across all unit combinations
- Keep API clear, flexible, and extensible

---
### Preconditions
- QuantityLength class (from UC3–UC6) exists
- LengthUnit enum supports:
  - FEET
  - INCHES
  - YARDS
  - CENTIMETERS
- All conversion factors are defined relative to a common base unit (INCHES)
- Two valid QuantityLength operands are provided
- A valid target LengthUnit is explicitly specified
- All operands belong to the same measurement category (Length)
- All values are finite numbers (not NaN or Infinity)

---
### Main Flow

1. Client calls:
   QuantityLength.add(length1, length2, targetUnit)

2.The method validates:
  - length1 and length2 are non-null
  - targetUnit is non-null and valid
  - All values are finite numbers

3. Convert both operands to the base unit (inches)

4. Add the normalized values

5. Convert the sum from base unit to the explicitly specified target unit

6. Return a new QuantityLength instance in the target unit

---
### Postconditions
- A new QuantityLength object is returned
- Result unit is always the explicitly specified target unit
- Original operands remain unchanged (immutability preserved)
- Addition is mathematically accurate within floating-point precision
- Addition remains commutative:
- add(A, B, target) == add(B, A, target)
- Invalid inputs result in a documented exception (IllegalArgumentException)

---
### Mathematical Properties Preserved
- Commutativity: A + B = B + A
- Identity element: A + 0 = A
- Sign preservation: Negative values handled correctly
- Scale independence: Same sum, different representations

---
### Key Concepts Tested
- Explicit target unit overrides implicit defaults
- Same-target and cross-target unit correctness
- Commutativity with explicit target unit
- Zero-value addition
- Negative-value handling
- Large and small magnitude conversions
- Precision tolerance across unit scales
- Null target unit validation
- Consistency across all unit combinations

---
### Summary
UC7 enhances the Quantity Measurement App by providing a clean, flexible, and explicit addition API.

It preserves all previous use cases (UC1–UC6), strengthens API expressiveness, and demonstrates solid object-oriented design principles.

---
### 📌 Repository Updates
Committed and pushed the UC7 implementation to the repository.

CodeLink:[UC7-TargetUnitAdditon](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)

---
### UC-8 Refactoring Unit Enum to Standalone with Conversion Responsibility
**(Date: 24 Feb 2026)**

---
### Overview
UC8 refactors the Quantity Measurement App to improve architecture quality, scalability, and adherence to SOLID principles.

In UC1–UC7, unit conversion logic was embedded inside the QuantityLength class. While functional, this approach tightly coupled quantity behavior with unit knowledge, making the system harder to extend to new units or measurement categories.

UC8 resolves this by extracting all unit conversion responsibility into a standalone LengthUnit enum, making it the single source of truth for unit-related behavior.

All features from UC1 to UC7 remain fully backward compatible.

---
### UC8 Objectives

- Decouple unit conversion logic from quantity objects
- Enforce the Single Responsibility Principle (SRP)
- Improve readability and maintainability
- Enable easy scalability for future measurement categories
- Preserve existing behavior without changing client code

---
### Preconditions
- QuantityLength class exists from UC3–UC7
- Supported length units:
  - FEET
  - INCHES
  - YARDS
  - CENTIMETERS
- A consistent base unit is defined (FEET)
- Arithmetic and comparison operations are limited to the length category
- Unit tests from UC1–UC7 are already implemented

---
### Design Changes Introduced in UC8

### Removed from QuantityLength
- Embedded unit conversion logic
- Hardcoded conversion factors
- Responsibility for unit-to-unit translation

### Added / Refactored
-LengthUnit (Standalone Enum)
  - Owns all conversion logic
  - Converts:
     - Values to base unit
     - Values from base unit
 -Acts as a reusable conversion strategy

- QuantityLength

Refactored into a pure immutable value object.
Responsibilities limited to:
- Holding value and unit
- Arithmetic operations
- Equality comparison
- Delegating conversion to LengthUnit
---
## Updated Responsibilities

| Component        | Responsibility                                  |
|------------------|-----------------------------------------------|
| LengthUnit       | Unit conversion logic                          |
| QuantityLength   | Value operations (equals, add, convert)        |
| EqualityService  | Cross-unit equality comparison                 |
| App              | User interaction / execution entry point       |

---
### Main Flow
- Client creates QuantityLength(value, unit)
- Conversion requests are delegated to LengthUnit
- For arithmetic operations:
  - Convert operands to base unit
  - Perform operation in base unit
  - Convert result to target unit
A new immutable QuantityLength object is returned

---
### Supported Operations (UC1–UC8)

### Equality (UC3 / UC4)
 - Same-unit equality
 - Cross-unit equality
 - Reflexive, symmetric, and transitive behavior

### Conversion (UC5)
- Explicit unit-to-unit conversion
- Base-unit driven conversion
- NaN and Infinite value validation

### Addition – Implicit Target (UC6)
- Result unit defaults to first operand unit

### Addition – Explicit Target (UC7)
- Result unit explicitly specified by client
- Supports all valid length units

---
### Testing Strategy
UC8 introduces refactor-validation tests while retaining all existing test cases.

### Test Coverage Includes
- Conversion to base unit correctness
- Conversion from base unit correctness
- Delegation from QuantityLength to LengthUnit
- Equality consistency after refactor
- Addition and conversion regression tests
- Immutability validation
- Backward compatibility verification for UC1–UC7
All existing tests pass without modification, confirming the refactor is safe and behavior-preserving.

---
### Concepts Reinforced in UC8

### Single Responsibility Principle (SRP)
- Units manage conversion
- Quantities manage behavior

### Encapsulation
- Conversion logic hidden inside enum
- Clean and minimal public APIs

### Open–Closed Principle (OCP)
- New units added by extending enum only
- No changes required in quantity classes

### Immutability
- All operations return new objects
- Thread-safe by design

### Architectural Scalability
Pattern directly extensible to:
 - WeightUnit
 - VolumeUnit
 - TemperatureUnit

### Example Outputs

Input: Quantity(1.0, FEET).convertTo(INCHES)
Output: Quantity(12.0, INCHES)

Input: Quantity(1.0, FEET).add(Quantity(12.0, INCHES), FEET)
Output: Quantity(2.0, FEET)

Input: Quantity(1.0, FEET).add(Quantity(12.0, INCHES), YARDS)
Output: Quantity(0.667, YARDS)

Input: Quantity(36.0, INCHES).equals(Quantity(1.0, YARDS))
Output: true

---
### Forward Compatibility

With UC8 implemented:
- Adding a new unit requires only enum extension
- Adding a new measurement category requires a new unit enum
- No refactor required for QuantityLength
- System remains clean, extensible, and maintainable

---
### Conclusion
UC8 elevates the Quantity Measurement App from a functional implementation to a well-architected, scalable system.

It demonstrates:
- Strong object-oriented design
- Practical refactoring skills
- Industry-grade architectural thinking
- UC8 serves as the architectural backbone for all future enhancements.

📌 Repository Updates
Committed and pushed the UC-8 implementation to the repository. 

Code Link: [UC8-StandaloneUnit](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC8-StandaloneUnit)

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


---
### UC10 – Generic Quantity Class with Unit Interface for Multi-Category Support
**(Date: 25 Feb 2026)**

---
### Overview
UC10 is the final architectural refinement of the Quantity Measurement App.

It eliminates category-specific quantity classes and replaces them with a single, generic, type-safe Quantity implementation.

Earlier use cases (UC1–UC9) introduced separate classes such as QuantityLength and QuantityWeight.

While functional, this design resulted in:
- Code duplication
- Scalability limitations
- Higher maintenance cost

UC10 resolves these issues by introducing:

- A generic Quantity<U> class
- A common IMeasurable interface implemented by all unit enums
- Centralized logic for equality, conversion, and addition
The system now supports multiple measurement categories without code duplication.

---
### Objectives

- Replace category-specific quantity classes with a generic implementation
- Enforce type safety using Java generics
- Standardize unit behavior using a common interface
- Preserve all functionality from UC1–UC9
- Prevent invalid cross-category comparisons
- Enable effortless scalability for future categories

---
### Problems in UC9 (Why UC10 Exists)

### Duplicate Quantity Classes
- QuantityLength and QuantityWeight contained identical logic
- Bug fixes had to be repeated in multiple classes

### Duplicate Unit Patterns
- Length and Weight enums followed the same structure
- Violated the DRY principle

### Poor Scalability

Each new category required:
- New unit enum
- New quantity class
- New application logic
- New duplicated tests

### Maintenance Risk

- Logic divergence between categories
- Higher probability of inconsistent behavior

UC10 removes all of these issues.

---
### Preconditions

- UC1–UC9 functionality is complete and tested
- Length and Weight units are implemented
- Java Generics and immutability principles are applied
- Base units are defined per category

---
### Design Introduced in UC10

### IMeasurable Interface
Defines a common contract for all measurement units.

### Responsibilities:
- Provide conversion factor to base unit
- Convert values to and from base unit
- Expose unit name

--- 
## Quantity
A single generic class that replaces all category-specific quantity classes.

### Responsibilities:
- Hold value and unit
- Perform conversion
- Perform addition
- Handle equality
- Enforce category safety
- Maintain immutability

---
### Unit Enums
  ### LengthUnit
  - Base unit: FEET

  ### WeightUnit
  - Base unit: KILOGRAM

  ### Each enum:
   - Implements IMeasurable
   - Contains only conversion data
   - Provides parsing helpers for console input

---
### Main Flow
1. User selects measurement category
2. User enters values and units
3. Application creates:
  - Quantity<LengthUnit>
  - Quantity<WeightUnit>
4. Operations performed:
  - Equality
  - Conversion
  - Addition
All logic is handled by the generic Quantity class.

### Supported Operations

### Equality
- Same-unit equality
- Cross-unit equality (same category)
- Reflexive, symmetric, transitive
- Cross-category comparison returns false

### Conversion
- Any unit to any unit (within category)
- Base-unit normalization ensures correctness

### Addition
- Implicit target unit (first operand’s unit)
- Explicit target unit supported
- Returns a new immutable Quantity

---
### Type Safety & Category Protection
- Compile-time safety via generics
- Runtime checks prevent cross-category comparison
- Length and Weight cannot be compared or added

---
### Testing Strategy
UC10 introduces generic test coverage while preserving previous behavior.

### Test Coverage Includes:
- Length equality, conversion, addition
- Weight equality, conversion, addition
- Generic Quantity operations
- Cross-category prevention
- Immutability validation
- Regression validation for UC1–UC9
All tests pass using the generic Quantity class.

---
### Design Principles Demonstrated

- Single Responsibility Principle
- Open–Closed Principle
- DRY Principle
- Immutability
- Type Safety via Generics
- High Scalability

---
### Extending the System
To add a new measurement category:
 1. Create a new enum implementing IMeasurable
2. Define conversion factors
3. Use Quantity<NewUnit>

No changes required in:
 - Quantity class
 - Application logic
 - Existing tests

---
### Conclusion
UC10 transforms the Quantity Measurement App into a clean, scalable, production-ready architecture.

- Eliminates redundancy
- Centralizes logic
- Improves maintainability
- Demonstrates advanced object-oriented design and generics mastery

This use case represents the final architectural milestone of the project.

---
### 📌 Repository Updates
- UC10 implemented
- Legacy quantity classes removed
- Generic architecture active
- All tests passing
- Interview-ready design
- Committed and pushed the UC10 implementation to the repository.

Code Link: [UC10-genericQantity](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC10-GenericQuantity)

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

---
### UC12 – Subtraction and Division Operations on Quantity Measurements
**(Date: 25 Feb 2026)**

---
### Description

UC12 extends the Quantity Measurement Application by introducing two new arithmetic operations to the generic Quantity<U> class:
- Subtraction
- Division
Building on UC1–UC11 (equality, conversion, addition), this use case enables comprehensive arithmetic manipulation of measurements.

### Subtraction
Allows users to compute the difference between two quantities of the same measurement category.

 ### Examples:
 - 5 L - 2 L = 3 L
 - 10 FEET - 6 INCHES = 9.5 FEET

 ### Division
Allows users to compute the ratio between two quantities, producing a dimensionless scalar result.

### Examples:
 - 10 KG ÷ 5 KG = 2.0
 - 24 INCHES ÷ 2 FEET = 1.0
Both operations:
- Support cross-unit arithmetic (within same category)
- Support implicit and explicit target unit
- Maintain immutability
- Provide validation and error handling
- Preserve SOLID principles and architectural consistency
UC12 proves that the generic Quantity<U> design scales without structural changes.

### Preconditions
- Quantity<U extends IMeasurable> is fully operational (UC10)
- IMeasurable defines conversion contract
- LengthUnit, WeightUnit, VolumeUnit implement IMeasurable
- UC1–UC11 functionality remains intact
- Subtraction returns Quantity<U>
- Division returns double
- Cross-category arithmetic is prevented

---
### Main Flow
---
### Subtraction Operation

### Method Signatures
Quantity<U> subtract(Quantity<U> other)
Quantity<U> subtract(Quantity<U> other, U targetUnit)

### Validation
- other is non-null
- Same measurement category
- Values are finite
- Target unit (if provided) is non-null

### Conversion to Base Unit
- Convert both quantities to base unit
- Perform subtraction:
  baseResult = this.baseValue - other.baseValue

### Convert to Target Unit
- Default: first operand's unit
- Or explicitly provided unit
- Round to two decimal places

### Return New Object
- Return new immutable Quantity<U>
- Original objects remain unchanged

### Result Meaning
- Positive → first operand larger
- Negative → second operand larger
- Zero → quantities equivalent

---
### Division Operation

### Method Signature
  double divide(Quantity<U> other)

### Validation
- other is non-null
- Same measurement category
- Finite numeric values
- Divisor not zero

### Conversion to Base Unit
 result = this.baseValue / other.baseValue

### Return Scalar
- Return double
- Dimensionless result

### Result Meaning
- 1 → first larger
- < 1 → second larger
- = 1 → equal

---
### Postconditions
- Subtraction returns new Quantity<U>
- Division returns scalar double
- Immutability preserved
- Cross-category operations prevented
- Works for Length, Weight, Volume
- Addition, subtraction, division coexist
- Mathematical properties respected:
 - Subtraction → non-commutative
 - Division → non-commutative & non-associative

--- 
### Testing Strategy
Comprehensive unit tests cover:

### Subtraction Tests
- Same-unit subtraction
- Cross-unit subtraction
- Explicit target unit
- Negative result
- Zero result
- Identity property
- Non-commutativity
- Large and small values
- Null operand handling
- Null target handling
- Cross-category prevention
- Chained operations
- Immutability validation
- Precision & rounding

---
### Division Tests
- Same-unit division
- Cross-unit division
- Ratio > 1
- Ratio < 1
- Ratio = 1
- Non-commutativity
- Non-associativity
- Division by zero
- Large ratios
- Small ratios
- Null operand handling
- Cross-category prevention
- Immutability validation
- Precision handling

---
### Concepts Reinforced

### Comprehensive Arithmetic Support
System evolves from comparison to full arithmetic domain model.

### Immutability
All arithmetic returns new objects.

### Non-Commutativity Awareness
Subtraction and division are order dependent.

### Division by Zero Protection
Fail-fast validation strategy.

### Target Unit Pattern
Consistent implicit & explicit unit handling.

### Cross-Category Type Safety
Compile-time generics + runtime validation.

### Code Reuse
Private helper methods prevent duplication.

### Validation Consistency
Uniform validation across all operations.

### Precision Handling
Subtraction → rounded
Division → raw double

### Polymorphism
Generic methods operate across all categories.

---
### SOLID & Object Calisthenics Review

### Single Responsibility
Quantity handles arithmetic logic only.

### Open–Closed Principle
New operations added without breaking architecture.

### DRY Principle
Shared validation & conversion logic reused.

### Possible Improvement
If arithmetic logic grows further, consider:
 - Strategy pattern for operations
 - Extract ArithmeticService
Current design remains clean and maintainable.

---
### Example Outputs
### Subtraction (Implicit Target)
 10 FEET - 6 INCHES → 9.5 FEET
 10 KG - 5000 G → 5 KG
 5 L - 500 mL → 4.5 L

### Subtraction (Explicit Target)
 10 FEET - 6 INCHES (INCHES) → 114 INCHES
 5 L - 2 L (mL) → 3000 mL

### Division
 10 FEET ÷ 2 FEET → 5.0
 24 INCHES ÷ 2 FEET → 1.0
 2000 G ÷ 1 KG → 2.0
 5 L ÷ 10 L → 0.5

### Error Cases
 subtract(null) → IllegalArgumentException
 divide(0) → ArithmeticException
 cross-category operation → IllegalArgumentException

---
### Conclusion
UC12 transforms the system into a complete arithmetic measurement engine.

- Supports addition, subtraction, division
- Maintains immutability
- Preserves type safety
- Scales across all measurement categories
- Requires no architectural restructuring

The Quantity Measurement App is now:
- Generic
- Extensible
- Architecturally mature
- Production-ready
- Interview-ready

---
### 📌 Repository Updates
- UC12 implemented
- Subtraction added
- Division added
- All categories supported
- All tests passing
- Backward compatibility preserved
- Committed and pushed the UC-12 implementation to the repository.

Code Link: -[UC-12Quantity Substraction and Division](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC12--QuantitySubtractionandDivision)


---
### UC13 – Centralized Arithmetic Logic to Enforce DRY in Quantity Operations
---
**(Date: 23 Feb 2026)**

### Description
UC13 refactors the arithmetic operations introduced in UC12 (add, subtract, divide) to eliminate code duplication and enforce the DRY (Don't Repeat Yourself) principle.

Instead of repeating validation, conversion, and base-unit normalization logic across multiple methods, UC13 introduces a centralized private helper method that encapsulates all shared arithmetic behavior.

Public API remains unchanged
All UC12 tests pass without modification
Behavior remains 100% identical
Internal implementation is optimized

---
### Problems in UC12 Implementation
UC12 implemented arithmetic operations directly inside each method. This caused:

### Code Duplication
- Same null checks repeated
- Same category validation repeated
- Same base-unit conversion repeated
- Same finiteness checks repeated
- Same target unit validation repeated

Future operations (multiply, modulo, etc.) would multiply duplication.

---
### DRY Principle Violation
- Validation logic copied across methods
- Error messages not centralized
- Changes required in multiple places
- Risk of inconsistent behavior

---
### Reduced Readability
Each arithmetic method looked like:

- Validation boilerplate
- Conversion boilerplate
- Actual arithmetic logic hidden at bottom
Intent was buried in repetitive code.

---
### Scalability Issue
Adding new arithmetic operations would:

- Duplicate validation again
- Duplicate conversion again
- Increase maintenance complexity

---
### Goal of UC13
- Centralize validation logic
- Centralize base-unit arithmetic logic
- Remove duplication
- Keep public API unchanged
- Preserve all UC12 behavior
- Improve maintainability
- Enable easy future extensions

---
### Refactoring Strategy
---
### Step 1 – Create ArithmeticOperation Enum
Two approaches supported:

Approach 1 – Abstract Method (Clean & Scalable)
Each enum constant implements:

       compute(double left, double right)

Example operations:

- ADD
- SUBTRACT
- DIVIDE
- MULTIPLY (future-ready)
This keeps operation logic tied directly to enum constants.
---
### Approach 2 – Lambda Expression (Modern & Concise)
Uses DoubleBinaryOperator.

Each enum constant defines:

      (a, b) -> a + b
Cleaner for small logic, less ideal for complex logic.

--- 
### Step 2 – Centralized Validation Helper

### Private Method:
  validateArithmeticOperands(Quantity<U> other, U targetUnit, boolean targetUnitRequired)

### Validates:
- Null operand
- Cross-category units
- Finiteness (NaN, Infinity)
- Target unit (if required)
All validation logic now lives in ONE place.

---
### Step 3 – Core Arithmetic Helper

### Private Method:
    performBaseArithmetic(Quantity<U> other, ArithmeticOperation operation)

### Responsibilities:
- Convert this to base unit
- Convert other to base unit
- Perform operation using enum
- Return base-unit result
No validation duplication. No conversion duplication.

---
### Step 4 – Refactor Public Methods

### add()
- Calls validation helper
- Calls performBaseArithmetic(ADD)
- Converts result to target unit
- Returns new Quantity

### subtract()
- Same flow
- Uses SUBTRACT enum

### divide()
- Calls helper with DIVIDE
- Returns dimensionless scalar
Public signatures unchanged.
---

### Internal Flow Example
q1.subtract(q2, FEET)

↓
validateArithmeticOperands(q2, FEET, true)

↓
performBaseArithmetic(q2, SUBTRACT)

↓
SUBTRACT.compute(base1, base2)

↓
Convert result to FEET

↓
Return new Quantity<>(value, FEET)


---
### Postconditions
- All arithmetic operations delegate to centralized helper
- Validation logic written once
- Conversion logic written once
- No duplicated code remains
- All UC12 tests pass
- Public API unchanged
- DRY principle enforced
- Maintainability improved
- Future operations easy to add

---
### Concepts Learned

 ### DRY Principle
common logic extracted into helper methods.

### Lambda Expressions
Anonymous functions:

(a, b) -> a + b
Introduced in Java 8 for functional programming.

### Functional Interface
DoubleBinaryOperator

Takes:

double, double → returns double
Compiler matches lambda automatically.

### Enum-Based Operation Dispatch
Cleaner than:

if-else chains
switch statements
Type-safe and extensible.

### Separation of Concerns
Public methods:

### Maintain API consistency
Private helpers:

- Handle validation
- Handle conversion
- Handle computation

### Encapsulation
Helper methods are private. Implementation hidden from users.

### Refactoring Without Behavior Change
Internal improvement. External behavior identical. Regression-free refactor.

### Key Test Coverage
### Validation Consistency
- Null operand rejected
- Cross-category rejected
- Non-finite values rejected

### Enum Dispatch
- ADD computes correctly
- SUBTRACT computes correctly
- DIVIDE computes correctly

### Backward Compatibility
- All UC12 tests pass unchanged

### Rounding Behavior
- Add/Subtract rounded to 2 decimals
- Divide returns raw double

### Immutability
- Original objects unchanged
- New objects returned
### Helper Delegation
- add → ADD enum
- subtract → SUBTRACT enum
- divide → DIVIDE enum

### DRY Enforcement
- Validation appears once
- Conversion appears once
---
### Scalability Proof
To add multiplication:

- Add MULTIPLY in enum
- Public multiply() calls helper
- No validation duplication
- No conversion duplication
System scales cleanly.

---
### Conclusion
UC13 transforms the arithmetic implementation into:

- Cleaner
- More maintainable
- DRY-compliant
- Future-proof
- Architecturally strong
It is a pure refactoring use case: Improved design without changing behavior.

---
### 📌 Repository Updates
- UC13 Implemented
- Code duplication removed
- DRY enforced
- All UC12 tests passing
- Public API unchanged
- Architecture significantly improved
- Committed and pushed the UC-13 implementation to the repository.

Code Link[UC13- CentralizedArithmaticLogic](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC13-CentralizedArithmeticLogic)

---

### UC14 – Temperature Measurement with Selective Arithmetic Support & IMeasurable Refactoring

**Date:** 23 Feb 2026

---

## Description

UC14 extends the **Quantity Measurement Application** to support **Temperature** alongside:

- Length  
- Weight  
- Volume  

Unlike these categories, **temperature has fundamentally different arithmetic rules**:

| Operation | Temperature Support |
|-----------|--------------------|
| Equality | ✅ Supported |
| Conversion | ✅ Supported |
| Addition | ❌ Not meaningful (absolute temperatures) |
| Subtraction | ❌ Not supported (in this design) |
| Division | ❌ Meaningless |

### Example

```
100°C + 50°C ≠ 150°C (not physically meaningful)
100°C ÷ 50°C → meaningless
```

This exposes a limitation in the previous design:

> The system assumed all measurement categories support the same arithmetic operations.

UC14 refactors the design to make **arithmetic support optional and capability-based**.

---

# Design Limitations Identified

## IMeasurable Assumed Uniform Behavior

- No way to express unsupported operations  
- Forced temperature to implement meaningless arithmetic  
- Violates **Interface Segregation Principle (ISP)**  

---

## Quantity Assumed All Units Support Arithmetic

```
new Quantity<>(50, CELSIUS).divide(...)
```

- Compiles successfully  
- Fails only at runtime  

**Problems**

- No compile-time communication of constraints.

---

## No Operation Capability Query

Cannot ask:

> "Does this unit support division?"

- Must rely on documentation  
- Unsafe API design  

---

# Objective of UC14

- Add **Temperature category**
- Support **conversion & equality only**
- Prevent unsupported arithmetic
- Maintain backward compatibility (**UC1–UC13**)
- Refactor **IMeasurable** safely
- Preserve **generics and type safety**
- Follow **SOLID principles**

---

# Refactoring Strategy

## Step 1 – Refactor IMeasurable Interface

Introduce **optional arithmetic support via default methods**.

### Add Functional Interface

```java
@FunctionalInterface
public interface SupportsArithmetic {
    boolean isSupported();
}
```

### Default Lambda (Backward Compatibility)

```java
SupportsArithmetic supportsArithmetic = () -> true;
```

All existing units inherit **true**.

### Default Methods Added

```java
default boolean supportsArithmetic() {
    return supportsArithmetic.isSupported();
}

default void validateOperationSupport(String operation) {
    // default: allow all operations
}
```

- Existing enums remain unchanged  
- No breaking changes introduced  

---

## Step 2 – Create TemperatureUnit Enum

Supports:

- CELSIUS
- FAHRENHEIT
- KELVIN

**Celsius treated as base unit internally.**

---

### Non-Linear Conversion

Temperature conversion is **NOT**

```
value × factor
```

Instead:

```
°F = (°C × 9/5) + 32
°C = (°F - 32) × 5/9
K = °C + 273.15
```

This requires **special conversion handling**.

---

### Disable Arithmetic Support

```java
SupportsArithmetic supportsArithmetic = () -> false;
```

Override validation:

```java
@Override
public void validateOperationSupport(String operation) {
    throw new UnsupportedOperationException(
        "Temperature does not support " + operation + " operation."
    );
}
```

---

## Step 3 – Update Quantity<U>

Before any arithmetic:

```java
this.unit.validateOperationSupport(operation.name());
```

If unsupported → **fail fast with meaningful exception**

- No structural changes to generics.

---

# Example Behavior

## Temperature Equality

```java
new Quantity<>(0.0, CELSIUS)
.equals(new Quantity<>(32.0, FAHRENHEIT))
→ true

new Quantity<>(273.15, KELVIN)
.equals(new Quantity<>(0.0, CELSIUS))
→ true
```

---

## Temperature Conversion

- 100°C → 212°F  
- 32°F → 0°C  
- 0°C → 273.15K  
- -40°C → -40°F  

---

## Unsupported Operations

```java
new Quantity<>(100.0, CELSIUS)
.add(new Quantity<>(50.0, CELSIUS))
```

Throws:

```
UnsupportedOperationException:
Temperature does not support ADD operation.
```

Same for:

- subtract()
- divide()

---

## Cross-Category Safety

```java
new Quantity<>(100, CELSIUS)
.equals(new Quantity<>(100, FEET))
```

→ **false**

Runtime check:

```
unit.getClass() comparison
```

Compile-time safety:

```
Quantity<TemperatureUnit> ≠ Quantity<LengthUnit>
```

**Layered type protection.**

---

# Concepts Reinforced

## Interface Segregation Principle (ISP)

Temperature should **not be forced** to implement unsupported arithmetic.

---

## Default Methods in Interfaces

- Evolve interface without breaking clients  
- Maintain backward compatibility  
- Powerful **Java 8 feature**

---

## Capability-Based Design

Instead of assuming operations:

> Ask unit if operation is supported.

Safer & extensible.

---

## Non-Linear Conversions

Unlike **length / weight**:

Temperature requires **formula-based conversion**.

---

## Absolute vs Relative Temperature

**Absolute temperature**

```
100°C → specific point
```

**Relative temperature**

```
Increase by 10°C → meaningful
```

This design prevents **invalid arithmetic on absolute values**.

---

## Polymorphic Error Handling

Temperature provides **specific error message**.

Better than generic:

```
IllegalArgumentException
```

---

# Backward Compatibility

Length, Weight, Volume:

- No changes required  
- Default arithmetic support = true  
- All **UC1–UC13 tests pass**

---

# Key Test Coverage

## Equality

- Celsius–Celsius  
- Fahrenheit–Fahrenheit  
- Kelvin–Kelvin  
- Cross-unit equality  
- Reflexive, symmetric, transitive  

---

## Conversion

- All pair combinations  
- Round-trip conversion  
- Absolute zero edge case  
- -40°C = -40°F special case  
- Precision with epsilon  

---

## Unsupported Operations

- add() throws  
- subtract() throws  
- divide() throws  
- Error message clarity  

---

## Cross Category

- Temperature vs Length → false  
- Temperature vs Weight → false  
- Temperature vs Volume → false  

---

## Default Behavior

- LengthUnit supports arithmetic  
- WeightUnit supports arithmetic  
- VolumeUnit supports arithmetic  

---

## Backward Compatibility

All **UC1–UC13 tests pass unchanged**

---

# Architectural Evolution

| UC | Focus |
|----|------|
| UC1–5 | Equality & conversion |
| UC6–9 | Addition |
| UC10 | Generic design |
| UC11 | Volume scalability proof |
| UC12 | Arithmetic operations |
| UC13 | DRY refactoring |
| UC14 | Capability-based design & ISP compliance |

UC14 makes the **system intellectually complete**.

---

# Final Outcome

After UC14, the system is:

- Generic  
- Scalable  
- DRY-compliant  
- ISP-compliant  
- Backward compatible  
- Capability-aware  
- Architecturally robust  

The design now supports **categories with different operational constraints** without hacks, duplication, or breaking changes.

---

# 📌 Repository Updates

- TemperatureUnit implemented  
- IMeasurable refactored safely  
- Arithmetic capability optional  
- All previous tests passing  
- Temperature tests passing  
- System evolution validated  

Committed and pushed the UC14 implementation to the repository.

**Code Link:** [UC14-TemperatureMeasurement](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC14-QuantitySubstractionandDivision)






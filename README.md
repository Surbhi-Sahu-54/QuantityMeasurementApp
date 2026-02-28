### Quantity Measurement App

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

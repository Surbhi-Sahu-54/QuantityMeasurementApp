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


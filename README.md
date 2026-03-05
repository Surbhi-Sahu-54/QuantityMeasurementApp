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

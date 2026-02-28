### Quantity Measurement App
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
📌 Repository Updates
Committed and pushed the UC7 implementation to the repository.

---
CodeLink:[UC7-TargetUnitAdditon](https://github.com/Surbhi-Sahu-54/QuantityMeasurementApp/tree/feature/UC7-TargetUnitAddition)

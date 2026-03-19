# Design Evaluation – PriceCalculator

## Overview

The original `PriceCalculator` class uses a chain of `if` statements inside `calculatePrice()` to handle each customer type. While functional, this design creates serious maintainability and extensibility problems as the system grows.

---

## Issue 1: Open/Closed Principle Violation

The most significant problem is that the class is not open for extension without being modified. Every time a new customer type needs to be supported — for example, a "STUDENT" or "EMPLOYEE" type — a developer must go into `calculatePrice()` and add another `if` block. This risks introducing bugs into discount logic that was previously working correctly. A well-designed system should allow new behaviors to be added without touching existing, tested code.

---

## Issue 2: Growing Complexity Over Time

With only four customer types the method is already a series of repeated conditional checks. If the system grows to support ten or twenty types, this method becomes extremely long and difficult to read. Debugging or auditing a specific discount requires scanning through all the other conditions as well.

---

## Issue 3: All Pricing Logic Is Centralized in One Place

Each customer type's discount rule is embedded directly in the `calculatePrice()` method rather than being encapsulated in its own class. This makes it impossible to reuse, override, or test individual discount behaviors in isolation. For example, you cannot unit test the MEMBER discount without also running through the other conditions.

---

## Issue 4: No Polymorphism

The design relies entirely on string comparison rather than taking advantage of Java's polymorphism. Passing `"MEMBER"` as a string is fragile — a typo like `"member"` would silently return the wrong price. Using distinct classes that implement a common interface eliminates this class of bug entirely.

---

## Solution

These problems are solved by applying the **Strategy Pattern**, which moves each pricing rule into its own class that implements a common `PricingStrategy` interface. The `PriceCalculator` then works with any strategy through the interface, making it easy to add new types by simply creating a new class — without modifying any existing code.

# Design Evaluation – OrderProcessor

## Overview

The `OrderProcessor` class contains several significant object-oriented design issues that make it difficult to maintain, test, and extend over time.

---

## Issue 1: Poor Encapsulation

All four fields — `customerName`, `email`, `item`, and `price` — are declared `public`. This violates one of the most fundamental principles of object-oriented design: data should be hidden inside a class and accessed only through controlled methods. When fields are public, any other part of the program can read or modify them without restriction, making it impossible to enforce rules or validate data. These fields should be `private` with getter and setter methods.

---

## Issue 2: A Single Method Doing Too Many Things (Violation of Single Responsibility)

The `processOrder()` method does at least five unrelated things:
1. Calculates tax
2. Prints a receipt to the console
3. Saves the order to a file
4. Sends a confirmation email
5. Logs activity

This is a violation of what Arthur Riel calls the idea that a class should have a single, well-defined purpose. When one method is responsible for so many different concerns, a bug in the file-writing logic could accidentally break the email notification logic, and changes to how receipts are printed require touching the same method that also handles file I/O.

---

## Issue 3: Responsibilities Belong to Other Classes

The `OrderProcessor` class is performing responsibilities that belong to other objects. For example:
- Tax calculation logic belongs in a `TaxCalculator` or `PriceCalculator` class.
- Writing to a file belongs in an `OrderRepository` or `OrderStorage` class.
- Sending an email belongs in a `NotificationService` class.
- Logging belongs in a `Logger` class.

By cramming all of this into one class, the design becomes tightly coupled. Any change to the file format, the email system, or the tax rules requires modifying the same class, increasing the risk of breaking unrelated functionality.

---

## Issue 4: Discount Applied After the Receipt Is Printed

The discount logic (`if (price > 500) total = total * 0.9`) is applied *after* the receipt has already been printed and the order has been saved to the file. This is a logical bug caused by poor organization of responsibilities. The discount should be calculated before any output is produced.

---

## Issue 5: Low Cohesion

Arthur Riel's heuristics suggest that a well-designed class should be highly cohesive — meaning all its methods and data should relate to a single purpose. `OrderProcessor` mixes data storage (fields for customer info), business logic (tax and discount), I/O operations (file writing, console printing), and messaging (email). This low cohesion makes the class hard to reuse and hard to test in isolation.

---

## Summary

The `OrderProcessor` class is a classic example of a "God Class" — it knows too much and does too much. A better design would distribute its responsibilities across multiple focused classes, each with a clear single purpose, communicating through well-defined interfaces.

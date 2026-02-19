# REFLECTION_A3.md
## CSCI 363 – Assignment 3 Reflection
### Comparing Assignment 2 and Assignment 3 ETL Pipeline Implementations

---

## Overview

Assignment 2 implemented the entire ETL pipeline as a single Java class (`ETLPipeline`) with one `main` method and one private helper method (`printSummary`). All logic — file reading, data validation, transformation, and file writing — lived inside `main`. Assignment 3 redesigns this into five distinct classes, each with a single clear responsibility. Both programs produce identical outputs for the same inputs.

---

## What Is Different About the Design?

**Assignment 2** had a monolithic structure:
- One class, one `main` method (~100 lines)
- File I/O, parsing, transformation rules, and output all interleaved
- No separation of concerns; changing one part risked breaking another

**Assignment 3** decomposes the same logic into five classes:

| Class | Responsibility |
|---|---|
| `ETLPipeline` | Orchestrates the pipeline; coordinates the three phases |
| `CSVReader` | Extract: reads and parses the CSV input file |
| `ProductTransformer` | Transform: applies all business rules to a product |
| `CSVWriter` | Load: writes transformed data to the output CSV |
| `Product` | Data model: encapsulates a single product's fields |

Each class has a focused job. If the transformation rules change, only `ProductTransformer` needs to be modified. If the input format changes, only `CSVReader` changes. This is the **Single Responsibility Principle** in practice.

---

## How Is Assignment 3 More Object-Oriented?

### Objects and Classes
Assignment 3 introduces a `Product` **class** to represent the core domain concept. Instead of scattering individual variables (`idStr`, `name`, `priceStr`, `category`) across a method, each record becomes a `Product` **object** with its own state. This matches the OO idea that data and the operations on it should be grouped together.

### Encapsulation
The `Product` class hides its internal fields (declared `private`) and exposes them only through public getters and setters. External code cannot directly touch `productId`, `name`, `price`, or `category` — they must go through the defined interface. Similarly, `CSVReader` hides its internal counters (`rowsRead`, `rowsSkipped`) and exposes them only through getter methods. This is **encapsulation** in action.

### Polymorphism (Design Readiness)
While Assignment 3 does not require polymorphism for the current requirements, the design is structured to support it naturally. `ProductTransformer` could be extracted into an interface (e.g., `Transformer`) with multiple implementations — for example, a `DiscountTransformer` and a `CategoryTransformer` applied in sequence. `CSVReader` and `CSVWriter` could similarly implement `DataSource` and `DataSink` interfaces, allowing the pipeline to work with JSON, XML, or database sources with no changes to `ETLPipeline`. The class decomposition makes polymorphic extension straightforward.

### Inheritance (Design Readiness)
If future assignments required reading from multiple file types, a base `AbstractReader` class could define the shared contract (file existence check, row counting) while concrete subclasses (`CSVReader`, `JSONReader`) override the parsing logic. The current design makes this extension natural without rewriting the pipeline.

---

## OO Concepts Used

- **Object**: Each row in the CSV becomes a `Product` object with its own state and identity.
- **Class**: Four supporting classes (`CSVReader`, `ProductTransformer`, `CSVWriter`, `Product`) define the blueprints for the pipeline's components.
- **Encapsulation**: `Product` fields are private; state is accessed only through getters/setters. `CSVReader` counters are private and exposed only via getters.
- **Inheritance**: Not explicitly implemented but the design is structured to support it cleanly.
- **Polymorphism**: Not explicitly implemented in this assignment but the class interfaces are designed to allow it naturally in future extensions.

---

## Testing: Confirming Assignment 3 Matches Assignment 2

To verify that Assignment 3 produces exactly the same output as Assignment 2, the following tests were run:

**Test 1 – Normal input (`products.csv` with valid and invalid rows):**
Both A2 and A3 were run against the same `products.csv`. The `transformed_products.csv` files produced were compared line-by-line. Results were identical: same rows, same order, same price values (confirming rounding behavior is preserved), same category reassignments (e.g., Electronics > $500 → "Premium Electronics"), same PriceRange labels.

**Test 2 – Missing input file:**
The input file was renamed/removed. Both A2 and A3 printed `ERROR: Input file not found at data/products.csv` and exited without creating or corrupting the output file. Behavior was identical.

**Test 3 – Empty input file (header only):**
A file containing only the header row (no data rows) was used. Both A2 and A3 wrote only the output header and printed `Rows read: 0, Rows transformed: 0, Rows skipped: 0`. Behavior was identical.

**Test 4 – Malformed rows:**
Rows with missing fields, extra commas, and non-numeric prices were included. Both A2 and A3 skipped those rows, incremented the skipped counter correctly, and continued processing valid rows. Behavior was identical.

The consistent output across all four test cases confirms that the OO refactoring in Assignment 3 preserves the exact behavior of Assignment 2 while improving the structure and maintainability of the code.

---

## Summary

Assignment 3 is a meaningful improvement in design. The monolithic `main` method of A2 has been replaced by a pipeline of collaborating objects, each responsible for one concern. The code is easier to read, easier to test in isolation, and far easier to extend. The use of `Product` as a first-class object, private fields with controlled access, and single-responsibility classes all demonstrate core OO principles that make the codebase more professional and maintainable.

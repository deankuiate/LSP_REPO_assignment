# AI_PROMPTS_A3.md
## CSCI 363 – Assignment 3: AI Interaction Transcript

This file documents the prompts submitted to the AI assistant (Claude) and summarizes the responses received. Prompts were used to brainstorm OO design, generate starter code, and produce Javadocs. All suggestions were reviewed, adapted, and edited before inclusion in the final submission.

---

### Prompt 1
**Prompt:**
> "I have an Assignment 2 ETL pipeline written as a single Java class. The whole thing is in `main`. How would you redesign it to be more object-oriented? What classes would you create and why?"

**Summary of Response:**
The AI suggested decomposing the pipeline into classes aligned with the ETL phases: a `CSVReader` for extraction, a `ProductTransformer` for transformation, a `CSVWriter` for loading, and a `Product` data model class. It explained that grouping related responsibilities into separate classes improves maintainability, testability, and readability. The AI described the Single Responsibility Principle as the guiding idea — each class should have one reason to change.

**How I Used It:**
This gave me the overall class structure. I kept the four-class breakdown (Reader, Transformer, Writer, Product) and added `ETLPipeline` as the orchestrator. I adapted the suggestion by keeping the same field names and method logic as A2 to ensure identical behavior.

---

### Prompt 2
**Prompt:**
> "Here is my Assignment 2 ETLPipeline.java. Can you help me write the `Product` class? It should hold ProductID, Name, Price, and Category as private fields with getters and setters. Include Javadocs."

**Summary of Response:**
The AI generated a `Product` class with private fields, a constructor accepting all four fields, and public getters and setters for each. It included Javadoc comments on the class, constructor, and each method explaining the parameter types and return values.

**How I Used It:**
I reviewed the generated Javadocs for accuracy and adjusted the descriptions to be more specific to the ETL context (e.g., clarifying that `productId` is parsed from the CSV integer field). The field types (`int`, `String`, `BigDecimal`) matched my A2 code exactly, so no logic changes were needed.

---

### Prompt 3
**Prompt:**
> "How should I design the `CSVReader` class? It needs to read the CSV, skip malformed rows, and track `rowsRead` and `rowsSkipped`. Show me the class with Javadocs."

**Summary of Response:**
The AI produced a `CSVReader` class that opens the file with a `BufferedReader`, reads and discards the header, then iterates through data rows. It showed how to skip blank lines, lines with the wrong number of comma-separated fields, and lines that throw `NumberFormatException`. It stored counters as private instance variables exposed via `getRowsRead()` and `getRowsSkipped()` getter methods.

**How I Used It:**
The structure closely matched what I needed. I reviewed the skip conditions carefully against my A2 logic to make sure they were identical (same field count check, same `NumberFormatException` catch, same blank-line handling). I added an explicit comment for the "empty file" edge case (null header line) to match the behavior in A2.

---

### Prompt 4
**Prompt:**
> "Write a `ProductTransformer` class that applies the same business rules as my A2 code: uppercase name, 10% discount for Electronics, round to 2 decimal places HALF_UP, upgrade category to 'Premium Electronics' if price > 500, and compute a PriceRange string. Include Javadocs."

**Summary of Response:**
The AI wrote a `ProductTransformer` with a single public `transform(Product product)` method that mutates the product in place and returns the computed `priceRange` string. It extracted `computePriceRange` as a private helper method. The transformation order matched: name → discount → round → category upgrade → price range.

**How I Used It:**
I verified the transformation order matched A2 exactly (particularly that rounding happens before the $500 category check). I also confirmed the `BigDecimal` threshold comparisons used `compareTo` with exact string literals ("0.90", "500.00") matching A2. The generated code was accurate and required minimal editing beyond Javadoc review.

---

### Prompt 5
**Prompt:**
> "Now write the `CSVWriter` class that writes a header row and then each product with its PriceRange to a CSV output file. Include Javadocs."

**Summary of Response:**
The AI produced a `CSVWriter` with a `writeProducts(List<Product> products, List<String> priceRanges)` method. It used a parallel list for price ranges (one `String` per `Product`) and wrote each row using `BufferedWriter`. The header matched: `ProductID,Name,Price,Category,PriceRange`.

**How I Used It:**
The parallel-list approach (products + priceRanges) was one the AI suggested as the simplest way to avoid storing the price range inside the `Product` class itself (keeping `Product` as a pure data model). I reviewed this design choice and agreed it was appropriate for this assignment's scope. I kept the output format identical to A2 (`price.toString()` not `toPlainString()` to preserve A2 behavior).

---

### Prompt 6
**Prompt:**
> "Help me write a Reflection comparing my A2 (single-class) and A3 (multi-class OO) designs. It should discuss: what's different, how A3 is more OO, which OO concepts were used (object, class, encapsulation, inheritance, polymorphism), and how I tested to confirm identical behavior."

**Summary of Response:**
The AI drafted a structured reflection covering all four required topics. It provided a table comparing classes, explanations of encapsulation (private fields + getters/setters) and object usage, and a note that inheritance and polymorphism were designed for but not required by this assignment. It suggested four test scenarios: normal input, missing file, empty file, and malformed rows.

**How I Used It:**
I used the AI draft as a starting point and significantly rewrote it in my own voice. I added specific references to my own class names and the exact test cases I ran. I removed some generic OO theory the AI included and replaced it with specific examples from my code (e.g., citing `CSVReader.rowsSkipped` as a concrete encapsulation example).

CSCI 363 – Assignment 3 Reflection
Object-Oriented Redesign of the ETL Pipeline

Overview

In Assignment 2, the ETL pipeline was implemented almost entirely inside a single Java class called ETLPipeline. While the program met all functional requirements and produced the correct output, the structure was not very flexible or easy to maintain. Most of the logic lived inside the main method, which handled reading the input file, validating data, transforming records, writing the output file, and printing summary information.

For Assignment 3, the goal was to redesign this solution using object-oriented principles while keeping the program’s behavior exactly the same. The input files, output files, transformations, error handling, and relative paths were all preserved. The main change was how the code was organized.

What Is Different About the Design?

The biggest difference between Assignment 2 and Assignment 3 is the level of decomposition.

In Assignment 2, the entire pipeline was implemented in one class. File I/O, parsing, transformation rules, and output logic were all mixed together. As the program grew, this made the code harder to read and harder to reason about. Any small change required digging through a large main method.

In Assignment 3, the same logic is split into multiple classes, each with a clear responsibility.

ETLPipeline is responsible for coordinating the overall flow of the program.
CSVReader handles reading and parsing the input CSV file.
ProductTransformer applies all validation and transformation rules.
CSVWriter writes the transformed data to the output CSV file.
Product represents a single product record.

This separation makes the program easier to understand and easier to modify. Each class focuses on one task, and the overall pipeline reads more clearly as a sequence of steps: read, transform, and write.

How Assignment 3 Is More Object-Oriented

Assignment 3 is more object-oriented because it models the problem domain more naturally.

Instead of treating each row as a set of loosely related variables, each row from the input file is represented as a Product object. This keeps related data together and makes the code easier to follow. Operations that work with products now operate on a single object rather than multiple independent values.

Encapsulation is also used throughout the redesign. All fields in the Product class are private and can only be accessed or modified through public getter and setter methods. This protects the internal state of the object and prevents unintended changes. The CSVReader class also encapsulates its internal counters for rows read and rows skipped and exposes them only through getter methods.

While inheritance and polymorphism are not explicitly used in this assignment, the design supports them naturally. For example, the transformation logic could be expanded into multiple transformer implementations, or different readers could be added without changing the pipeline structure. The class-based design makes these kinds of extensions possible without major rewrites.

Object-Oriented Concepts Used

Object
Each product from the input file is represented as a Product object with its own data.

Class
The program is broken into multiple classes that define clear responsibilities within the pipeline.

Encapsulation
Private fields and public methods are used to control access to internal state.

Inheritance
Not directly implemented, but the design allows for it if future requirements demand it.

Polymorphism
Not directly implemented, but the structure supports interchangeable components if extended.

Testing and Verification

To confirm that Assignment 3 behaves exactly the same as Assignment 2, several tests were performed.

First, both versions were run using the same products.csv file containing valid and invalid rows. The resulting transformed_products.csv files were compared and found to be identical, including row order, price rounding, category changes, and output formatting.

Second, the input file was removed to test error handling. Both programs printed the same error message and exited without producing an output file.

Third, an empty input file containing only the header row was tested. In both cases, the output file contained only the header, and the summary output reported zero rows read, transformed, and skipped.

Finally, malformed rows with missing fields, extra commas, or non-numeric prices were tested. Both implementations skipped these rows, updated counters correctly, and continued processing valid rows without crashing.

These tests confirm that the object-oriented redesign preserves the exact behavior of the original implementation.

Summary

Assignment 3 improves the overall structure of the ETL pipeline without changing its functionality. The large, single-method design from Assignment 2 has been replaced with a set of smaller, focused classes that work together to perform the same task. This makes the code easier to read, easier to test, and easier to extend. Overall, the redesign demonstrates a clearer understanding of object-oriented principles and results in a more maintainable and professional solution.
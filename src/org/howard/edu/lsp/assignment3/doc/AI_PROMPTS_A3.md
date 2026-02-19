CSCI 363 – Assignment 3
AI_PROMPTS_A3.md

Overview

For Assignment 3, I used a generative AI assistant as a brainstorming and support tool while redesigning my Assignment 2 ETL pipeline to be more object-oriented. The AI helped me think through class responsibilities, object-oriented structure, and documentation. I did not copy code blindly. I reviewed all suggestions, adjusted them to match the exact requirements of the assignment, and ensured the final implementation preserved the same behavior as Assignment 2.

Below are examples of prompts I asked and how I used the responses.

Prompt 1
“How can I redesign a simple Java ETL pipeline to be more object-oriented?”

AI Response Summary
The AI suggested breaking the pipeline into separate classes for reading input, transforming data, writing output, and representing the data as objects. It emphasized separating responsibilities instead of putting everything in one main method.

How I Used It
This confirmed the direction I was already considering. I adopted the idea of splitting the pipeline into Extract, Transform, and Load components and introduced a Product class to represent each record. I refined the class structure to exactly match the assignment requirements and my existing logic from Assignment 2.

––––––––––––––––

Prompt 2
“What classes would make sense for an object-oriented ETL pipeline in Java?”

AI Response Summary
The AI suggested classes such as CSVReader, Transformer, CSVWriter, and a data model class to represent rows.

How I Used It
I used this suggestion as a starting point but customized it. I created CSVReader, ProductTransformer, CSVWriter, Product, and ETLPipeline. I ensured that each class had a single responsibility and that the overall behavior matched Assignment 2 exactly.

––––––––––––––––

Prompt 3
“How can encapsulation be applied in a simple Java data processing program?”

AI Response Summary
The AI explained that encapsulation involves keeping fields private and exposing behavior through public methods, especially for data models.

How I Used It
I applied this directly in the Product class by making all fields private and using getters and setters. I also applied encapsulation in CSVReader by keeping row counters private and exposing them only through getter methods.

––––––––––––––––

Prompt 4
“Does this design demonstrate object-oriented principles even without inheritance or polymorphism?”

AI Response Summary
The AI explained that not all OO designs require inheritance or polymorphism and that clean class decomposition and encapsulation are valid demonstrations of object-oriented thinking.

How I Used It
This helped guide my reflection. I clearly stated in REFLECTION_A3.md that inheritance and polymorphism were not explicitly required but that the design supports them naturally if the system were extended in the future.

––––––––––––––––

Prompt 5
“Help me write Javadocs for my Java classes.”

AI Response Summary
The AI generated sample Javadocs describing class responsibilities and public methods.

How I Used It
I used the AI-generated Javadocs as a draft only. I reviewed and edited all documentation to ensure accuracy, clarity, and consistency with the actual behavior of my code. I removed anything that did not exactly match my implementation.

––––––––––––––––

Summary

The AI assistant was used as a learning and brainstorming tool rather than a code generator. All final design decisions, implementation details, and testing were done by me. The AI helped clarify object-oriented concepts, suggest possible class structures, and improve documentation quality, but I ensured that the final solution met all assignment requirements and preserved the exact behavior of Assignment 2.
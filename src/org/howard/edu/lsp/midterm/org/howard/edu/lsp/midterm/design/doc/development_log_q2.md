# Development Log – Question 2

## External Resources Used

### AI Tool: Claude (Anthropic)

**Complete Conversation Transcript:**

**Prompt:** I have a Java midterm question about object-oriented design. I need to evaluate an OrderProcessor class that has poor encapsulation (all public fields), a processOrder() method that calculates tax, prints a receipt, saves to a file, sends email, applies a discount, and logs activity all in one place. I need to identify the OO design issues referencing Arthur Riel's heuristics and write it in a markdown file called evaluation.md. Then I need to propose a redesigned system using CRC cards in a markdown file called design.md.

**Response:** Claude identified five major design issues: (1) all fields being public violates encapsulation, (2) the processOrder() method violates single responsibility by doing tax calculation, printing, file I/O, emailing, and logging, (3) responsibilities belong to separate dedicated classes, (4) the discount is applied after the receipt is already printed which is a logical bug, and (5) the class has low cohesion making it a "God Class." For the redesigned CRC cards, Claude proposed splitting the system into Order (data), TaxCalculator (pricing logic), ReceiptPrinter (output), OrderRepository (persistence), NotificationService (email and logging), and a thin OrderProcessor coordinator.

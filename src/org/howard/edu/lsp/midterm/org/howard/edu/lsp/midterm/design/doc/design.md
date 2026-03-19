# Proposed Improved Design – CRC Cards

The redesigned system breaks the monolithic `OrderProcessor` into five focused classes, each with a single responsibility.

---

## CRC Card 1

**Class:** Order  
**Responsibilities:**
- Store customer name, email, item, and price
- Provide access to order data via getters
- Enforce that all required fields are present  

**Collaborators:** None

---

## CRC Card 2

**Class:** TaxCalculator  
**Responsibilities:**
- Calculate tax amount based on item price
- Calculate the total price including tax
- Apply any applicable discounts before returning final price  

**Collaborators:** Order

---

## CRC Card 3

**Class:** ReceiptPrinter  
**Responsibilities:**
- Format and print the order receipt to the console
- Display customer name, item, and total price  

**Collaborators:** Order, TaxCalculator

---

## CRC Card 4

**Class:** OrderRepository  
**Responsibilities:**
- Save completed order records to persistent storage (e.g., a file)
- Format the order data for storage  

**Collaborators:** Order

---

## CRC Card 5

**Class:** NotificationService  
**Responsibilities:**
- Send a confirmation email to the customer
- Log order activity with a timestamp  

**Collaborators:** Order

---

## CRC Card 6

**Class:** OrderProcessor  
**Responsibilities:**
- Coordinate the full order processing workflow
- Delegate tax calculation, receipt printing, saving, and notification to the appropriate classes  

**Collaborators:** Order, TaxCalculator, ReceiptPrinter, OrderRepository, NotificationService

---

## How This Improves the Design

Each class now has one reason to change. If the tax rules change, only `TaxCalculator` is modified. If the file format changes, only `OrderRepository` is updated. `OrderProcessor` becomes a thin coordinator rather than a bloated God Class, and each component can be tested independently.

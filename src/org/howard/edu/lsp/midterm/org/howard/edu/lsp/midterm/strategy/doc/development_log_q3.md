# Development Log – Question 3

## External Resources Used

### AI Tool: Claude (Anthropic)

**Complete Conversation Transcript:**

**Prompt:** I have a Java midterm question. I need to evaluate a PriceCalculator class that uses a chain of if statements to apply discounts based on customer type (REGULAR, MEMBER, VIP, HOLIDAY). Then I need to refactor it using the Strategy Pattern. I need a PricingStrategy interface, a separate class for each customer type that implements it, a refactored PriceCalculator that uses the interface, and a Driver class that tests all four types with a price of 100.0 and prints output in the format "REGULAR: 100.0", "MEMBER: 90.0", etc.

**Response:** Claude identified four design problems with the original class: it violates the Open/Closed Principle because adding a new customer type requires modifying existing code, the method grows increasingly complex with more types, all pricing logic is centralized rather than encapsulated per type, and the string-based dispatch is fragile and untestable in isolation. Claude then implemented the Strategy Pattern by creating a PricingStrategy interface with a calculatePrice(double price) method, four concrete strategy classes (RegularPricingStrategy, MemberPricingStrategy, VIPPricingStrategy, HolidayPricingStrategy), a refactored PriceCalculator that accepts a PricingStrategy via its constructor and a setStrategy() method, and a Driver that demonstrates all four strategies with a purchase price of 100.0.

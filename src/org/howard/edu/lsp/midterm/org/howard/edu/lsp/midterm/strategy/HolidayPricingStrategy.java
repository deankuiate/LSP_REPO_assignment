// Dean Kuiate
package org.howard.edu.lsp.midterm.strategy;

public class HolidayPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double price) {
        return price * 0.85;
    }
}

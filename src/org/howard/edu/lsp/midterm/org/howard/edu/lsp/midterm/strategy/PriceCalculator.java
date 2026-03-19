// Dean Kuiate
package org.howard.edu.lsp.midterm.strategy;

public class PriceCalculator {

    private PricingStrategy strategy;

    public PriceCalculator(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public void setStrategy(PricingStrategy strategy) {
        this.strategy = strategy;
    }

    public double calculatePrice(double price) {
        return strategy.calculatePrice(price);
    }
}

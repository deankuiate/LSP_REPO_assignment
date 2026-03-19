// Dean Kuiate
package org.howard.edu.lsp.midterm.strategy;

public class MemberPricingStrategy implements PricingStrategy {

    @Override
    public double calculatePrice(double price) {
        return price * 0.90;
    }
}

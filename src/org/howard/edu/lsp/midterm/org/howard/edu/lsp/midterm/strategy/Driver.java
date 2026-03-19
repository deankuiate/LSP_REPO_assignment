// Dean Kuiate
package org.howard.edu.lsp.midterm.strategy;

public class Driver {

    public static void main(String[] args) {
        double purchasePrice = 100.0;

        PriceCalculator calculator = new PriceCalculator(new RegularPricingStrategy());
        System.out.println("REGULAR: " + calculator.calculatePrice(purchasePrice));

        calculator.setStrategy(new MemberPricingStrategy());
        System.out.println("MEMBER: " + calculator.calculatePrice(purchasePrice));

        calculator.setStrategy(new VIPPricingStrategy());
        System.out.println("VIP: " + calculator.calculatePrice(purchasePrice));

        calculator.setStrategy(new HolidayPricingStrategy());
        System.out.println("HOLIDAY: " + calculator.calculatePrice(purchasePrice));
    }
}

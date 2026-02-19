package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Applies all business-rule transformations to a {@link Product} object.
 * Responsible for the Transform phase of the ETL pipeline.
 *
 * <p>Transformations applied (in order):
 * <ol>
 *   <li>Convert product name to uppercase.</li>
 *   <li>Apply a 10% discount to Electronics products.</li>
 *   <li>Round price to two decimal places (HALF_UP).</li>
 *   <li>Reclassify Electronics priced above $500 as "Premium Electronics".</li>
 *   <li>Assign a PriceRange label based on the final price.</li>
 * </ol>
 */
public class ProductTransformer {

    /**
     * Transforms the given product in place by applying all business rules,
     * and returns the computed PriceRange label.
     *
     * @param product the product to transform; mutated directly
     * @return the PriceRange string ("Low", "Medium", "High", or "Premium")
     */
    public String transform(Product product) {
        // 1. Name to uppercase
        product.setName(product.getName().toUpperCase());

        boolean wasElectronics = product.getCategory().equals("Electronics");

        // 2. Apply 10% discount for Electronics
        BigDecimal price = product.getPrice();
        if (wasElectronics) {
            price = price.multiply(new BigDecimal("0.90"));
        }

        // 3. Round to 2 decimal places
        price = price.setScale(2, RoundingMode.HALF_UP);
        product.setPrice(price);

        // 4. Reclassify as Premium Electronics if price > 500
        if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
            product.setCategory("Premium Electronics");
        }

        // 5. Assign price range
        return computePriceRange(price);
    }

    /**
     * Computes the PriceRange label for a given price.
     *
     * @param price the final product price
     * @return "Low" if price &le; $10, "Medium" if &le; $100,
     *         "High" if &le; $500, or "Premium" otherwise
     */
    private String computePriceRange(BigDecimal price) {
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
            return "High";
        } else {
            return "Premium";
        }
    }
}

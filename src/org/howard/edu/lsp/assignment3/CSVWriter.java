package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.List;

/**
 * Handles writing transformed product records to a CSV output file.
 * Responsible for the Load phase of the ETL pipeline.
 */
public class CSVWriter {

    /** Path to the output CSV file. */
    private final String outputPath;

    /**
     * Constructs a CSVWriter for the specified output file path.
     *
     * @param outputPath the relative or absolute path to the output CSV file
     */
    public CSVWriter(String outputPath) {
        this.outputPath = outputPath;
    }

    /**
     * Writes the CSV header and all transformed product rows to the output file.
     * Each product entry includes its PriceRange label.
     *
     * @param products   the list of transformed {@link Product} objects to write
     * @param priceRanges a parallel list of PriceRange labels, one per product
     * @throws IOException if the file cannot be created or written to
     */
    public void writeProducts(List<Product> products, List<String> priceRanges) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))) {
            // Write header
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                String priceRange = priceRanges.get(i);

                writer.write(
                    p.getProductId() + "," +
                    p.getName() + "," +
                    p.getPrice().toString() + "," +
                    p.getCategory() + "," +
                    priceRange
                );
                writer.newLine();
            }
        }
    }
}

package org.howard.edu.lsp.assignment2;

import java.io.*;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {
        String inputPath = "data/products.csv";
        String outputPath = "data/transformed_products.csv";

        int rowsRead = 0;
        int rowsWritten = 0;
        int rowsSkipped = 0;

        File inputFile = new File(inputPath);

        // Case C: Missing input file
        if (!inputFile.exists()) {
            System.out.println("ERROR: Input file not found at " + inputPath);
            return;
        }

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath))
        ) {

            // Always write header to output
            writer.write("ProductID,Name,Price,Category,PriceRange");
            writer.newLine();

            String line = reader.readLine(); // Read header
            if (line == null) {
                // Empty file (no header even)
                printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);
                return;
            }

            while ((line = reader.readLine()) != null) {
                rowsRead++;

                line = line.trim();

                // Skip blank lines
                if (line.isEmpty()) {
                    rowsSkipped++;
                    continue;
                }

                String[] fields = line.split(",");

                // Skip wrong number of fields
                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    // Trim fields
                    String idStr = fields[0].trim();
                    String name = fields[1].trim();
                    String priceStr = fields[2].trim();
                    String category = fields[3].trim();

                    int productId = Integer.parseInt(idStr);
                    BigDecimal price = new BigDecimal(priceStr);

                    // 1. Name to uppercase
                    name = name.toUpperCase();

                    boolean wasElectronics = category.equals("Electronics");

                    // 2. 10% discount for Electronics
                    if (wasElectronics) {
                        price = price.multiply(new BigDecimal("0.90"));
                    }

                    // Round price to 2 decimals (HALF_UP)
                    price = price.setScale(2, RoundingMode.HALF_UP);

                    // 3. Premium Electronics check
                    if (wasElectronics && price.compareTo(new BigDecimal("500.00")) > 0) {
                        category = "Premium Electronics";
                    }

                    // 4. PriceRange
                    String priceRange;
                    if (price.compareTo(new BigDecimal("10.00")) <= 0) {
                        priceRange = "Low";
                    } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
                        priceRange = "Medium";
                    } else if (price.compareTo(new BigDecimal("500.00")) <= 0) {
                        priceRange = "High";
                    } else {
                        priceRange = "Premium";
                    }

                    // Write output row
                    writer.write(
                        productId + "," +
                        name + "," +
                        price.toString() + "," +
                        category + "," +
                        priceRange
                    );
                    writer.newLine();

                    rowsWritten++;

                } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
    rowsSkipped++;
}
            }

        } catch (IOException e) {
            System.out.println("ERROR: Unable to process files.");
            return;
        }

        printSummary(rowsRead, rowsWritten, rowsSkipped, outputPath);
    }

    private static void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
    }
}
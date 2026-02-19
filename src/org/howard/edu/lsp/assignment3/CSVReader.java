package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Handles reading and parsing of product records from a CSV input file.
 * Responsible for the Extract phase of the ETL pipeline.
 * Skips malformed rows and tracks skipped row count.
 */
public class CSVReader {

    /** Path to the input CSV file. */
    private final String inputPath;

    /** Number of rows that were skipped due to invalid data. */
    private int rowsSkipped;

    /** Number of data rows successfully read (including those later skipped). */
    private int rowsRead;

    /**
     * Constructs a CSVReader for the specified input file path.
     *
     * @param inputPath the relative or absolute path to the input CSV file
     */
    public CSVReader(String inputPath) {
        this.inputPath = inputPath;
        this.rowsSkipped = 0;
        this.rowsRead = 0;
    }

    /**
     * Reads all valid product records from the CSV file.
     * Skips rows that are blank, have the wrong number of fields,
     * or contain non-numeric values for ID or Price.
     *
     * @return a list of valid {@link Product} objects parsed from the file
     * @throws IOException if the file cannot be opened or read
     */
    public List<Product> readProducts() throws IOException {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(inputPath))) {
            String line = reader.readLine(); // Read and discard header

            if (line == null) {
                // File is completely empty — no header, no data
                return products;
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

                // Skip rows with wrong number of fields
                if (fields.length != 4) {
                    rowsSkipped++;
                    continue;
                }

                try {
                    String idStr    = fields[0].trim();
                    String name     = fields[1].trim();
                    String priceStr = fields[2].trim();
                    String category = fields[3].trim();

                    int productId    = Integer.parseInt(idStr);
                    BigDecimal price = new BigDecimal(priceStr);

                    products.add(new Product(productId, name, price, category));

                } catch (NumberFormatException e) {
                    rowsSkipped++;
                }
            }
        }

        return products;
    }

    /**
     * Returns the total number of data rows encountered (excluding header).
     *
     * @return number of rows read
     */
    public int getRowsRead() {
        return rowsRead;
    }

    /**
     * Returns the number of rows that were skipped due to invalid data.
     *
     * @return number of rows skipped
     */
    public int getRowsSkipped() {
        return rowsSkipped;
    }
}

package org.howard.edu.lsp.assignment3;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Entry point and orchestrator for the ETL (Extract, Transform, Load) pipeline.
 * Coordinates the three phases using dedicated helper classes:
 * {@link CSVReader} for extraction, {@link ProductTransformer} for transformation,
 * and {@link CSVWriter} for loading.
 *
 * <p>Usage: Run this class directly. Input and output paths are set as constants.
 */
public class ETLPipeline {

    /** Relative path to the input CSV file. */
    private static final String INPUT_PATH  = "data/products.csv";

    /** Relative path to the output CSV file. */
    private static final String OUTPUT_PATH = "data/transformed_products.csv";

    /**
     * Main method. Validates the input file exists, then runs the ETL pipeline
     * and prints a summary to standard output.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Case C: Missing input file
        File inputFile = new File(INPUT_PATH);
        if (!inputFile.exists()) {
            System.out.println("ERROR: Input file not found at " + INPUT_PATH);
            return;
        }

        ETLPipeline pipeline = new ETLPipeline();
        pipeline.run();
    }

    /**
     * Executes the full ETL pipeline: extract, transform, and load.
     * Prints a summary of rows read, transformed, and skipped upon completion.
     */
    public void run() {
        CSVReader reader             = new CSVReader(INPUT_PATH);
        ProductTransformer transformer = new ProductTransformer();
        CSVWriter writer             = new CSVWriter(OUTPUT_PATH);

        List<Product> products;
        List<String> priceRanges = new ArrayList<>();

        // --- EXTRACT ---
        try {
            products = reader.readProducts();
        } catch (IOException e) {
            System.out.println("ERROR: Unable to process files.");
            return;
        }

        // --- TRANSFORM ---
        for (Product product : products) {
            String priceRange = transformer.transform(product);
            priceRanges.add(priceRange);
        }

        // --- LOAD ---
        try {
            writer.writeProducts(products, priceRanges);
        } catch (IOException e) {
            System.out.println("ERROR: Unable to process files.");
            return;
        }

        // Print summary
        int rowsRead    = reader.getRowsRead();
        int rowsSkipped = reader.getRowsSkipped();
        int rowsWritten = products.size();

        printSummary(rowsRead, rowsWritten, rowsSkipped, OUTPUT_PATH);
    }

    /**
     * Prints a summary of the ETL pipeline run to standard output.
     *
     * @param read       total rows read from input (excluding header)
     * @param written    total rows successfully written to output
     * @param skipped    total rows skipped due to invalid data
     * @param outputPath the path where output was written
     */
    private void printSummary(int read, int written, int skipped, String outputPath) {
        System.out.println("Rows read: " + read);
        System.out.println("Rows transformed: " + written);
        System.out.println("Rows skipped: " + skipped);
        System.out.println("Output written to: " + outputPath);
    }
}

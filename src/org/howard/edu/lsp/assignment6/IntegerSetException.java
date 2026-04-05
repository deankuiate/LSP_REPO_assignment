package org.howard.edu.lsp.assignment6;

/**
 * Exception thrown when an operation is attempted on an empty IntegerSet
 * where the operation requires at least one element (e.g., largest, smallest).
 */
public class IntegerSetException extends Exception {
    public IntegerSetException(String message) {
        super(message);
    }
}

package org.howard.edu.lsp.assignment5;

/**
 * Exception thrown when an invalid operation is performed on an IntegerSet,
 * such as calling largest() or smallest() on an empty set.
 *
 * @author Student
 * @version 1.0
 */
public class IntegerSetException extends RuntimeException {

    /**
     * Constructs a new IntegerSetException with the specified detail message.
     *
     * @param message the detail message
     */
    public IntegerSetException(String message) {
        super(message);
    }
}

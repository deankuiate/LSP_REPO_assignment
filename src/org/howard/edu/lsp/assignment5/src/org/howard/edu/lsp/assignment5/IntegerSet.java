package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet models a mathematical set of integers.
 * A set contains no duplicate values and supports standard set operations
 * such as union, intersection, difference, and complement.
 *
 * <p>All set operations (union, intersect, diff, complement) return a new
 * IntegerSet and do not modify the original sets.</p>
 *
 * @author Student
 * @version 1.0
 */
public class IntegerSet {

    /** Internal storage for the set elements. */
    private ArrayList<Integer> set = new ArrayList<>();

    /**
     * Default constructor. Creates an empty IntegerSet.
     */
    public IntegerSet() {
    }

    /**
     * Clears all elements from the set, leaving it empty.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the number of elements in the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns {@code true} if this set contains exactly the same elements
     * as the specified set, regardless of order.
     *
     * @param b the IntegerSet to compare with
     * @return {@code true} if both sets have the same elements; {@code false} otherwise
     */
    public boolean equals(IntegerSet b) {
        if (this.length() != b.length()) {
            return false;
        }
        ArrayList<Integer> copyA = new ArrayList<>(set);
        ArrayList<Integer> copyB = new ArrayList<>(b.set);
        Collections.sort(copyA);
        Collections.sort(copyB);
        return copyA.equals(copyB);
    }

    /**
     * Returns {@code true} if the set contains the specified value.
     *
     * @param value the integer to search for
     * @return {@code true} if the value is in the set; {@code false} otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
     *
     * @return the largest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int largest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest element in the set.
     *
     * @return the smallest integer in the set
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set. If the item is already present, it is not added again.
     *
     * @param item the integer to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set. If the item is not present, nothing happens.
     *
     * @param item the integer to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new IntegerSet containing all elements that appear in either
     * this set or the specified set.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the union
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int val : intSetb.set) {
            if (!result.set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new IntegerSet containing only elements common to both
     * this set and the specified set.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the intersection
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        result.set.retainAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in this set but not in
     * the specified set.
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the difference (this - b)
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        result.set.removeAll(intSetb.set);
        return result;
    }

    /**
     * Returns a new IntegerSet containing elements in the specified set
     * but not in this set (i.e., b - this).
     *
     * @param intSetb the other IntegerSet
     * @return a new IntegerSet representing the complement (b - this)
     */
    public IntegerSet complement(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(intSetb.set);
        result.set.removeAll(this.set);
        return result;
    }

    /**
     * Returns {@code true} if the set contains no elements.
     *
     * @return {@code true} if the set is empty; {@code false} otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set. Elements are sorted in
     * ascending order, separated by a comma and a single space, and enclosed
     * in square brackets.
     *
     * <p>An empty set returns {@code []}.</p>
     *
     * @return a formatted string representing the set
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}

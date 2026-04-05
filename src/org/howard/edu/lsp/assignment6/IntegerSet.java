package org.howard.edu.lsp.assignment6;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * IntegerSet represents a mathematical set of integers.
 * Duplicate values are not allowed.
 */
public class IntegerSet {

    private List<Integer> set = new ArrayList<>();

    /** Default constructor: creates an empty IntegerSet. */
    public IntegerSet() {}

    /** Constructor that initializes the set from an existing list. */
    public IntegerSet(ArrayList<Integer> set) {
        this.set = set;
    }

    /**
     * Clears all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     */
    public int length() {
        return set.size();
    }

    /**
     * Returns true if this set equals another set (same elements, any order).
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof IntegerSet)) return false;
        IntegerSet other = (IntegerSet) o;
        if (this.length() != other.length()) return false;
        List<Integer> copy = new ArrayList<>(other.set);
        for (int val : this.set) {
            if (!copy.remove(Integer.valueOf(val))) return false;
        }
        return true;
    }

    /**
     * Returns true if the set contains the given value.
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest element in the set.
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
     * @throws IntegerSetException if the set is empty
     */
    public int smallest() throws IntegerSetException {
        if (isEmpty()) {
            throw new IntegerSetException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an element to the set. Duplicates are ignored.
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an element from the set. Does nothing if element is not present.
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Sets this set to the union of itself and the given set.
     */
    public void union(IntegerSet intSetb) {
        for (int val : intSetb.set) {
            if (!set.contains(val)) {
                set.add(val);
            }
        }
    }

    /**
     * Sets this set to the intersection of itself and the given set.
     */
    public void intersect(IntegerSet intSetb) {
        set.retainAll(intSetb.set);
    }

    /**
     * Sets this set to the difference of itself minus the given set (this - b).
     */
    public void diff(IntegerSet intSetb) {
        set.removeAll(intSetb.set);
    }

    /**
     * Sets this set to the complement relative to intSetb (elements in b not in this).
     */
    public void complement(IntegerSet intSetb) {
        List<Integer> result = new ArrayList<>();
        for (int val : intSetb.set) {
            if (!set.contains(val)) {
                result.add(val);
            }
        }
        set = result;
    }

    /**
     * Returns true if the set is empty.
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns the set as a string in the format [1, 2, 3].
     * Returns [] for empty set.
     */
    @Override
    public String toString() {
        return set.toString();
    }
}

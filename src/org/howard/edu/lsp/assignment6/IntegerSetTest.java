package org.howard.edu.lsp.assignment6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test cases for IntegerSet.
 * Each method is tested with at least one normal case and one edge case.
 */
public class IntegerSetTest {

    // ─────────────────────────────────────────────
    // isEmpty()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test isEmpty on empty set")
    public void testIsEmptyOnEmptySet() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty(), "A new set should be empty");
    }

    @Test
    @DisplayName("Test isEmpty on non-empty set")
    public void testIsEmptyOnNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertFalse(set.isEmpty(), "Set with elements should not be empty");
    }

    // ─────────────────────────────────────────────
    // add()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test add normal case")
    public void testAddNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        assertTrue(set.contains(10), "Set should contain added element");
    }

    @Test
    @DisplayName("Test add duplicate value (edge case)")
    public void testAddDuplicate() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(3);
        assertEquals(1, set.length(), "Adding duplicate should not increase set size");
    }

    // ─────────────────────────────────────────────
    // remove()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test remove normal case")
    public void testRemoveNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        set.remove(7);
        assertFalse(set.contains(7), "Element should be removed from the set");
    }

    @Test
    @DisplayName("Test remove value not present (edge case)")
    public void testRemoveValueNotPresent() {
        IntegerSet set = new IntegerSet();
        set.add(4);
        assertDoesNotThrow(() -> set.remove(99),
            "Removing a non-existent value should not throw an exception");
        assertEquals(1, set.length(), "Set size should be unchanged after removing missing element");
    }

    // ─────────────────────────────────────────────
    // contains()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test contains normal case")
    public void testContainsNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(15);
        assertTrue(set.contains(15), "Set should contain 15");
    }

    @Test
    @DisplayName("Test contains value not present (edge case)")
    public void testContainsValueNotPresent() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        assertFalse(set.contains(100), "Set should not contain a value that was never added");
    }

    // ─────────────────────────────────────────────
    // largest()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test largest normal case")
    public void testLargestNormalCase() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(8);
        set.add(1);
        assertEquals(8, set.largest(), "Largest element should be 8");
    }

    @Test
    @DisplayName("Test largest with single element (edge case)")
    public void testLargestSingleElement() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(42);
        assertEquals(42, set.largest(), "Largest of a single-element set should be that element");
    }

    @Test
    @DisplayName("Test largest on empty set throws exception (edge case)")
    public void testLargestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IntegerSetException.class, set::largest,
            "Calling largest() on empty set should throw IntegerSetException");
    }

    // ─────────────────────────────────────────────
    // smallest()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test smallest normal case")
    public void testSmallestNormalCase() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(5);
        set.add(2);
        set.add(9);
        assertEquals(2, set.smallest(), "Smallest element should be 2");
    }

    @Test
    @DisplayName("Test smallest with single element (edge case)")
    public void testSmallestSingleElement() throws IntegerSetException {
        IntegerSet set = new IntegerSet();
        set.add(7);
        assertEquals(7, set.smallest(), "Smallest of a single-element set should be that element");
    }

    @Test
    @DisplayName("Test smallest on empty set throws exception (edge case)")
    public void testSmallestEmptySetThrowsException() {
        IntegerSet set = new IntegerSet();
        assertThrows(IntegerSetException.class, set::smallest,
            "Calling smallest() on empty set should throw IntegerSetException");
    }

    // ─────────────────────────────────────────────
    // equals()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test equals normal case")
    public void testEqualsNormalCase() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        b.add(1); b.add(2); b.add(3);
        assertTrue(a.equals(b), "Two sets with the same elements should be equal");
    }

    @Test
    @DisplayName("Test equals same elements different order (edge case)")
    public void testEqualsDifferentOrder() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        b.add(3); b.add(1); b.add(2);
        assertTrue(a.equals(b), "Sets with same elements in different order should be equal");
    }

    @Test
    @DisplayName("Test equals with different elements")
    public void testEqualsNotEqual() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(1); b.add(3);
        assertFalse(a.equals(b), "Sets with different elements should not be equal");
    }

    // ─────────────────────────────────────────────
    // union()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test union normal case")
    public void testUnionNormalCase() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(3); b.add(4);
        a.union(b);
        assertTrue(a.contains(1) && a.contains(2) && a.contains(3) && a.contains(4),
            "Union should contain all elements from both sets");
    }

    @Test
    @DisplayName("Test union with empty set (edge case)")
    public void testUnionWithEmptySet() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet(); // empty
        a.add(1); a.add(2);
        a.union(b);
        assertEquals(2, a.length(), "Union with empty set should not change the set");
    }

    // ─────────────────────────────────────────────
    // intersect()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test intersect normal case")
    public void testIntersectNormalCase() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        b.add(2); b.add(3); b.add(4);
        a.intersect(b);
        assertTrue(a.contains(2) && a.contains(3) && !a.contains(1) && !a.contains(4),
            "Intersection should contain only common elements");
    }

    @Test
    @DisplayName("Test intersect with no common elements (edge case)")
    public void testIntersectNoCommonElements() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(3); b.add(4);
        a.intersect(b);
        assertTrue(a.isEmpty(), "Intersection of disjoint sets should be empty");
    }

    // ─────────────────────────────────────────────
    // diff()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test diff normal case")
    public void testDiffNormalCase() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2); a.add(3);
        b.add(2); b.add(3);
        a.diff(b);
        assertTrue(a.contains(1) && !a.contains(2) && !a.contains(3),
            "Difference should only contain elements in a but not in b");
    }

    @Test
    @DisplayName("Test diff with identical sets (edge case)")
    public void testDiffIdenticalSets() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(1); b.add(2);
        a.diff(b);
        assertTrue(a.isEmpty(), "Difference of identical sets should be empty");
    }

    // ─────────────────────────────────────────────
    // complement()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test complement normal case")
    public void testComplementNormalCase() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(1); b.add(2); b.add(3); b.add(4);
        a.complement(b);
        assertTrue(a.contains(3) && a.contains(4) && !a.contains(1) && !a.contains(2),
            "Complement should contain elements in b not in original a");
    }

    @Test
    @DisplayName("Test complement with disjoint sets (edge case)")
    public void testComplementDisjointSets() {
        IntegerSet a = new IntegerSet();
        IntegerSet b = new IntegerSet();
        a.add(1); a.add(2);
        b.add(3); b.add(4);
        a.complement(b);
        assertTrue(a.contains(3) && a.contains(4),
            "Complement of disjoint sets should be all elements of b");
    }

    // ─────────────────────────────────────────────
    // toString()
    // ─────────────────────────────────────────────

    @Test
    @DisplayName("Test toString normal case")
    public void testToStringNormalCase() {
        IntegerSet set = new IntegerSet();
        set.add(1); set.add(2); set.add(3);
        String result = set.toString();
        assertTrue(result.startsWith("[") && result.endsWith("]"),
            "toString should produce output in bracket format");
    }

    @Test
    @DisplayName("Test toString on empty set (edge case)")
    public void testToStringEmptySet() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString(), "toString of empty set should be []");
    }
}

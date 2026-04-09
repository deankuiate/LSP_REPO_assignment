package org.howard.edu.lsp.assignment5;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import static org.junit.jupiter.api.Assertions.*;

/**
 * JUnit 5 test class for IntegerSet.
 *
 * @author Student
 * @version 1.0
 */
public class IntegerSetTest {

    private IntegerSet set1;
    private IntegerSet set2;

    @BeforeEach
    public void setUp() {
        set1 = new IntegerSet();
        set2 = new IntegerSet();
    }

    // -----------------------------------------------------------------------
    // clear()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test clear on non-empty set makes it empty")
    public void testClearNonEmpty() {
        set1.add(1);
        set1.add(2);
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test clear on already empty set stays empty")
    public void testClearEmpty() {
        set1.clear();
        assertTrue(set1.isEmpty());
    }

    // -----------------------------------------------------------------------
    // length()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test length of empty set is 0")
    public void testLengthEmpty() {
        assertEquals(0, set1.length());
    }

    @Test
    @DisplayName("Test length after adding elements")
    public void testLengthAfterAdd() {
        set1.add(10);
        set1.add(20);
        set1.add(30);
        assertEquals(3, set1.length());
    }

    @Test
    @DisplayName("Test length does not increase on duplicate add")
    public void testLengthNoDuplicate() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // -----------------------------------------------------------------------
    // equals()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test equals returns true for same elements in different order")
    public void testEqualsOrderIndependent() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(3); set2.add(1); set2.add(2);
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals returns false when sets differ")
    public void testEqualsNotEqual() {
        set1.add(1); set1.add(2);
        set2.add(1); set2.add(3);
        assertFalse(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals on two empty sets")
    public void testEqualsEmpty() {
        assertTrue(set1.equals(set2));
    }

    @Test
    @DisplayName("Test equals returns false when sizes differ")
    public void testEqualsDifferentSize() {
        set1.add(1); set1.add(2);
        set2.add(1);
        assertFalse(set1.equals(set2));
    }

    // -----------------------------------------------------------------------
    // contains()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test contains returns true for existing element")
    public void testContainsTrue() {
        set1.add(7);
        assertTrue(set1.contains(7));
    }

    @Test
    @DisplayName("Test contains returns false for missing element")
    public void testContainsFalse() {
        set1.add(7);
        assertFalse(set1.contains(99));
    }

    @Test
    @DisplayName("Test contains on empty set returns false")
    public void testContainsEmpty() {
        assertFalse(set1.contains(1));
    }

    // -----------------------------------------------------------------------
    // largest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test largest returns correct maximum")
    public void testLargest() {
        set1.add(3); set1.add(1); set1.add(8); set1.add(2);
        assertEquals(8, set1.largest());
    }

    @Test
    @DisplayName("Test largest throws exception on empty set")
    public void testLargestEmpty() {
        assertThrows(IntegerSetException.class, () -> set1.largest());
    }

    // -----------------------------------------------------------------------
    // smallest()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test smallest returns correct minimum")
    public void testSmallest() {
        set1.add(3); set1.add(1); set1.add(8); set1.add(2);
        assertEquals(1, set1.smallest());
    }

    @Test
    @DisplayName("Test smallest throws exception on empty set")
    public void testSmallestEmpty() {
        assertThrows(IntegerSetException.class, () -> set1.smallest());
    }

    // -----------------------------------------------------------------------
    // add()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test add increases length")
    public void testAdd() {
        set1.add(42);
        assertEquals(1, set1.length());
        assertTrue(set1.contains(42));
    }

    @Test
    @DisplayName("Test add duplicate does not change set")
    public void testAddDuplicate() {
        set1.add(5);
        set1.add(5);
        assertEquals(1, set1.length());
    }

    // -----------------------------------------------------------------------
    // remove()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test remove existing element")
    public void testRemoveExisting() {
        set1.add(1); set1.add(2); set1.add(3);
        set1.remove(2);
        assertFalse(set1.contains(2));
        assertEquals(2, set1.length());
    }

    @Test
    @DisplayName("Test remove non-existing element does nothing")
    public void testRemoveNonExisting() {
        set1.add(1);
        set1.remove(99);
        assertEquals(1, set1.length());
    }

    // -----------------------------------------------------------------------
    // union()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test union of two sets with overlap")
    public void testUnionWithOverlap() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.union(set2);
        assertTrue(result.contains(1));
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertTrue(result.contains(4));
        assertEquals(4, result.length());
    }

    @Test
    @DisplayName("Test union does not modify original sets")
    public void testUnionDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        set1.union(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    @Test
    @DisplayName("Test union with empty set returns copy of the other")
    public void testUnionWithEmpty() {
        set1.add(1); set1.add(2);
        IntegerSet result = set1.union(set2);
        assertEquals(2, result.length());
    }

    // -----------------------------------------------------------------------
    // intersect()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test intersect returns common elements")
    public void testIntersect() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.contains(2));
        assertTrue(result.contains(3));
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("Test intersect with no common elements returns empty set")
    public void testIntersectEmpty() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.intersect(set2);
        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Test intersect does not modify originals")
    public void testIntersectDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.intersect(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -----------------------------------------------------------------------
    // diff()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test diff returns elements in set1 not in set2")
    public void testDiff() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertTrue(result.contains(1));
        assertFalse(result.contains(2));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test diff with disjoint sets returns copy of set1")
    public void testDiffDisjoint() {
        set1.add(1); set1.add(2);
        set2.add(3); set2.add(4);
        IntegerSet result = set1.diff(set2);
        assertEquals(2, result.length());
    }

    @Test
    @DisplayName("Test diff does not modify originals")
    public void testDiffDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.diff(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -----------------------------------------------------------------------
    // complement()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test complement returns elements in set2 not in set1")
    public void testComplement() {
        set1.add(1); set1.add(2); set1.add(3);
        set2.add(2); set2.add(3); set2.add(4);
        IntegerSet result = set1.complement(set2);
        assertTrue(result.contains(4));
        assertFalse(result.contains(2));
        assertEquals(1, result.length());
    }

    @Test
    @DisplayName("Test complement does not modify originals")
    public void testComplementDoesNotModifyOriginals() {
        set1.add(1); set1.add(2);
        set2.add(2); set2.add(3);
        set1.complement(set2);
        assertEquals(2, set1.length());
        assertEquals(2, set2.length());
    }

    // -----------------------------------------------------------------------
    // isEmpty()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test isEmpty on new set")
    public void testIsEmptyTrue() {
        assertTrue(set1.isEmpty());
    }

    @Test
    @DisplayName("Test isEmpty returns false after add")
    public void testIsEmptyFalse() {
        set1.add(1);
        assertFalse(set1.isEmpty());
    }

    // -----------------------------------------------------------------------
    // toString()
    // -----------------------------------------------------------------------

    @Test
    @DisplayName("Test toString on empty set returns []")
    public void testToStringEmpty() {
        assertEquals("[]", set1.toString());
    }

    @Test
    @DisplayName("Test toString returns elements in ascending order")
    public void testToStringSorted() {
        set1.add(3); set1.add(1); set1.add(2);
        assertEquals("[1, 2, 3]", set1.toString());
    }

    @Test
    @DisplayName("Test toString single element")
    public void testToStringSingle() {
        set1.add(5);
        assertEquals("[5]", set1.toString());
    }
}

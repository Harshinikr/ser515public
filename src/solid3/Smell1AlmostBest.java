package test3434;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import solid3.Smell1AlmostBest;

public class MyTest {

    // Equivalence Class Tests
    @ParameterizedTest
    @CsvSource({
        "2, 3, 8",   // 2^3 = 8
        "5, 2, 25",  // 5^2 = 25
        "3, 4, 81"   // 3^4 = 81
    })
    @DisplayName("Equivalence Class: Valid base and exponent values")
    void testEquivalenceClasses(int base, int pow, int expected) {
        assertEquals(expected, Smell1AlmostBest.toPower(base, pow));
    }

    // Boundary Cases
    @Test
    @DisplayName("Boundary: Any number to power 0 should return 1")
    void testPowerZero() {
        assertEquals(1, Smell1AlmostBest.toPower(10, 0));
    }

    @Test
    @DisplayName("Boundary: Any number to power 1 should return the number itself")
    void testPowerOne() {
        assertEquals(7, Smell1AlmostBest.toPower(7, 1));
    }

    @Test
    @DisplayName("Boundary: Base 0 with positive power should return 0")
    void testBaseZero() {
        assertEquals(0, Smell1AlmostBest.toPower(0, 5));
    }

    // Edge Case
    @Test
    @DisplayName("Edge Case: 0^0 should return 1 (Java behavior)")
    void testZeroToZero() {
        assertEquals(1, Smell1AlmostBest.toPower(0, 0));
    }

    // Failure Cases
    @Test
    @DisplayName("Failure Case: Negative exponent should cause StackOverflowError")
    void testNegativeExponentFails() {
        assertThrows(StackOverflowError.class, () -> {
            Smell1AlmostBest.toPower(2, -3);
        }, "Expected stack overflow due to unhandled negative exponent");
    }

    @Test
    @DisplayName("Failure Case: Large exponent may cause integer overflow")
    void testLargeExponentOverflow() {
        int result = Smell1AlmostBest.toPower(1000, 5); // 1000^5 = 10^15 > Integer.MAX_VALUE
        assertTrue(result <= 0, "Expected integer overflow for large exponent");
    }
}

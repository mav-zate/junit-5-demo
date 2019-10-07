import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTimeout;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.Duration;
import org.junit.jupiter.api.Test;

public class AssertionsDemo {

    @Test
    void assertionRepertoire() {
        // Structural Equality
        assertEquals(2, 2);
        assertNotEquals(50, 100);

        // Truthiness
        assertTrue(1 < 2);
        assertFalse(1 > 2);

        // Existence
        assertNull(null);
        assertNotNull(1);

        // Referential Equality
        Object obj = new Object();
        Object referenceOne = obj;
        Object referenceTwo = obj;
        Object somethingDifferent = new Object();

        assertSame(referenceOne, referenceTwo);
        assertNotSame(referenceOne, somethingDifferent);
    }

    @Test
    void manualFailure() {
        if (false) {
            fail();
            fail(" and here's why...");
        }

        fail(() -> " and here's why but lazier...");
    }

    @Test
    void assertionSignatures() {
        // Each assertion has at least three signatures:
        assertEquals(2, 1 + 1); // expected && actual
        assertEquals(4, 2 + 2, "two plus two is four"); // expected, actual && message
        assertEquals(3, 4 - 1, () -> "minus one that's three, quick maths"); // expected, actual && lazy loaded message
    }

    @Test
    void individualAssertions() {
        // JUnit4 style
        assertEquals(1, Utils.squareOf(1));
        assertEquals(4, Utils.squareOf(2));
        assertEquals(9, Utils.squareOf(3));
        assertEquals(16, Utils.squareOf(4));
        assertEquals(25, Utils.squareOf(5));
        assertEquals(36, Utils.squareOf(6));
        assertEquals(49, Utils.squareOf(7));
        assertEquals(64, Utils.squareOf(8));
    }

    @Test
    void groupedAssertions() {
        // JUnit5 style
        assertAll("Squaring Function",
            () -> assertEquals(1, Utils.squareOf(1)),
            () -> assertEquals(4, Utils.squareOf(2)),
            () -> assertEquals(9, Utils.squareOf(3)),
            () -> assertEquals(16, Utils.squareOf(4)),
            () -> assertEquals(25, Utils.squareOf(5)), // will fail
            () -> assertEquals(36, Utils.squareOf(6)),
            () -> assertEquals(49, Utils.squareOf(7)), // also will fail
            () -> assertEquals(64, Utils.squareOf(8)) // not a chance of passing
        );
    }

    @Test
    void dependentAssertions() {
        assertAll("Two Groupings of Assertions",
            // First Grouping
            () -> {
                String notAString = Utils.thisMethodAlwaysReturnsNull();
                assertNotNull(notAString);

                // Dependent on first assertion so will not run
                String alsoNotAString = Utils.thisMethodAlwaysReturnsNull();
                String nope = Utils.thisMethodAlwaysReturnsNull();
                assertAll(
                    () -> assertNotNull(alsoNotAString),
                    () -> assertNotNull(nope)
                );
            },
            // Second Grouping
            () -> {
                // Completely independent of previous group
                String definitelyAString = Utils.thisMethodActuallyReturnsAString();

                assertNotNull(definitelyAString);
                System.out.println("This grouping passed independent of the first one");
            }
        );
    }

    @Test
    void exceptionsTesting() {
        assertAll(
            () -> assertThrows(Exception.class, Utils::expectNoException), // fails
            () -> {
                Exception exception = assertThrows(Exception.class, Utils::oopsAnException);
                assertEquals("mistakes were made", exception.getMessage());
            }
        );
    }

    @Test
    void timeouts() {
        assertAll(
            // passes
            () -> assertTimeout(Duration.ofSeconds(5), () -> Utils.pauseThreadXSeconds(1)),

            // fails and lets function finish execution
            () -> assertTimeout(Duration.ofSeconds(1), () -> Utils.pauseThreadXSeconds(3)),

            // fails and interrupts function
            () -> assertTimeoutPreemptively(Duration.ofSeconds(1), () -> Utils.pauseThreadXSeconds(10))
        );
    }
}

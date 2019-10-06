import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.EmptyStackException;
import java.util.Stack;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;

public class LegibilityDemo {
    @Nested
    @DisplayName("Showcase of Display Name Annotation")
    class DisplayNameAnnotation {
        @Test
        @DisplayName("Custom test name containing spaces")
        void testWithDisplayNameContainingSpaces() {
        }

        @Test
        @DisplayName("(◕‿◕✿)" )
        void testWithDisplayNameContainingSpecialCharacters() {
        }

        @Test
        @DisplayName("\uD83D\uDCA9\uD83D\uDCA9\uD83D\uDCA9"  )
        void testWithDisplayNameContainingEmoji() {
        }
    }

    Stack<Integer> testSubject;

    @Nested
    @TestInstance(Lifecycle.PER_CLASS)
    @DisplayName("When a stack is empty, ")
    class stackWhenEmpty {
        @BeforeAll
        void init() {
            testSubject = new Stack<>();
        }

        @Test
        @DisplayName("pop should throw an empty stack exception")
        void testPop() {
            assertThrows(EmptyStackException.class, () -> testSubject.pop());
        }

        @Test
        @DisplayName("peek should throw an empty stack exception")
        void testPeek() {
            assertThrows(EmptyStackException.class, () -> testSubject.peek());
        }
    }

    @Nested
    @DisplayName("When a stack has items, ")
    class stackWhenFull {
        @BeforeEach
        void init() {
            testSubject = new Stack<>();
            testSubject.push(1);
            testSubject.push(2);
            testSubject.push(3);
        }

        @Test
        @DisplayName("pop should eject items LIFO")
        void testPop() {
            assertAll(
                () -> assertEquals(3, testSubject.pop()),
                () -> assertEquals(2, testSubject.pop()),
                () -> assertEquals(1, testSubject.pop())
            );
        }

        @Test
        @DisplayName("peek should show the item last inserted")
        void testPeek() {
            assertAll(
                () -> {
                    assertEquals(3, testSubject.peek());
                    testSubject.pop();
                },
                () -> {
                    testSubject.push(4);
                    assertEquals(4, testSubject.peek());
                }
            );
        }
    }
}

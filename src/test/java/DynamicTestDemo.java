import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.ThrowingConsumer;

public class DynamicTestDemo {
    @TestFactory
    Stream<DynamicTest> generateRandomNumberOfTests() {

        // Generates random positive integers between 0 and 100 until
        // a number evenly divisible by 7 is encountered.
        Iterator<Integer> inputGenerator = new Iterator<Integer>() {

            Random random = new Random();
            int current;

            @Override
            public boolean hasNext() {
                current = random.nextInt(100);
                return current % 7 != 0;
            }

            @Override
            public Integer next() {
                return current;
            }
        };

        // Generates display names like: input:5, input:37, input:85, etc.
        Function<Integer, String> displayNameGenerator = (input) -> "input:" + input;

        // Executes tests based on the current input value.
        ThrowingConsumer<Integer> testExecutor = (input) -> assertTrue(input % 7 != 0);

        // Returns a stream of dynamic tests.
        return DynamicTest.stream(inputGenerator, displayNameGenerator, testExecutor);
    }

    SampleController testSubject;
    @TestFactory
    DynamicNode realisticExample() {
        testSubject = new SampleController();

        Class<?> classOfTestSubject = testSubject.getClass();
        Collection<Method> routes = Arrays.stream(classOfTestSubject.getMethods())
            .filter(method -> method.getAnnotation(Path.class) != null)
            .collect(Collectors.toList());

        return dynamicContainer(
            "All routes should return 200 status code upon success",
            routes.stream().map(route -> dynamicTest(route.getName(), () -> {
                    Response response = (Response) route.invoke(testSubject); // testSubject.firstSampleRoute();
                    assertEquals(200, response.getStatus());
                })).collect(Collectors.toList()));
    }
}

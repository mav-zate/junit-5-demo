import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.parallel.Execution;

import static org.junit.jupiter.api.parallel.ExecutionMode.CONCURRENT;
import static org.junit.jupiter.api.parallel.ExecutionMode.SAME_THREAD;

public class ConcurrencyTest {
    Set<Thread> slowpokeThreads;
    Set<Thread> speedyGonzalesThreads;

    @Nested
    @Execution(SAME_THREAD)
    @TestInstance(Lifecycle.PER_CLASS)
    class Slowpoke {
        LocalDateTime startTime;
        LocalDateTime endTime;

        @BeforeAll
        public void init() {
            startTime = LocalDateTime.now();
            slowpokeThreads = new HashSet<>();
            System.out.println(String.format("Starting tests for %s at %s",
                this.getClass().getName(),
                startTime));
        }

        @Test
        public void testOne() throws InterruptedException {
            slowpokeThreads.add(Thread.currentThread());
            Thread.sleep(10_000);
        }

        @Test
        public void testTwo() throws InterruptedException {
            slowpokeThreads.add(Thread.currentThread());
            Thread.sleep(10_000);
        }

        @Test
        public void testThree() throws InterruptedException {
            slowpokeThreads.add(Thread.currentThread());
            Thread.sleep(10_000);

        }

        @AfterAll
        public void cleanUp() {
            endTime = LocalDateTime.now();
            String duration = Utils.durationFormatter(Duration.between(startTime, endTime).toString());
            System.out.println(String.format("Finished tests for %s at %s for a total duration of %s seconds",
                this.getClass().getName(),
                endTime,
                duration));
            System.out.println(String.format("%s used %d threads in execution",
                this.getClass().getName(),
                slowpokeThreads.size()));
        }
    }

    @Nested
    @Execution(CONCURRENT)
    @TestInstance(Lifecycle.PER_CLASS)
    class SpeedyGonzales {
        LocalDateTime startTime;
        LocalDateTime endTime;

        @BeforeAll
        public void init() {
            startTime = LocalDateTime.now();
            speedyGonzalesThreads = new HashSet<>();
            System.out.println(String.format("Starting tests for %s at %s",
                this.getClass().getName(),
                startTime));
        }

        @Test
        public void testOne() throws InterruptedException {
            speedyGonzalesThreads.add(Thread.currentThread());
            Thread.sleep(10_000);
        }

        @Test
        public void testTwo() throws InterruptedException {
            speedyGonzalesThreads.add(Thread.currentThread());
            Thread.sleep(10_000);
        }

        @Test
        public void testThree() throws InterruptedException {
            speedyGonzalesThreads.add(Thread.currentThread());
            Thread.sleep(10_000);
        }

        @AfterAll
        public void cleanUp() {
            endTime = LocalDateTime.now();
            String duration = Utils.durationFormatter(Duration.between(startTime, endTime).toString());
            System.out.println(String.format("Finished tests for %s at %s for a total duration of %s seconds",
                this.getClass().getName(),
                endTime,
                duration));
            System.out.println(String.format("%s used %d threads in execution",
                this.getClass().getName(),
                speedyGonzalesThreads.size()));
        }
    }
}

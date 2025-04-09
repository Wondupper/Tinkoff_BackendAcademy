package backend.academy;

import backend.academy.realization.MethodBenchmark;
import java.util.concurrent.TimeUnit;
import lombok.experimental.UtilityClass;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

@UtilityClass
@SuppressWarnings("MagicNumber")
public class Main {
    public static void main(String[] args) throws RunnerException {
        Options options = new OptionsBuilder()
            .include(MethodBenchmark.class.getSimpleName())
            .shouldFailOnError(true)
            .shouldDoGC(true)
            .mode(Mode.AverageTime)
            .timeUnit(TimeUnit.NANOSECONDS)
            .forks(1)
            .warmupForks(1)
            .warmupIterations(3)
            .warmupTime(TimeValue.seconds(5))
            .measurementIterations(5)
            .measurementTime(TimeValue.minutes(2))
            .build();

        new Runner(options).run();
    }
}

package benchmark;

import algorithms.Kadanes_Algorithm;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Fork;
import org.openjdk.jmh.annotations.Measurement;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Param;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.Warmup;
import org.openjdk.jmh.annotations.Level;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
@Warmup(iterations = 3)
@Measurement(iterations = 5)
@Fork(1)
@State(value = Scope.Thread)
public class KadanesBenchmark {

    @Param({"100", "1000", "10000", "100000"})
    private int n;

    private int[] arr;
    private final Random random = new Random();
    private Kadanes_Algorithm algo;

    @Setup(Level.Trial)
    public void setup() {
        algo = new Kadanes_Algorithm();
        arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(2001) - 1000; // [-1000, 1000]
        }
    }

    @Benchmark
    public Kadanes_Algorithm.Result measureKadanePerformance() {
        return algo.findMaxSubarray(arr);
    }
}

package cli;

import algorithms.Kadanes_Algorithm;
import metrics.PerformanceTracker;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BenchmarkRunner {

    private static final Random random = new Random();

    public static void main(String[] args) {
        int[] inputSizes = {100, 1000, 10000, 100000};
        String distribution = "random";
        int trials = 3;

        for (String arg : args) {
            if (arg.startsWith("--distribution=")) {
                distribution = arg.split("=")[1];
            } else if (arg.startsWith("--trials=")) {
                trials = Integer.parseInt(arg.split("=")[1]);
            } else if (arg.startsWith("--sizes=")) {
                inputSizes = Arrays.stream(arg.split("=")[1].split(","))
                        .mapToInt(Integer::parseInt)
                        .toArray();
            }
        }

        System.out.println("=== Kadane’s Algorithm Benchmark ===");
        System.out.println("Distribution: " + distribution);
        System.out.println("Trials per input: " + trials);
        System.out.println("Input sizes: " + Arrays.toString(inputSizes));

        Kadanes_Algorithm algo = new Kadanes_Algorithm();

        String outputFile = "docs/performance-plots/kadane_benchmark.csv";

        try (FileWriter writer = new FileWriter(outputFile)) {
            writer.write("n,trial,time(ms),comparisons,arrayAccesses\n");

            for (int n : inputSizes) {
                for (int t = 1; t <= trials; t++) {
                    int[] arr = generateArray(n, distribution);

                    PerformanceTracker.reset();
                    long start = System.nanoTime();
                    algo.findMaxSubarray(arr);
                    long end = System.nanoTime();

                    double elapsedMs = (end - start) / 1_000_000.0;

                    writer.write(String.format("%d,%d,%.3f,%d,%d\n",
                            n, t, elapsedMs, PerformanceTracker.getComparisons(), PerformanceTracker.getArrayAccesses()));

                    System.out.printf("n=%d trial=%d → %.3f ms (%d comparisons, %d accesses)\n",
                            n, t, elapsedMs, PerformanceTracker.getComparisons(), PerformanceTracker.getArrayAccesses());
                }
            }
            System.out.println("✅ Results saved to: " + outputFile);
        } catch (IOException e) {
            System.err.println("Error writing results: " + e.getMessage());
        }
    }
    private static int[] generateArray(int n, String type) {
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = random.nextInt(2001) - 1000;
        }

        switch (type) {
            case "sorted":
                Arrays.sort(arr);
                break;
            case "reversed":
                Arrays.sort(arr);
                reverse(arr);
                break;
            case "nearly-sorted":
                Arrays.sort(arr);
                for (int i = 0; i < n / 20; i++) {
                    int idx1 = random.nextInt(n);
                    int idx2 = random.nextInt(n);
                    int tmp = arr[idx1];
                    arr[idx1] = arr[idx2];
                    arr[idx2] = tmp;
                }
                break;
            default:
                break;
        }
        return arr;
    }
    private static void reverse(int[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            int tmp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = tmp;
        }
    }
}

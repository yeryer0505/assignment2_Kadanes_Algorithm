package metrics;

public class PerformanceTracker {
    private static long comparisons;
    private static long arrayAccesses;
    private static long startTime;
    private static long endTime;

    public static void incrementComparison(long n) { comparisons += n; }
    public static void incrementArrayAccess(long n) { arrayAccesses += n; }

    public static long getComparisons() { return comparisons; }
    public static long getArrayAccesses() { return arrayAccesses; }
    public long getElapsedTimeNs() { return endTime - startTime; }

    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { endTime = System.nanoTime(); }

    public static void reset() {
        comparisons = 0;
        arrayAccesses = 0;
        startTime = 0;
        endTime = 0;
    }

    public String toCsvRow(String algorithm, int n, String distribution, int trial) {
        return String.format(
                "%s,%d,%s,%d,%d,%d,%d",
                algorithm, n, distribution, trial,
                getElapsedTimeNs(), getComparisons(), getArrayAccesses()
        );
    }
}

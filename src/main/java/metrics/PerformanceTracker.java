package metrics;

public class PerformanceTracker {
    private long comparisons;
    private long arrayAccesses;
    private long startTime;
    private long endTime;

    public void incrementComparison(long n) { comparisons += n; }
    public void incrementArrayAccess(long n) { arrayAccesses += n; }

    public long getComparisons() { return comparisons; }
    public long getArrayAccesses() { return arrayAccesses; }
    public long getElapsedTimeNs() { return endTime - startTime; }

    public void startTimer() { startTime = System.nanoTime(); }
    public void stopTimer() { endTime = System.nanoTime(); }

    public void reset() {
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

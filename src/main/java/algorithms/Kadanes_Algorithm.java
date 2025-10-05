package algorithms;

import metrics.PerformanceTracker;

public class Kadanes_Algorithm {

    private final PerformanceTracker tracker = new PerformanceTracker();

    public Result findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Array must not be null or empty");

        tracker.reset();
        tracker.startTimer();

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;
        tracker.incrementArrayAccess(2);

        for (int i = 1; i < arr.length; i++) {
            tracker.incrementArrayAccess(1);
            tracker.incrementComparison(1);

            if (arr[i] > maxEndingHere + arr[i]) {
                maxEndingHere = arr[i];
                tempStart = i;
            } else {
                maxEndingHere += arr[i];
            }

            tracker.incrementComparison(1);
            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
        }

        tracker.stopTimer();
        return new Result(maxSoFar, start, end, tracker);
    }

    public static class Result {
        public final int maxSum;
        public final int start;
        public final int end;
        public final PerformanceTracker tracker;

        public Result(int sum, int s, int e, PerformanceTracker t) {
            this.maxSum = sum;
            this.start = s;
            this.end = e;
            this.tracker = t;
        }
    }
}

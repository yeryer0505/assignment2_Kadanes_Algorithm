package algorithms;

import metrics.PerformanceTracker;

public class Kadanes_Algorithm {

    public static class Result {
        public int maxSum;
        public int start;
        public int end;

        public Result(int maxSum, int start, int end) {
            this.maxSum = maxSum;
            this.start = start;
            this.end = end;
        }
    }

    public Result findMaxSubarray(int[] arr) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException("Array cannot be empty");

        int maxSoFar = arr[0];
        int maxEndingHere = arr[0];
        int start = 0, end = 0, tempStart = 0;

        for (int i = 1; i < arr.length; i++) {
            PerformanceTracker.incrementArrayAccess(2);
            int val = arr[i];

            if (maxEndingHere + val < val) {
                maxEndingHere = val;
                tempStart = i;
            } else {
                maxEndingHere += val;
            }
            PerformanceTracker.incrementComparison(1);

            if (maxEndingHere > maxSoFar) {
                maxSoFar = maxEndingHere;
                start = tempStart;
                end = i;
            }
            PerformanceTracker.incrementComparison(1);
        }

        return new Result(maxSoFar, start, end);
    }
}

package algorithms;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Kadanes_AlgorithmTest {

    @Test
    void testSinglePositiveElement() {
        Kadanes_Algorithm algo = new Kadanes_Algorithm();
        int[] arr = {5};
        Kadanes_Algorithm.Result r = algo.findMaxSubarray(arr);

        assertEquals(5, r.maxSum);
        assertEquals(0, r.start);
        assertEquals(0, r.end);
    }

    @Test
    void testMixedArray() {
        Kadanes_Algorithm algo = new Kadanes_Algorithm();
        int[] arr = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        Kadanes_Algorithm.Result r = algo.findMaxSubarray(arr);

        assertEquals(6, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(6, r.end);
    }

    @Test
    void testAllNegativeValues() {
        Kadanes_Algorithm algo = new Kadanes_Algorithm();
        int[] arr = {-5, -2, -7, -1, -3};
        Kadanes_Algorithm.Result r = algo.findMaxSubarray(arr);

        assertEquals(-1, r.maxSum);
        assertEquals(3, r.start);
        assertEquals(3, r.end);
    }

    @Test
    void testEmptyArrayThrowsException() {
        Kadanes_Algorithm algo = new Kadanes_Algorithm();
        assertThrows(IllegalArgumentException.class, () -> algo.findMaxSubarray(new int[]{}));
    }

    @Test
    void testMultipleMaxSubarrays() {
        Kadanes_Algorithm algo = new Kadanes_Algorithm();
        int[] arr = {1, 2, -1, 1, 2, -1, 1, 2};
        Kadanes_Algorithm.Result r = algo.findMaxSubarray(arr);

        assertEquals(7, r.maxSum);
    }
    @Test
    public void testEmptyArray() {
        Kadanes_Algorithm kadane = new Kadanes_Algorithm();
        assertEquals(0, kadane.findMaxSubarraySum(new int[]{}));
    }

    @Test
    public void testSingleElementArray() {
        Kadanes_Algorithm kadane = new Kadanes_Algorithm();
        assertEquals(5, kadane.findMaxSubarraySum(new int[]{5}));
        assertEquals(-3, kadane.findMaxSubarraySum(new int[]{-3}));
    }
}

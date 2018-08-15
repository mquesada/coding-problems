package com.algorithms.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Given a stream of integers and a window size, calculate the moving average of all integers in the sliding window.
 *
 * https://leetcode.com/problems/moving-average-from-data-stream/
 *
 * https://www.programcreek.com/2014/05/leetcode-moving-average-from-data-stream-java/
 */
public class MovingAverage {

    private static double[] movingAverage(int[] nums, int k) {
        if (nums == null || nums.length == 0 || k == 0) {
            return null;
        }

        double[] average = new double[nums.length];
        int currentSum = 0;
        Queue<Integer> window = new LinkedList<>();

        for (int i = 0; i < nums.length; i++) {
            int value = nums[i];
            currentSum += value;
            window.add(value);
            if (window.size() > k) {
                currentSum -= window.poll();
            }
            average[i] = currentSum / k;
        }

        return average;

    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(movingAverage(new int[]{1, 3, 5, 6, 8}, 3)));
    }

}

package com.algorithms.leetcode;

import java.util.Stack;

/**
 * Given n non-negative integers representing the histogram's bar height where the width of each bar is 1,
 * find the area of largest rectangle in the histogram.
 * <p>
 * https://leetcode.com/problems/largest-rectangle-in-histogram/description/
 */
public class LargestRectangleHistogram {

    public static int largestRectangleArea(int[] heights) {
        if (heights == null || heights.length == 0) {
            return 0;
        }

        int max = 0;
        Stack<Integer> stack = new Stack<>();

        int n = heights.length;
        for (int i = 0; i < n; i++) {
            int height = heights[i];

            if (stack.isEmpty() || height >= heights[stack.peek()]) {
                stack.push(i);
            } else {
                while (!stack.isEmpty() && heights[stack.peek()] > height) {
                    int area = heights[stack.pop()] * (stack.isEmpty() ? i : i - stack.peek() - 1);
                    max = Math.max(max, area);
                }
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int area = heights[stack.pop()] * (stack.isEmpty() ? n : n - stack.peek() - 1);
            max = Math.max(max, area);
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(largestRectangleArea(new int[]{2, 1, 5, 6, 2, 3}));
        System.out.println(largestRectangleArea(new int[]{1}));
    }
}

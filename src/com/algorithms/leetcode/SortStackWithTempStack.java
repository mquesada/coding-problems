package com.algorithms.leetcode;

import java.util.Stack;

/**
 * Given a stack of integers, sort it in ascending order using another temporary stack.
 *
 * https://www.geeksforgeeks.org/sort-stack-using-temporary-stack/
 */
public class SortStackWithTempStack {

    public static Stack<Integer> sort(Stack<Integer> stack) {
        if (stack == null || stack.isEmpty()) {
            return stack;
        }

        Stack<Integer> temp = new Stack<>();
        while (!stack.isEmpty()) {
            int value = stack.pop();

            while (!temp.isEmpty() && temp.peek() < value) {
                stack.push(temp.pop());
            }

            temp.push(value);
        }

        return temp;
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<>();
        int[] nums = new int[] {34, 3, 31, 98, 92, 23};
        for (int num : nums) {
            stack.push(num);
        }
        System.out.println(sort(stack));

        nums = new int[] {3, 5, 1, 4, 2, 8};
        for (int num : nums) {
            stack.push(num);
        }
        System.out.println(sort(stack));
    }

}

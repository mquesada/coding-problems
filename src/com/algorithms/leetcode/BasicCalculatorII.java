package com.algorithms.leetcode;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string contains only non-negative integers, +, -, *, / operators and empty spaces .
 * The integer division should truncate toward zero.
 * <p>
 * https://leetcode.com/problems/basic-calculator-ii/
 */
public class BasicCalculatorII {

    public static int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }
        Stack<Integer> stack = new Stack<>();
        int operand = 0;
        char operator = '+';
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (Character.isDigit(c)) {
                operand = operand * 10 + c - '0';
            }

            if ((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (operator == '-') {
                    stack.push(-operand);
                } else if (operator == '+') {
                    stack.push(operand);
                } else if (operator == '*') {
                    stack.push(stack.pop() * operand);
                } else if (operator == '/') {
                    stack.push(stack.pop() / operand);
                }
                operator = c;
                operand = 0;
            }
        }

        return stack.stream().mapToInt(Integer::intValue).sum();
    }

    public static void main(String[] args) {
        System.out.println(calculate("3+2*2"));
        System.out.println(calculate(" 3/2 "));
        System.out.println(calculate(" 3+5 / 2 "));
        System.out.println(calculate("12/3+3*2"));
        System.out.println(calculate("3+12/3*2"));
    }

}

package com.algorithms.leetcode;

import java.util.Stack;

/**
 * Implement a basic calculator to evaluate a simple expression string.
 * <p>
 * The expression string may contain open ( and closing parentheses ), the plus + or minus sign -,
 * non-negative integers and empty spaces .
 * <p>
 * https://leetcode.com/problems/basic-calculator/
 */
public class BasicCalculator {

    public static int calculate(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int result = 0;
        int operator = 1;
        int operand = 0;

        Stack<Integer> res = new Stack<>();
        Stack<Integer> ops = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c != ' ') {
                if (Character.isDigit(c)) {
                    operand = (operand * 10) + c - '0';
                    if (i < s.length() - 1 && !Character.isDigit(s.charAt(i + 1))
                            || i == s.length() - 1) {
                        result = result + (operator * operand);
                        operand = 0;
                        operator = 1;
                    }
                } else if (c == '+' || c == '-') {
                    operator = c == '+' ? 1 : -1;
                } else if (c == '(') {
                    res.push(result);
                    result = 0;
                    ops.push(operator);
                    operator = 1;
                } else if (c == ')') {
                    result = res.pop() + (ops.pop() * result);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {

        System.out.println(calculate("1 + 1"));
        System.out.println(calculate(" 2-1 + 2"));
        System.out.println(calculate("(1+(4+5+2)-3)+(6+8)"));
        System.out.println(calculate("((1+2) + 3) - 1"));
        System.out.println(calculate("1+2 - (1+1) + 2"));
        System.out.println(calculate("(10 + 3) + (4 + (2+1))"));
        System.out.println(calculate("1-(5)"));
    }
}

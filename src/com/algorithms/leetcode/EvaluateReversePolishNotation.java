package com.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

/**
 * Evaluate the value of an arithmetic expression in Reverse Polish Notation.
 * <p>
 * Valid operators are +, -, *, /. Each operand may be an integer or another expression.
 * <p>
 * Note:
 * <p>
 * Division between two integers should truncate toward zero.
 * The given RPN expression is always valid. That means the expression would always evaluate to a result
 * and there won't be any divide by zero operation.
 * <p>
 * https://leetcode.com/problems/evaluate-reverse-polish-notation/description/
 */
public class EvaluateReversePolishNotation {

    private static final Set<String> OPS = new HashSet<String>() {{
        add("+");
        add("-");
        add("/");
        add("*");
    }};

    public static int evalRPN(String[] tokens) {
        if (tokens == null || tokens.length == 0) {
            return 0;
        }

        Stack<Integer> result = new Stack<>();
        for (String token : tokens) {
            if (OPS.contains(token)) {
                int val2 = result.pop();
                int val1 = result.pop();
                switch (token) {
                    case "+":
                        result.push(val1 + val2);
                        break;
                    case "-":
                        result.push(val1 - val2);
                        break;
                    case "/":
                        result.push(val1 / val2);
                        break;
                    case "*":
                        result.push(val1 * val2);
                        break;
                }
            } else {
                result.push(Integer.valueOf(token));
            }
        }
        return result.pop();
    }

    public static void main(String[] args) {
        System.out.println(evalRPN(new String[]{"2", "1", "+", "3", "*"}));
        System.out.println(evalRPN(new String[]{"4", "13", "5", "/", "+"}));
        System.out.println(evalRPN(new String[]{"10", "6", "9", "3", "+", "-11", "*", "/", "*", "17", "+", "5", "+"}));
    }


}

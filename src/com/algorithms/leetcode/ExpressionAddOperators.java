package com.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * https://leetcode.com/problems/expression-add-operators/
 * <p>
 * https://www.geeksforgeeks.org/print-all-possible-expressions-that-evaluate-to-a-target/
 */
public class ExpressionAddOperators {

    private static void addOperators(List<String> res, StringBuilder sb, String numStr,
                                     int idx, int target, long currentValue, long last) {
        if (idx == numStr.length()) {
            if (target == currentValue) {
                res.add(sb.toString());
            }
            return;
        }

        int len = sb.length();
        long number = 0;
        for (int i = idx; i < numStr.length(); i++) {
            if (numStr.charAt(idx) == '0' && i != idx) {
                break;
            }

            number = number * 10 + (numStr.charAt(i) - '0');

            if (idx == 0) {
                addOperators(res, sb.append(number), numStr, i + 1, target, number, number);
                sb.setLength(len);
            } else {
                addOperators(res, sb.append("+").append(number), numStr, i + 1, target, currentValue + number, number);
                sb.setLength(len);
                addOperators(res, sb.append("-").append(number), numStr, i + 1, target, currentValue - number, -number);
                sb.setLength(len);
                addOperators(res, sb.append("*").append(number), numStr, i + 1, target, currentValue - last + last * number, last * number);
                sb.setLength(len);
            }
        }
    }

    public static List<String> addOperators(String num, int target) {
        List<String> res = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        addOperators(res, sb, num, 0, target, 0, 0);
        return res;
    }

    public static void main(String[] args) {
        System.out.println(addOperators("123", 6));
        System.out.println(addOperators("232", 8));
        System.out.println(addOperators("105", 5));
    }
}

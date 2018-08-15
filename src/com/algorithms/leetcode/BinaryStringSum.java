package com.algorithms.leetcode;

/**
 * Given two binary strings, return their sum (also a binary string).
 * <p>
 * The input strings are both non-empty and contains only characters 1 or 0.
 * <p>
 * https://leetcode.com/problems/add-binary/
 */
public class BinaryStringSum {

    private static String fillZeros(String s, int count) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < count; i++) {
            sb.append('0');
        }
        sb.append(s);

        return sb.toString();
    }

    public static String addBinary(String a, String b) {
        if (a == null || a.isEmpty()) {
            return b;
        }

        if (b == null || b.isEmpty()) {
            return a;
        }

        if (a.length() < b.length()) {
            a = fillZeros(a, b.length() - a.length());
        } else if (b.length() < a.length()) {
            b = fillZeros(b, a.length() - b.length());
        }

        StringBuilder result = new StringBuilder();
        int carry = 0;
        for (int i = a.length() - 1; i >= 0; i--) {
            int vA = a.charAt(i) - '0';
            int vB = b.charAt(i) - '0';
            int sum = (vA ^ vB) ^ carry;
            result.append(sum);
            carry = (vA & vB) | (vA & carry) | (vB & carry);
        }
        if (carry == 1) {
            result.append(carry);
        }
        result.reverse();
        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(addBinary("11", "1"));
        System.out.println(addBinary("1010", "1011"));
        System.out.println(addBinary("100", "10"));
        System.out.println(addBinary(null, "10"));
        System.out.println(addBinary("10101", null));
        System.out.println(addBinary("10101", "000"));
    }
}

package com.algorithms.leetcode;

/**
 * Given two strings A and B, find the minimum number of times A has to be repeated such that B is a substring of it.
 * If no such solution, return -1.
 * <p>
 * For example, with A = "abcd" and B = "cdabcdab".
 * <p>
 * Return 3, because by repeating A three times (“abcdabcdabcd”), B is a substring of it; and B is not a substring of
 * A repeated two times ("abcdabcd").
 * <p>
 * Note:
 * The length of A and B will be between 1 and 10000.
 * <p>
 * https://leetcode.com/problems/repeated-string-match/description/
 */
public class RepeatedStringMatch {

    public static int repeatedStringMatch(String A, String B) {
        int count = 1;

        StringBuilder sb = new StringBuilder(A);

        while (sb.length() < B.length()) {
            sb.append(A);
            count++;
        }

        if (sb.indexOf(B) >= 0) {
            return count;
        }

        sb.append(A);
        if (sb.indexOf(B) >= 0) {
            return count + 1;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(repeatedStringMatch("abcd", "cdabcdab"));
        System.out.println(repeatedStringMatch("dabc", "cdabcdab"));
    }

}

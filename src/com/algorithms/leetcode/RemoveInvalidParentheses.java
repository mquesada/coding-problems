package com.algorithms.leetcode;

import java.util.*;

/**
 * Remove the minimum number of invalid parentheses in order to make the input string valid.
 * Return all possible results.
 * <p>
 * Note: The input string may contain letters other than the parentheses ( and ).
 * <p>
 * https://leetcode.com/problems/remove-invalid-parentheses/
 */
public class RemoveInvalidParentheses {

    private static boolean isValid(String text) {
        int count = 0;

        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            if (c == '(' || c == ')') {
                if (c == '(') {
                    count++;
                } else if (count-- == 0) {
                    return false;
                }
            }
        }

        return count == 0;
    }

    public static List<String> removeInvalidParentheses(String s) {
        if (s == null) {
            return Collections.emptyList();
        }
        List<String> result = new ArrayList<>();

        Set<String> visited = new HashSet<>();
        Queue<String> queue = new LinkedList<>();
        queue.offer(s);

        boolean found = false;
        while (!queue.isEmpty()) {
            String s2 = queue.poll();
            if (isValid(s2)) {
                result.add(s2);
                found = true;
            }

            // Process further if we haven't found solutions already
            if (!found) {
                for (int i = 0; i < s2.length(); i++) {
                    char c = s2.charAt(i);
                    if (c == '(' || c == ')') {
                        String s3 = s2.substring(0, i) + s2.substring(i + 1);
                        if (!visited.contains(s3)) {
                            queue.offer(s3);
                            visited.add(s3);
                        }
                    }
                }
            }
        }

        return result;
    }

    public static void main(String[] args) {
        System.out.println(removeInvalidParentheses("()())()"));
        System.out.println(removeInvalidParentheses("(a)())()"));
        System.out.println(removeInvalidParentheses(")("));
        System.out.println(removeInvalidParentheses(")(()"));
        System.out.println(removeInvalidParentheses("())("));
        System.out.println(removeInvalidParentheses("()()("));
        System.out.println(removeInvalidParentheses("()(((((((()"));
    }
}

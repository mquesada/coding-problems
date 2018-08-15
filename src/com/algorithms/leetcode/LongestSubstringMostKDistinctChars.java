package com.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given a string you need to print longest possible substring that has exactly M unique characters.
 * If there are more than one substring of longest possible length, then print any one of them
 * <p>
 * https://leetcode.com/problems/longest-substring-with-at-most-k-distinct-characters/
 * <p>
 * https://www.geeksforgeeks.org/find-the-longest-substring-with-k-unique-characters-in-a-given-string/
 */
public class LongestSubstringMostKDistinctChars {

    private static String getLongest(String s, String longest, int start, int end) {
        if (end - start > longest.length()) {
            longest = s.substring(start, end);
        }
        return longest;
    }

    public static String lengthOfLongestSubstring(String s, int k) {
        if (s == null || s.length() < 2) {
            return s;
        }

        String longest = "";
        int start = 0;
        int end = 0;
        Map<Character, Integer> unique = new HashMap<>(s.length());

        while (end < s.length()) {
            char c = s.charAt(end);
            if (unique.containsKey(c)) {
                unique.put(c, unique.get(c) + 1);
            } else if (unique.size() < k) {
                unique.put(c, 1);
            } else {
                while (start < s.length()
                        && unique.size() >= k) {

                    char cs = s.charAt(start);
                    unique.put(cs, unique.get(cs) - 1);
                    if (unique.get(cs) == 0) {
                        unique.remove(cs);
                    }
                    start++;
                }
                unique.put(c, 1);
            }
            end++;

            if (unique.size() == k) {
                longest = getLongest(s, longest, start, end);
            }
        }

        return unique.size() == k ? longest : "ERROR";
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("aabbcc", 1));
        System.out.println(lengthOfLongestSubstring("aabbcc", 2));
        System.out.println(lengthOfLongestSubstring("aabbcc", 3));
        System.out.println(lengthOfLongestSubstring("aaabbb", 3));
        System.out.println(lengthOfLongestSubstring("eceba", 2));
        System.out.println(lengthOfLongestSubstring("abcbbbbcccbdddadacb", 2));
        System.out.println(lengthOfLongestSubstring("abcddcccbdddaaaaddd", 2));
    }
}

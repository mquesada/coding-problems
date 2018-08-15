package com.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

/**
 * Given a string, find the length of the longest substring without repeating characters.
 * <p>
 * Examples:
 * <p>
 * Given "abcabcbb", the answer is "abc", which the length is 3.
 * <p>
 * Given "bbbbb", the answer is "b", with the length of 1.
 * <p>
 * Given "pwwkew", the answer is "wke", with the length of 3. Note that the answer must be a substring,
 * "pwke" is a subsequence and not a substring.
 * <p>
 * https://leetcode.com/problems/longest-substring-without-repeating-characters/description/
 */
public class LongestSubstringNonRepeatingChars {

    public static int lengthOfLongestSubstring(String s) {
        if (s == null) {
            return 0;
        }
        if (s.length() < 2) {
            return s.length();
        }

        int longest = 1;
        int start = 0;
        int end = 1;
        Set<Character> unique = new HashSet<>(s.length());
        unique.add(s.charAt(start));

        while (end < s.length()) {
            char c = s.charAt(end);
            if (!unique.contains(c)) {
                unique.add(c);
                end++;

                longest = Math.max(longest, end - start);
            } else {
                while (start < s.length()
                        && unique.contains(c)) {
                    unique.remove(s.charAt(start));
                    start++;
                }
            }
        }

        return longest;
    }

    public static void main(String[] args) {
        System.out.println(lengthOfLongestSubstring("abcabcbb"));
        System.out.println(lengthOfLongestSubstring("bbbbb"));
        System.out.println(lengthOfLongestSubstring("pwwkew"));
        System.out.println(lengthOfLongestSubstring("a"));
        System.out.println(lengthOfLongestSubstring("ABDEFGABEF"));
        System.out.println(lengthOfLongestSubstring("GEEKSFORGEEKS"));
        System.out.println(lengthOfLongestSubstring("GEEKSFORGEEKSABCDFM"));
        System.out.println(lengthOfLongestSubstring("DVDF"));
    }
}

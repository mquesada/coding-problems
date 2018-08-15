package com.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers using the following mapping:
 * <p>
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 * <p>
 * Given a non-empty string containing only digits, determine the total number of ways to decode it.
 * <p>
 * https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {

    private static int numDecodings(String s, int idx, Map<Integer, Integer> cache) {
        if (idx > s.length()) {
            return 0;
        }
        if (idx == s.length()) {
            return 1;
        }

        if (cache.containsKey(idx)) {
            return cache.get(idx);
        }

        int ways = 0;
        int digit = s.charAt(idx) - '0';
        if (digit > 0) {
            ways = numDecodings(s, idx + 1, cache);
            if (idx < s.length() - 1) {
                int num = (digit * 10) + (s.charAt(idx + 1) - '0');
                if (num <= 26) {
                    ways += numDecodings(s, idx + 2, cache);
                }
            }
        }
        cache.put(idx, ways);

        return ways;
    }

    public static int numDecodings(String s) {
        return numDecodings(s, 0, new HashMap<>());
    }

    public static void main(String[] args) {
        System.out.println(numDecodings("226"));
        System.out.println(numDecodings("12"));
        System.out.println(numDecodings("295"));
        System.out.println(numDecodings("10"));
        System.out.println(numDecodings("0"));
        System.out.println(numDecodings("20"));
        System.out.println(numDecodings("30"));
        System.out.println(numDecodings("00"));
        System.out.println(numDecodings("7541387519572282368613553811323167125532172369624572591562685959575371877973171856836975137559677665"));
    }
}

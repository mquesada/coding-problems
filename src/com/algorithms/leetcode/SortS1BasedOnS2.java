package com.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Given two lowercase strings, S1 and S2, sort S1 in same order as S2.
 * If a character in S1 doesn't exist in S2, put them at the end.
 * If S1 is "program" and S2 is "grapo", then return "grrapom". 
 */
public class SortS1BasedOnS2 {


    public static String sort(String s1, String s2) {
        if (s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return s1;
        }

        Map<Character, Integer> charPos = new HashMap<>(s2.length());
        for (int i = 0; i < s2.length(); i++) {
            charPos.putIfAbsent(s2.charAt(i), i);
        }

        StringBuilder[] sortArr = new StringBuilder[s1.length()];

        for (int i = 0; i < s1.length(); i++) {
            char c = s1.charAt(i);
            int pos = charPos.getOrDefault(c, s1.length() - 1);
            if (sortArr[pos] == null) {
                sortArr[pos] = new StringBuilder();
            }
            sortArr[pos].append(c);
        }

        StringBuilder result = new StringBuilder(s1.length());
        for (StringBuilder sb : sortArr) {
            if (sb != null) {
                result.append(sb.toString());
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        System.out.println(sort("program", "grapo"));
        System.out.println(sort("icandothis", "tsohdca"));
    }
}

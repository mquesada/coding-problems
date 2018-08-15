package com.algorithms.leetcode;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
 * <p>
 * For example, two is written as II in Roman numeral, just two one's added together. Twelve is written as, XII, which is simply X + II. The number twenty seven is written as XXVII, which is XX + V + II.
 * <p>
 * Roman numerals are usually written largest to smallest from left to right. However, the numeral for four is not IIII. Instead, the number four is written as IV. Because the one is before the five we subtract it making four. The same principle applies to the number nine, which is written as IX. There are six instances where subtraction is used:
 * <p>
 * I can be placed before V (5) and X (10) to make 4 and 9.
 * X can be placed before L (50) and C (100) to make 40 and 90.
 * C can be placed before D (500) and M (1000) to make 400 and 900.
 * Given a roman numeral, convert it to an integer. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * https://leetcode.com/problems/roman-to-integer/description/
 */
public class RomanToInt {

    private static Map<Character, Integer> ROMAN_TO_INT = new LinkedHashMap<>();

    static {
        ROMAN_TO_INT.put('M', 1000);
        ROMAN_TO_INT.put('D', 500);
        ROMAN_TO_INT.put('C', 100);
        ROMAN_TO_INT.put('L', 50);
        ROMAN_TO_INT.put('X', 10);
        ROMAN_TO_INT.put('V', 5);
        ROMAN_TO_INT.put('I', 1);
    }

    public int romanToInt(String s) {
        if (s == null || s.isEmpty()) {
            return 0;
        }

        int number = 0;
        int previousValue = 0;
        for (int i = 0; i < s.length(); i++) {
            int value = ROMAN_TO_INT.get(s.charAt(i));
            if (value > previousValue) {
                value -= 2 * previousValue;
            }
            number += value;
            previousValue = value;
        }

        return number;
    }

}

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
 * Given an integer, convert it to a roman numeral. Input is guaranteed to be within the range from 1 to 3999.
 * <p>
 * https://leetcode.com/problems/integer-to-roman/description/
 */
public class IntegerToRoman {

    private static Map<Integer, String> romans = new LinkedHashMap<>();

    static {
        romans.put(1000, "M");
        romans.put(900, "CM");
        romans.put(500, "D");
        romans.put(400, "CD");
        romans.put(100, "C");
        romans.put(90, "XC");
        romans.put(50, "L");
        romans.put(40, "XL");
        romans.put(10, "X");
        romans.put(9, "IX");
        romans.put(5, "V");
        romans.put(4, "IV");
        romans.put(1, "I");
    }

    public static String intToRoman(int num) {
        if (num == 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Integer, String> entry : romans.entrySet()) {
            if (num >= entry.getKey()) {
                int count = num / entry.getKey();
                num -= entry.getKey() * count;
                for (int i = 0; i < count; i++) {
                    sb.append(entry.getValue());
                }
                if (num == 0) {
                    break;
                }
            }
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(intToRoman(3332));
        System.out.println(intToRoman(1904));
        System.out.println(intToRoman(1908));
        System.out.println(intToRoman(58));
        System.out.println(intToRoman(1954));
        System.out.println(intToRoman(1994));
        System.out.println(intToRoman(2));
        System.out.println(intToRoman(6));
        System.out.println(intToRoman(9));
    }

}

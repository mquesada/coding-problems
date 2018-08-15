package com.algorithms.leetcode;

/**
 * Convert a non-negative integer to its english words representation. Given input is guaranteed to be less than 2^31 - 1.
 * <p>
 * https://leetcode.com/problems/integer-to-english-words/
 */
public class ConvertNumberToWords {

    private final static String[] LESS20 = new String[]{"One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine",
            "Ten", "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"};
    private final static String[] LESS100 = new String[]{"Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"};
    private final static String[] LESSBILLION = new String[]{"Billion", "Million", "Thousand", "Hundred"};

    public static String numberToWords(int num) {
        if (num == 0) {
            return "Zero";
        }

        if (num < 20) {
            return LESS20[num - 1];
        }

        if (num < 100) {
            int div = num / 10;
            num -= div * 10;
            return LESS100[div - 2] + (num > 0 ? " " + LESS20[num - 1] : "");
        }

        StringBuilder result = new StringBuilder();

        int i = 0;
        int[] ranges = new int[]{1000 * 1000000, 1000000, 1000, 100};
        while (num > 0 && i < ranges.length) {
            int range = ranges[i];
            if (num >= range) {
                int div = num / range;
                result.append(numberToWords(div)).append(" ").append(LESSBILLION[i]).append(" ");
                num -= div * range;
            }
            i++;
        }

        if (num > 0) {
            result.append(numberToWords(num));
        }

        return result.toString().trim();
    }


    public static void main(String[] args) {
        System.out.println(Integer.MAX_VALUE);
        System.out.println(numberToWords(Integer.MAX_VALUE));
        System.out.println(numberToWords(123));
        System.out.println(numberToWords(12345));
        System.out.println(numberToWords(1234567));
        System.out.println(numberToWords(20));
        System.out.println(numberToWords(21));
        System.out.println(numberToWords(99));
        System.out.println(numberToWords(999));
        System.out.println(numberToWords(4));
    }
}

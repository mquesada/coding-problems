package com.algorithms.leetcode;

/**
 * Suppose you have a long flowerbed in which some of the plots are planted and some are not.
 * However, flowers cannot be planted in adjacent plots - they would compete for water and both would die.
 * <p>
 * Given a flowerbed (represented as an array containing 0 and 1, where 0 means empty and 1 means not empty),
 * and a number n, return if n new flowers can be planted in it without violating the no-adjacent-flowers rule.
 * <p>
 * https://leetcode.com/problems/can-place-flowers
 */
public class CanPlaceFlowerBed {

    public static boolean canPlaceFlowers(int[] flowerbed, int n) {
        if (flowerbed == null || flowerbed.length == 0) {
            return false;
        }
        if (n == 0) {
            return true;
        }

        int i = 0;
        while (i < flowerbed.length && n > 0) {
            if (flowerbed[i] == 1) {
                i += 2;
            } else if (i == flowerbed.length - 1 || (i < flowerbed.length - 1 && flowerbed[i + 1] == 0)) {
                n--;
                i += 2;
            } else {
                i += 3;
            }
        }

        return n == 0;
    }

    public static void main(String[] args) {
        System.out.println(canPlaceFlowers(new int[]{1, 0, 0, 0, 1, 0, 0}, 2));
    }
}

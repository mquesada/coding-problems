package com.algorithms.leetcode;

import java.util.HashMap;
import java.util.Map;

public class SparseVectors {

    public static int dotProduct(int[][] a1, int[][] a2) {
        if (a1 == null || a2 == null) {
            return 0;
        }

        Map<Integer, Integer> map1 = new HashMap<>();
        for (int[] pair : a1) {
            map1.put(pair[0], pair[1]);
        }
        Map<Integer, Integer> map2 = new HashMap<>();
        for (int[] pair : a2) {
            map2.put(pair[0], pair[1]);
        }

        int product = 0;

        for (Map.Entry<Integer, Integer> entry : map1.entrySet()) {
            if (map2.containsKey(entry.getKey())) {
                product += entry.getValue() * map2.get(entry.getKey());
            }
        }

        return product;
    }

    public static void main(String[] args) {
        System.out.println(dotProduct(new int[][]{{1,4},{4,2},{5,3}}, new int[][]{{1,7},{2,5},{5,1}}));
    }
}

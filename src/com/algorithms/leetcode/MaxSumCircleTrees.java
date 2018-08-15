package com.algorithms.leetcode;

import java.util.LinkedList;
import java.util.Queue;

public class MaxSumCircleTrees {

    public static long maxValue(int[] trees, int time) {
        if (trees == null || trees.length == 0 || time == 0) {
            return 0;
        }

        int sum = 0;
        if (time >=  trees.length) {
            for (int tree : trees) {
                sum += tree;
            }
            return sum;
        }

        int max = Integer.MIN_VALUE;
        int round = 0;
        int i = 0;
        Queue<Integer> queue = new LinkedList<>();
        while (round < 2 && i < trees.length) {
            int value = trees[i];
            sum += value;
            queue.add(value);

            if (queue.size() == time) {
                max = Math.max(max, sum);
            } else if (queue.size() > time) {
                sum -= queue.poll();
                max = Math.max(max, sum);
            }

            if (i == trees.length - 1 && round == 0 && time > 1) {
                i = 0;
                round++;
            } else if (round == 1 && time - 1 - (i + 1) == 0) {
                break;
            } else {
                i++;
            }
        }

        return max;
    }

    public static void main(String[] args) {
        System.out.println(maxValue(new int[]{9,3,1,8}, 2));
        System.out.println(maxValue(new int[]{3, 8, 4, 7, 8, 9, 5, 0, 8}, 7));
        System.out.println(maxValue(new int[]{3, 8, 4, 7, 8, 9, 5, 0, 8}, 3));
        System.out.println(maxValue(new int[]{3, 8, 4, 7, 8, 9, 5, 0, 8}, 9));
        System.out.println(maxValue(new int[]{3, 8, 4, 7, 8, 9, 5, 0, 8}, 12));
        System.out.println(maxValue(new int[]{3, 8, 4, 7, 8, 9, 5, 0, 8}, 1));
    }

}

package com.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * On an infinite number line (x-axis), we drop given squares in the order they are given.
 * <p>
 * The i-th square dropped (positions[i] = (left, side_length)) is a square with the left-most point being
 * positions[i][0] and sidelength positions[i][1].
 * <p>
 * The square is dropped with the bottom edge parallel to the number line, and from a higher height than all currently
 * landed squares. We wait for each square to stick before dropping the next.
 * <p>
 * The squares are infinitely sticky on their bottom edge, and will remain fixed to any positive length surface they
 * touch (either the number line or another square). Squares dropped adjacent to each other will not stick together
 * prematurely.
 * <p>
 * Return a list ans of heights. Each height ans[i] represents the current highest height of any square we have dropped,
 * after dropping squares represented by positions[0], positions[1], ..., positions[i].
 * <p>
 * https://leetcode.com/problems/falling-squares/
 */
public class FallingSquares {

    private static boolean intersect(int x1, int y1, int x2, int y2) {
        int s = Math.max(x1, x2);
        int e = Math.min(y1, y2);
        return (e - s) > 0;
    }

    public static List<Integer> fallingSquares(int[][] positions) {
        int[] answer = new int[positions.length];
        for (int i = 0; i < positions.length; i++) {
            int x1 = positions[i][0];
            int size = positions[i][1];
            int y1 = x1 + size;
            answer[i] += size;

            for (int j = i + 1; j < positions.length; j++) {
                int x2 = positions[j][0];
                int y2 = x2 + positions[j][1];
                if (intersect(x1, y1, x2, y2)) { //intersect
                    answer[j] = Math.max(answer[j], answer[i]);
                }
            }
        }

        List<Integer> result = new ArrayList<>(positions.length);
        int max = -1;
        for (int x : answer) {
            max = Math.max(max, x);
            result.add(max);
        }
        return result;
    }

    public static void main(String[] args) {

//        int[][] positions = new int[][]{{1, 2}, {2, 3}, {6, 1}};
//        int[][] positions = new int[][]{{1, 2}, {3, 2}, {4, 2}, {1, 2}};
//        int[][] positions = new int[][]{{1, 2}, {2, 2}, {1, 2}, {5, 7}};
//        int[][] positions = new int[][]{{100, 100}, {200, 100}};
//        int[][] positions = new int[][]{{9, 7}, {1, 9}, {3, 1}};
        int[][] positions = new int[][]{{50, 47}, {95, 48}, {88, 77}, {84, 3}, {53, 87}, {98, 79}, {88, 28}, {13, 22}, {53, 73}, {85, 55}};

        System.out.println(fallingSquares(positions));

    }

}

package com.algorithms.leetcode;

/**
 * Given a 2d grid map of '1's (land) and '0's (water), count the number of islands.
 * An island is surrounded by water and is formed by connecting adjacent lands horizontally or vertically.
 * You may assume all four edges of the grid are all surrounded by water.
 * <p>
 * https://leetcode.com/problems/number-of-islands/
 */
public class NumberOfIslands {

    private static void markIslands(char[][] binaryMatrix, int row, int col) {
        int n = binaryMatrix.length;
        int m = binaryMatrix[0].length;

        if (row < 0 || row == n
                || col < 0 || col == m
                || binaryMatrix[row][col] == 'x'
                || binaryMatrix[row][col] == '0') {
            return;
        }

        binaryMatrix[row][col] = 'x';

        markIslands(binaryMatrix, row, col - 1); // left neighbor
        markIslands(binaryMatrix, row, col + 1); // right neighbor
        markIslands(binaryMatrix, row - 1, col); // top neighbor
        markIslands(binaryMatrix, row + 1, col); // bottom neighbor
    }

    static int numIslands(char[][] binaryMatrix) {
        if (binaryMatrix == null) {
            return 0;
        }

        int count = 0;
        for (int i = 0; i < binaryMatrix.length; i++) {
            for (int j = 0; j < binaryMatrix[0].length; j++) {
                if (binaryMatrix[i][j] == '1') {
                    count++;
                    markIslands(binaryMatrix, i, j);
                }
            }
        }

        return count;
    }

}

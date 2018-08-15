package com.algorithms.leetcode;

/**
 * Given a non-empty 2D array grid of 0's and 1's, an island is a group of 1's (representing land)
 * connected 4-directionally (horizontal or vertical.) You may assume all four edges of the grid are surrounded by water.
 * <p>
 * Find the maximum area of an island in the given 2D array. (If there is no island, the maximum area is 0.)
 * <p>
 * https://leetcode.com/problems/max-area-of-island/
 */
public class MaxAreaOfIsland {

    private static int maxAreaOfIsland(int[][] grid, int row, int col) {
        if (row < 0 || row > grid.length - 1
                || col < 0 || col > grid[row].length - 1
                || grid[row][col] == -1) {
            return 0;
        }

        if (grid[row][col] == 0) {
            grid[row][col] = -1;
            return 0;
        }

        grid[row][col] = -1;
        int count = 1;

        count += maxAreaOfIsland(grid, row - 1, col);
        count += maxAreaOfIsland(grid, row, col - 1);
        count += maxAreaOfIsland(grid, row, col + 1);
        count += maxAreaOfIsland(grid, row + 1, col);

        return count;
    }

    public int maxAreaOfIsland(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }

        int region = 0;
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                region = Math.max(region, maxAreaOfIsland(grid, row, col));
            }
        }

        return region;

    }

}

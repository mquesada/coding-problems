package com.algorithms.leetcode;

import java.util.*;

/**
 * There is a 2d array and gbikes are located in that location. There is a person and he wants to know the nearest
 * location of the bike which is available for him(there can be more than 1 nearest bike).
 * Person can only move left, right, up or down. Output should be the distance in int.
 */
public class GBikesLocation {

    private static class Node {
        private int row;
        private int col;
        private int distance;

        public Node(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public Node(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Node node = (Node) o;
            return row == node.row &&
                    col == node.col;
        }

        @Override
        public int hashCode() {
            return Objects.hash(row, col);
        }
    }

    private static final int[][] DIRECTIONS = new int[][]{{0, -1}, {0, 1}, {-1, 0}, {1, 0}};

    private static boolean isValid(int row, int col, int rows, int cols) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }

    public static int findClosestBike(int[][] locations, int row, int col) {
        int rows = locations.length;
        int cols = locations[0].length;

        if (!isValid(row, col, rows, cols)) {
            return -1;
        }

        if (locations[row][col] == 1) {
            return 0;
        }

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(row, col));

        Set<Node> visited = new HashSet<>();

        while (!queue.isEmpty()) {
            Node node = queue.poll();

            if (!visited.contains(node)) {
                visited.add(node);

                if (locations[node.row][node.col] == 1) {
                    return node.distance;
                }

                for (int[] direction : DIRECTIONS) {
                    int nRow = node.row + direction[0];
                    int nCol = node.col + direction[1];
                    if (isValid(nRow, nCol, rows, cols)) {
                        queue.offer(new Node(nRow, nCol, node.distance + 1));
                    }
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println(findClosestBike(new int[][]{
                {0, 0, 1, 0, 0},
                {1, 1, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 1, 0, 0},
                {0, 1, 0, 0, 0}
        }, 4, 0));

        System.out.println(findClosestBike(new int[][]{
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0}
        }, 4, 0));

        System.out.println(findClosestBike(new int[][]{
                {0, 0, 0, 0, 1},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 0, 0},
                {0, 0, 0, 1, 0},
                {0, 0, 0, 0, 0}
        }, 2, 2));
    }


}

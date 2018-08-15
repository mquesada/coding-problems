package com.algorithms.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * The n-queens puzzle is the problem of placing n queens on an n×n chessboard such that no two queens attack each other.
 * <p>
 * Given an integer n, return all distinct solutions to the n-queens puzzle.
 * <p>
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate
 * a queen and an empty space respectively.
 * <p>
 * https://leetcode.com/problems/n-queens/description/
 */
public class NQueens {

    private static void addResult(boolean[][] board, List<List<String>> result) {
        List<String> row = new ArrayList<>();
        for (int i = 0; i < board.length; i++) {
            StringBuilder sb = new StringBuilder(board.length);
            for (int j = 0; j < board.length; j++) {
                sb.append(board[i][j] ? "Q" : ".");
            }
            row.add(sb.toString());
        }
        result.add(row);
    }

    private static boolean isValid(boolean[][] board, int row, int col) {
        int N = board.length;
        int i, j;

        // Check previous rows same column
        for (i = 0; i < row; i++) {
            if (board[i][col]) {
                return false;
            }
        }

        // Check upper left diagonal
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--) {
            if (board[i][j]) {
                return false;
            }
        }

        // Check upper right diagonal
        for (i = row, j = col; i >= 0 && j < N; i--, j++) {
            if (board[i][j]) {
                return false;
            }
        }

        return true;
    }

    private static void solveNQueens(boolean[][] board, int n, List<List<String>> result) {
        if (n == board.length) {
            addResult(board, result);
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if (isValid(board, n, i)) {
                board[n][i] = true;
                solveNQueens(board, n + 1, result);
                board[n][i] = false;
            }
        }
    }

    public static List<List<String>> solveNQueens(int n) {
        boolean[][] board = new boolean[n][n];
        List<List<String>> result = new ArrayList<>();
        solveNQueens(board, 0, result);
        return result;
    }

    public static void main(String[] args) {
        System.out.println(solveNQueens(8));
    }
}

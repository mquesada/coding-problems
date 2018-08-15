package com.algorithms.leetcode;

/**
 * Find the total area covered by two rectilinear rectangles in a 2D plane.
 * <p>
 * Each rectangle is defined by its bottom left corner and top right corner as shown in the figure.
 * <p>
 * https://leetcode.com/problems/rectangle-area/
 */
public class RectangleArea {

    public static int computeArea(int A, int B, int C, int D, int E, int F, int G, int H) {

        int areaOfA = (C - A) * (D - B);
        int areaOfB = (G - E) * (H - F);

        int x1 = Math.max(A, E);
        int x2 = Math.min(C, G);
        int y1 = Math.max(B, F);
        int y2 = Math.min(D, H);

        int overlapArea = 0;
        if (x2 > x1 && y2 > y1) {
            overlapArea = (x2 - x1) * (y2 - y1);
        }

        return areaOfA + areaOfB - overlapArea;
    }

    public static void main(String[] args) {
        System.out.println(computeArea(-3, 0, 3, 4, 0, -1, 9, 2));
        System.out.println(computeArea(-1500000001, 0, -1500000000, 1, 1500000000, 0, 1500000000, 1));
    }
}

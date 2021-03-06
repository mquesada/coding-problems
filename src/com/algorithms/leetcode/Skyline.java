package com.algorithms.leetcode;

import java.util.*;

/**
 * A city's skyline is the outer contour of the silhouette formed by all the buildings in that city when viewed from a distance.
 * Now suppose you are given the locations and height of all the buildings as shown on a cityscape photo (Figure A),
 * write a program to output the skyline formed by these buildings collectively (Figure B).
 * <p>
 * Buildings  Skyline Contour
 * The geometric information of each building is represented by a triplet of integers [Li, Ri, Hi], where Li and Ri
 * are the x coordinates of the left and right edge of the ith building, respectively, and Hi is its height.
 * It is guaranteed that 0 ≤ Li, Ri ≤ INT_MAX, 0 < Hi ≤ INT_MAX, and Ri - Li > 0. You may assume all buildings are
 * perfect rectangles grounded on an absolutely flat surface at height 0.
 * <p>
 * For instance, the dimensions of all buildings in Figure A are recorded as: [ [2 9 10], [3 7 15], [5 12 12],
 * [15 20 10], [19 24 8] ] .
 * <p>
 * The output is a list of "key points" (red dots in Figure B) in the format of [ [x1,y1], [x2, y2], [x3, y3], ... ]
 * that uniquely defines a skyline. A key point is the left endpoint of a horizontal line segment. Note that the last
 * key point, where the rightmost building ends, is merely used to mark the termination of the skyline, and always
 * has zero height. Also, the ground in between any two adjacent buildings should be considered part of the skyline
 * contour.
 * <p>
 * For instance, the skyline in Figure B should be represented as:[ [2 10], [3 15], [7 12], [12 0], [15 10], [20 8], [24, 0] ].
 * <p>
 * Notes:
 * <p>
 * The number of buildings in any input list is guaranteed to be in the range [0, 10000].
 * The input list is already sorted in ascending order by the left x position Li.
 * The output list must be sorted by the x position.
 * There must be no consecutive horizontal lines of equal height in the output skyline. For instance, [...[2 3], [4 5], [7 5], [11 5], [12 7]...] is not acceptable; the three lines of height 5 should be merged into one in the final output as such: [...[2 3], [4 5], [12 7], ...]
 * <p>
 * https://leetcode.com/problems/the-skyline-problem/
 */
public class Skyline {

    private static class Point implements Comparable<Point> {
        private int x;
        private int h;
        private boolean start;

        Point(int x, int h, boolean start) {
            this.x = x;
            this.h = h;
            this.start = start;
        }

        @Override
        public int compareTo(Point o) {
            if (this.x != o.x) {
                return Integer.compare(this.x, o.x);
            }
            if (this.start && o.start) { // Both are start
                return Integer.compare(this.h, o.h) * -1;
            } else if (!this.start && !o.start) { // Both are end
                return Integer.compare(this.h, o.h);
            } else if (this.start) { // This is start
                return -1;
            }
            return 1; // Other is start
        }
    }

    public List<int[]> getSkyline(int[][] buildings) {
        if (buildings == null || buildings.length == 0) {
            return Collections.emptyList();
        }

        List<int[]> result = new ArrayList<>();

        Point[] points = new Point[buildings.length * 2];
        int i = 0;
        for (int[] building : buildings) {
            points[i] = new Point(building[0], building[2], true);
            points[i + 1] = new Point(building[1], building[2], false);
            i += 2;
        }

        Arrays.sort(points);

        PriorityQueue<Integer> maxHeightQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxHeightQueue.offer(0);

        int previousMaxHeight = 0;

        for (Point point : points) {
            if (point.start) {
                maxHeightQueue.offer(point.h);
            } else {
                maxHeightQueue.remove(point.h);
            }

            int currentMaxHeight = maxHeightQueue.peek();
            if (currentMaxHeight != previousMaxHeight) {
                result.add(new int[]{point.x, currentMaxHeight});
                previousMaxHeight = currentMaxHeight;
            }

        }

        return result;
    }

    public static void main(String args[]) {
        int[][] buildings = {{1, 3, 4}, {3, 4, 4}, {2, 6, 2}, {8, 11, 4}, {7, 9, 3}, {10, 11, 2}};
//        int[][] buildings = {{2, 9, 10}, {3, 7, 15}, {5, 12, 12}, {15, 20, 10}, {19, 24, 8}};
        Skyline sd = new Skyline();
        List<int[]> criticalPoints = sd.getSkyline(buildings);
        criticalPoints.forEach(cp -> System.out.println(cp[0] + " " + cp[1]));

    }
}

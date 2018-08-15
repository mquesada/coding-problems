package com.algorithms.leetcode;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, return the ordering of courses you
 * should take to finish all courses.
 * <p>
 * There may be multiple correct orders, you just need to return one of them.
 * If it is impossible to finish all courses, return an empty array.
 * <p>
 * https://leetcode.com/problems/course-schedule-ii/description/
 */
public class CourseScheduleII {

    private static boolean tso(Map<Integer, List<Integer>> graph,
                               int node,
                               Set<Integer> visited,
                               Set<Integer> processed) {
        if (visited.contains(node)) {
            return !processed.contains(node);
        }

        visited.add(node);

        for (int edge : graph.get(node)) {
            if (tso(graph, edge, visited, processed)) {
                return true;
            }
        }

        processed.add(node);

        return false;
    }

    public static int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courses.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            courses.get(pre[1]).add(pre[0]);
        }

        Set<Integer> visited = new HashSet<>();
        Set<Integer> processed = new LinkedHashSet<>();

        for (int course : courses.keySet()) {
            if (tso(courses, course, visited, processed)) {
                return new int[0];
            }
        }

        int i = processed.size() - 1;
        int[] result = new int[processed.size()];
        for (int course : processed) {
            result[i] = course;
            i--;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(findOrder(2, new int[][]{{1, 0}})));
        System.out.println(Arrays.toString(findOrder(4, new int[][]{{1, 0}, {2, 0}, {3, 1}, {3, 2}})));
    }

}

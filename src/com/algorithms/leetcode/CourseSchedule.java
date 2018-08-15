package com.algorithms.leetcode;

import java.util.*;

/**
 * There are a total of n courses you have to take, labeled from 0 to n-1.
 * <p>
 * Some courses may have prerequisites, for example to take course 0 you have to first take course 1,
 * which is expressed as a pair: [0,1]
 * <p>
 * Given the total number of courses and a list of prerequisite pairs, is it possible for you to finish all courses?
 * <p>
 * https://leetcode.com/problems/course-schedule/
 */
public class CourseSchedule {

    private static boolean hasCycle(Map<Integer, List<Integer>> courses) {
        Set<Integer> visited = new HashSet<>();
        Set<Integer> processed = new HashSet<>();
        // Go through all nodes in case there are disconnected areas in the graph
        for (int node : courses.keySet()) {
            if (hasCycle(courses, node, visited, processed)) {
                return true;
            }
        }

        return false;
    }

    private static boolean hasCycle(Map<Integer, List<Integer>> courses, Integer node,
                                    Set<Integer> visited, Set<Integer> processed) {

        if (visited.contains(node)) {
            return !processed.contains(node);
        }

        visited.add(node);
        for (int child : courses.get(node)) {
            if (hasCycle(courses, child, visited, processed)) {
                return true;
            }
        }
        processed.add(node);

        return false;
    }

    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> courses = new HashMap<>();
        for (int i = 0; i < numCourses; i++) {
            courses.put(i, new ArrayList<>());
        }

        for (int[] pre : prerequisites) {
            courses.get(pre[0]).add(pre[1]);
        }

        return !hasCycle(courses);
    }

    public static void main(String[] args) {
        System.out.println(canFinish(2, new int[][]{{1, 0}}));
        System.out.println(canFinish(2, new int[][]{{1, 0}, {0, 1}}));
    }
}

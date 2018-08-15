package com.algorithms.leetcode;

import com.algorithms.util.Interval;

import java.util.*;

/**
 * Given a collection of intervals, merge all overlapping intervals.
 * <p>
 * Input: [[1,3],[2,6],[8,10],[15,18]]
 * Output: [[1,6],[8,10],[15,18]]
 * Explanation: Since intervals [1,3] and [2,6] overlaps, merge them into [1,6].
 * <p>
 * Input: [[1,4],[4,5]]
 * Output: [[1,5]]
 * Explanation: Intervals [1,4] and [4,5] are considered overlapping.
 *
 * https://leetcode.com/problems/merge-intervals/
 */
public class MergeIntervals {

    private static boolean checkOverlap(Interval m1, Interval m2) {
        int s = Math.max(m1.getStart(), m2.getStart());
        int e = Math.min(m1.getEnd(), m2.getEnd());

        return (e - s) >= 0;
    }

    public List<Interval> merge(List<Interval> meetings) {
        if (meetings == null || meetings.size() < 2) {
            return meetings;
        }

        // merge meetings ranges
        Collections.sort(meetings, new Comparator<Interval>() {
            @Override
            public int compare(Interval m1, Interval m2) {
                return Integer.compare(m1.getStart(), m2.getStart());
            }
        });

        List<Interval> result = new ArrayList<>();

        Interval current = meetings.get(0);

        for (int i = 1; i < meetings.size(); i++) {
            Interval next = meetings.get(i);
            if (checkOverlap(current, next)) {
                current = new Interval(Math.min(current.getStart(), next.getStart()),
                        Math.max(current.getEnd(), next.getEnd()));
            } else {
                result.add(current);
                current = next;
            }
        }

        if (current != null) {
            result.add(current);
        }

        return result;
    }

}

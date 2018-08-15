package com.algorithms.leetcode;

import com.algorithms.util.Interval;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of non-overlapping intervals, insert a new interval into the intervals (merge if necessary).
 *
 * You may assume that the intervals were initially sorted according to their start times.
 *
 * https://leetcode.com/problems/insert-interval/
 */
public class InsertIntervals {

    private static int compare(Interval m1, Interval m2) {
        if (m1.getEnd() < m2.getStart()) {
            return -1;
        } else if (m1.getStart() > m2.getEnd()){
            return 1;
        }
        return 0;
    }

    public List<Interval> insert(List<Interval> intervals, Interval newInterval) {
        if (intervals == null || intervals.isEmpty()) {
            if (newInterval != null) {
                return Arrays.asList(newInterval);
            }
            return null;
        }

        int n = intervals.size();
        List<Interval> result = new ArrayList<>();

        // New interval needs to be added first
        Interval first = intervals.get(0);
        if (compare(newInterval, first) == -1) {
            result.add(newInterval);
            result.addAll(intervals);
            return result;
        }
        // New interval needs to be added last
        Interval last = intervals.get(n - 1);
        if (compare(newInterval, last) == 1) {
            result.addAll(intervals);
            result.add(newInterval);
            return result;
        }

        // Check if it goes in the middle
        for (int i = 0; i < n; i++) {
            Interval current = intervals.get(i);
            int overlap = compare(newInterval, current);
            if (overlap == -1) {
                result.add(newInterval);
                result.add(current);
                if (i < n - 1) {
                    result.addAll(intervals.subList(i + 1, n));
                }
                break;
            } else if (overlap == 0) {
                newInterval = new Interval(Math.min(current.getStart(), newInterval.getStart()),
                        Math.max(current.getEnd(), newInterval.getEnd()));
                if (i == n - 1) {
                    result.add(newInterval);
                }
            } else {
                result.add(current);
            }
        }
        return result;

    }
}

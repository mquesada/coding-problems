package com.algorithms.leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

/**
 * Median is the middle value in an ordered integer list. If the size of the list is even, there is no middle value. So the median is the mean of the two middle value.
 * <p>
 * For example,
 * [2,3,4], the median is 3
 * <p>
 * [2,3], the median is (2 + 3) / 2 = 2.5
 * <p>
 * Design a data structure that supports the following two operations:
 * <p>
 * void addNum(int num) - Add a integer number from the data stream to the data structure.
 * double findMedian() - Return the median of all elements so far.
 * <p>
 * https://leetcode.com/problems/find-median-from-data-stream/
 */
public class MedianFinder {

    private PriorityQueue<Integer> min;
    private PriorityQueue<Integer> max;

    public MedianFinder() {
        this.min = new PriorityQueue<>();
        this.max = new PriorityQueue<>(Collections.reverseOrder());
    }

    public void addNum(int num) {
        max.offer(num);
        min.offer(max.poll());

        if (max.size() < min.size()) {
            max.offer(min.poll());
        }
    }

    public double findMedian() {
        return (max.size() == min.size())
                ? (double) (max.peek() + (min.peek())) / 2.0
                : max.peek();
    }

}

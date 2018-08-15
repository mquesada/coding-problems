package com.algorithms.leetcode;

import java.util.PriorityQueue;

/**
 * Find the kth largest element in an unsorted array. Note that it is the kth largest element in the sorted order,
 * not the kth distinct element.
 *
 * Example 1:
 *
 * Input: [3,2,1,5,6,4] and k = 2
 * Output: 5
 * Example 2:
 *
 * Input: [3,2,3,1,2,4,5,5,6] and k = 4
 * Output: 4
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ array's length.
 *
 * https://leetcode.com/problems/kth-largest-element-in-an-array/
 */
public class KthLargestArray {

    public static int findKthLargest(int[] nums, int k) {

        PriorityQueue<Integer> kLargest = new PriorityQueue<>();
        for (int i = 0; i < nums.length; i++) {
            if (i < k) {
                kLargest.add(nums[i]);
            } else {
                kLargest.add(nums[i]);
                kLargest.poll();
            }
        }

        return kLargest.poll();
    }

    public static void main(String[] args) {
        System.out.println(findKthLargest(new int[]{3, 2, 1, 5, 6, 4}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
        System.out.println(findKthLargest(new int[]{-1, 2, 0}, 2));
        System.out.println(findKthLargest(new int[]{3, 2, 3, 1, 2, 4, 5, 5, 6}, 4));
    }
}

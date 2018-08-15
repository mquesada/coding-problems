package com.algorithms.leetcode;

import com.algorithms.util.Counter;
import com.algorithms.util.TreeNode;

/**
 * Given a binary search tree, write a function kthSmallest to find the kth smallest element in it.
 * <p>
 * Note:
 * You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
 * <p>
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 */
public class KSmallestBST {

    public int kthSmallest(TreeNode root, int k, Counter counter) {
        if (root == null) {
            return Integer.MAX_VALUE;
        }
        int left = kthSmallest(root.getLeft(), k, counter);
        if (left != Integer.MAX_VALUE) {
            return left;
        }
        counter.increment();
        if (counter.getValue() == k) {
            return root.getValue();
        }

        return kthSmallest(root.getRight(), k, counter);
    }

    public int kthSmallest(TreeNode root, int k) {
        if (root == null || k == 0) {
            return 0;
        }
        return kthSmallest(root, k, new Counter(0));
    }

}

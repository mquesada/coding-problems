package com.algorithms.leetcode;

import com.algorithms.util.Counter;
import com.algorithms.util.TreeNode;

/**
 * Given a Binary Search Tree (BST) and a positive integer k, find the k’th largest element in the Binary Search Tree.
 * <p>
 * https://www.geeksforgeeks.org/kth-largest-element-in-bst-when-modification-to-bst-is-not-allowed/
 */
public class KthLargestBST {

    private static int findKthLargest(TreeNode node, int k, Counter counter) {
        if (node == null) {
            return Integer.MIN_VALUE;
        }

        int right = findKthLargest(node.getRight(), k, counter);
        if (right != Integer.MIN_VALUE) {
            return right;
        }

        counter.increment();
        if (counter.getValue() == k) {
            return node.getValue();
        }

        return findKthLargest(node.getLeft(), k, counter);
    }

    public static int findKthLargest(TreeNode root, int k) {
        if (root == null || k <= 0) {
            throw new IllegalArgumentException("Invalid input!");
        }

        return findKthLargest(root, k, new Counter(0));
    }

    public static void main(String[] args) {
        TreeNode root = new TreeNode(4);
        TreeNode left = new TreeNode(2);
        left.setLeft(new TreeNode(1));
        left.setRight(new TreeNode(2));
        root.setLeft(left);

        TreeNode right = new TreeNode(7);
        TreeNode rightLeft = new TreeNode(6);
        rightLeft.setLeft(new TreeNode(5));
        right.setLeft(rightLeft);
        TreeNode rightRight = new TreeNode(9);
        rightRight.setLeft(new TreeNode(8));
        rightRight.setRight(new TreeNode(10));
        right.setRight(rightRight);
        root.setRight(right);

        System.out.println(findKthLargest(root, 4));
        System.out.println(findKthLargest(root, 6));
        System.out.println(findKthLargest(root, 5));

    }

}

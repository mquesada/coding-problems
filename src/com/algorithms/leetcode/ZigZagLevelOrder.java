package com.algorithms.leetcode;

import com.algorithms.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the zigzag level order traversal of its nodes' values. (ie, from left to right,
 * then right to left for the next level and alternate between).
 * <p>
 * https://leetcode.com/problems/binary-tree-zigzag-level-order-traversal/description/
 */
public class ZigZagLevelOrder {

    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<List<Integer>> result = new ArrayList<>();
        boolean left = true;

        Stack<TreeNode> current = new Stack<>();
        current.push(root);

        while (!current.isEmpty()) {
            Stack<TreeNode> next = new Stack<>();
            List<Integer> level = new ArrayList<>();
            while (!current.isEmpty()) {
                TreeNode node = current.pop();
                level.add(node.getValue());
                if (left) {
                    if (node.getLeft() != null) {
                        next.push(node.getLeft());
                    }
                    if (node.getRight() != null) {
                        next.push(node.getRight());
                    }
                } else {
                    if (node.getRight() != null) {
                        next.push(node.getRight());
                    }
                    if (node.getLeft() != null) {
                        next.push(node.getLeft());
                    }
                }
            }
            result.add(level);

            left = !left;
            current = next;
        }
        return result;
    }

}

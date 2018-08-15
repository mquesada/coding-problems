package com.algorithms.leetcode;

import com.algorithms.util.TreeNode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/**
 * Given a binary tree, return the postorder traversal of its nodes' values.
 * <p>
 * Follow up: Recursive solution is trivial, could you do it iteratively?
 * <p>
 * https://leetcode.com/problems/binary-tree-postorder-traversal/description/
 */
public class PostOrderTraversalIterative {

    public List<Integer> postorderTraversal(TreeNode root) {
        if (root == null) {
            return Collections.emptyList();
        }

        List<Integer> result = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        int done = 0;

        while (!stack.isEmpty()) {
            TreeNode current = stack.peek();

            if ((current.getLeft() == null && current.getRight() == null)
                    || (current.getRight() != null && current.getRight().getValue() == done)
                    || (current.getLeft() != null && current.getLeft().getValue() == done)) {
                done = stack.pop().getValue();
                result.add(done);
            } else {
                if (current.getRight() != null) {
                    stack.push(current.getRight());
                }

                if (current.getLeft() != null) {
                    stack.push(current.getLeft());
                }
            }
        }

        return result;
    }

}

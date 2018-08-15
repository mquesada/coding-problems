package com.algorithms.leetcode;

/**
 * Reverse a singly linked list.
 *
 * Example:
 *
 * Input: 1->2->3->4->5->NULL
 * Output: 5->4->3->2->1->NULL
 * Follow up:
 *
 * A linked list can be reversed either iteratively or recursively. Could you implement both?
 *
 * https://leetcode.com/problems/reverse-linked-list/
 */
public class ReverseLinkedList {

    private static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    public static ListNode reverseList(ListNode head) {
        if (head == null) {
            return null;
        }

        ListNode current = head;
        ListNode next = head.next;
        ListNode temp = null;

        while (current != null) {
            current.next = temp;
            temp = current;
            current = next;
            if (next != null) {
                next = next.next;
            }
        }

        head = temp;

        return head;
    }

    public static ListNode reverseListRecursive(ListNode node) {
        if (node == null || node.next == null) {
            return node;
        }

        ListNode reversed = reverseListRecursive(node.next);
        node.next.next = node;
        node.next = null;
        return reversed;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);

        ListNode head2 = reverseList(head);
        System.out.println();
    }
}

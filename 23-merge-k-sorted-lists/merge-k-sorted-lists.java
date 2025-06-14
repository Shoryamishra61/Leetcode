import java.util.PriorityQueue;

/**
 * Definition for singly-linked list.
 * public class ListNode {
 * int val;
 * ListNode next;
 * ListNode() {}
 * ListNode(int val) { this.val = val; }
 * ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }

        // Use a min-heap to keep track of the smallest node among all lists.
        // The comparator ensures that nodes are ordered by their 'val'.
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // Add the head of each list to the min-heap.
        for (ListNode list : lists) {
            if (list != null) {
                minHeap.add(list);
            }
        }

        // A dummy node to simplify building the new list.
        ListNode dummyHead = new ListNode(-1);
        ListNode current = dummyHead;

        // Continue until the heap is empty.
        while (!minHeap.isEmpty()) {
            // Get the node with the smallest value from the heap.
            ListNode smallestNode = minHeap.poll();

            // Append it to our result list.
            current.next = smallestNode;
            current = current.next;

            // If there's a next node in the same list, add it to the heap.
            if (smallestNode.next != null) {
                minHeap.add(smallestNode.next);
            }
        }

        // The merged list starts after the dummy head.
        return dummyHead.next;
    }
}
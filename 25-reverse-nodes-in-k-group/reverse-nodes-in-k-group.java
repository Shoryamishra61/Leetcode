/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k == 1) return head;
        
        // Dummy before the list head to simplify edge handling
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode groupPrev = dummy;
        
        while (true) {
            // Find the k-th node from groupPrev
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break;           // fewer than k nodes left
            ListNode groupNext = kth.next;    // start of next group
            
            // Reverse this group
            ListNode prev = groupNext;
            ListNode curr = groupPrev.next;
            while (curr != groupNext) {
                ListNode tmp = curr.next;
                curr.next = prev;
                prev = curr;
                curr = tmp;
            }
            
            // Rewire the previous part to the new head (kth)
            ListNode tail = groupPrev.next;   // was head, now tail
            groupPrev.next = kth;
            groupPrev = tail;                 // move groupPrev to tail
        }
        
        return dummy.next;
    }
    
    // Returns the k-th node from curr (exclusive); null if not enough nodes
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}

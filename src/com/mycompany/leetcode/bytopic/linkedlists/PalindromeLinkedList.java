package com.mycompany.leetcode.bytopic.linkedlists;

public class PalindromeLinkedList {

    public static void main(String[] args) {
        PalindromeLinkedList runner = new PalindromeLinkedList();
        int[] arr1 = {1, 2, 5, 7, 7, 5, 2, 1};
        int[] arr2 = {1, 2, 5, 2, 1};
        int[] arr3 = {1};
        ListNode list1 = ListNode.valueOf(arr1);
        ListNode list2 = ListNode.valueOf(arr2);
        ListNode list3 = ListNode.valueOf(arr3);

        // test printing
        System.out.println("Print in order");
        System.out.println(list1);
        System.out.println("Print reverse");
        ListNode.printValuesReverse(list1);
        System.out.println();

        // test ed solution
        System.out.println("Editorial solution");
        boolean ans1 = runner.isPalindromeBtfl(list1);
        System.out.println(ans1);
        System.out.println(list1);

        boolean ans2 = runner.isPalindromeEd(list2);
        System.out.println(ans2);
        System.out.println(list2);

        boolean ans3 = runner.isPalindromeEd(list3);
        System.out.println(ans3);
        System.out.println(list3);

        // test my solution
        System.out.println("My solution");
        ans1 = runner.isPalindrome(list1);
        System.out.println(ans1);
        System.out.println(list1);

        ans2 = runner.isPalindrome(list2);
        System.out.println(ans2);
        System.out.println(list2);

        ans3 = runner.isPalindrome(list3);
        System.out.println(ans3);
        System.out.println(list3);

    }

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }

        public static void printValuesReverse(ListNode node) {
            if (node != null) {
                printValuesReverse(node.next);
                System.out.print(" " + node.val + " ") ;
            }
        }

        public static ListNode valueOf(int[] arr) {
            ListNode prev = null;
            for(int i = arr.length-1; i >= 0; i--) {
                ListNode curr = new ListNode(arr[i]);
                curr.next = prev;
                prev = curr;
            }
            return prev;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append("List = [");
            sb.append(val);
            for(ListNode node = next; node != null; node = node.next) {
                sb.append(", ");
                sb.append(node.val);
            }
            sb.append("]");
            return sb.toString();
        }
    }

    // ***********************************************************************
    // my solution with the same idea as in the best solution from editorial
    // ***********************************************************************
    public boolean isPalindrome(ListNode head) {

        // проверка не нужна так как код корректно работает для списка из 1-го элемента
        /*if (head.next == null) {
            return true;
        }*/

        ListNode slow = head, fast = head;
        int len = 1;
        while(fast.next != null) {
            slow = slow.next;
            fast = fast.next;
            len++;
            if (fast.next != null) {
                fast = fast.next;
                len++;
            }
        }
        // slow - start of second part (even n), or middle (odd n)
        // fast - last node
        // reverse from slow to fast
        reverse(slow);
        ListNode startHead = head, startTail = fast;
        for(int i = 0; i < len/2; i++) {
            if (startHead.val != startTail.val) {
                return false;
            }
            startHead = startHead.next;
            startTail = startTail.next;
        }
        return true;
    }

    private void reverse(ListNode start) {
        ListNode first = start, second = start.next;
        first.next = null;
        while(second != null) {
            ListNode next = second.next;
            second.next = first;
            first = second;
            second = next;
        }
    }

    // ***********************************************************************
    // best solution from editorial
    // ***********************************************************************

    public boolean isPalindromeEd(ListNode head) {

        if (head == null) return true;

        // Find the end of first half and reverse second half.
        ListNode firstHalfEnd = endOfFirstHalf(head);
        ListNode secondHalfStart = reverseList(firstHalfEnd.next);

        // Check whether or not there is a palindrome.
        ListNode p1 = head;
        ListNode p2 = secondHalfStart;
        boolean result = true;
        while (result && p2 != null) {
            if (p1.val != p2.val) result = false;
            p1 = p1.next;
            p2 = p2.next;
        }

        // Restore the list and return the result.
        //firstHalfEnd.next = reverseList(secondHalfStart);
        reverseList(secondHalfStart);
        return result;
    }

    // Taken from https://leetcode.com/problems/reverse-linked-list/solution/
    private ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode nextTemp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = nextTemp;
        }
        return prev;
    }

    private ListNode endOfFirstHalf(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (fast.next != null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    // ***********************************************************************
    // smart recursive solution from editorial
    // ***********************************************************************

    private ListNode frontPointer;

    private boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            if (!recursivelyCheck(currentNode.next)) return false;
            if (currentNode.val != frontPointer.val) return false;
            frontPointer = frontPointer.next;
        }
        return true;
    }

    public boolean isPalindromeBtfl(ListNode head) {
        frontPointer = head;
        return recursivelyCheck(head);
    }
}

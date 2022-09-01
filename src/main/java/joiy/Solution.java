package joiy;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // get num1, num2
        // get sum
        // generate ListNode and return
        long sum = getNum(l1) + getNum(l2);
        return makeListNode(sum);
    }

    private long getNum(ListNode node) {
        if (node.next == null) {
            return node.val;
        }
        long base = 1;
        long rst = 0;
        ListNode curr = node;
        while (curr.next != null) {
            rst += curr.val * base;
            base *= 10;
            curr = curr.next;
        }
        rst += curr.val * base;
        return rst;
    }

    private ListNode makeListNode(long num) {
        // get last digit by num%10
        // remove last digit by num/10
        // iterate until num<10
        if (num < 10) {
            return new ListNode((int) num);
        }

        ListNode rst = new ListNode();
        ListNode curr = rst;

        while (num >= 10) {
            curr.val = (int) (num % 10);
            num /= 10;
            curr.next = new ListNode();
            curr = curr.next;
        }
        curr.val = (int) num;
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(new Solution().addTwoNumbers(
                new Solution().makeListNode(11111111111L),
                new Solution().makeListNode(11111111111L)
        ));
    }

}


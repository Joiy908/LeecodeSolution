package joiy;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.math.BigInteger;

public class Solution {
    public static class ListNode {
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
        public java.lang.String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 1 get num1, num2 : use bigInteger
        // 2 get sum
        // 3 generate ListNode and return

        BigInteger n1 = getBigInter(l1);
        BigInteger n2 = getBigInter(l2);
        BigInteger sum = n1.add(n2);
        return makeListNode(sum);
    }

    private ListNode makeListNode(BigInteger sum) {
        // get last digit by tracing a ptr
        // remove last digit by decrease ptr
        // iterate until ptr=0
        String s = sum.toString();
        int ptr = s.length() - 1;
        if (ptr == 0) {
            return new ListNode(Integer.parseInt(s));
        }

        ListNode rst = new ListNode();
        ListNode curr = rst;

        while (ptr > 0) {
            // - 48 to convert '1' to 1, '2' to 2
            curr.val = s.charAt(ptr) - 48;
            ptr -= 1;
            curr.next = new ListNode();
            curr = curr.next;
        }
        curr.val = s.charAt(ptr) - 48;
        return rst;
    }

    private BigInteger getBigInter(ListNode node) {
        // 1 get length of nodeList
        // 2 new a char[len]
        // 3 put node.val in 2 reversely
        int len = getLen(node);
        char[] chars = new char[len];
        for (int i = len - 1; i > -1; i--) {
            // + 48 to convert 1 to '1', 2 to '2'
            chars[i] = (char) (48 + node.val);
            node = node.next;
        }
        return new BigInteger(java.lang.String.copyValueOf(chars));
    }

    /**
     * assume node.val != null
     */
    private int getLen(ListNode node) {
        int len = 0;
        if (node.next == null) {
            return 1;
        }
        while (node.next != null){
            node = node.next;
            len += 1;
        }
        return len+1;
    }

    @Test
    public void testGetLen() {
        // --- test getLen()
        ListNode node1 = new ListNode(1);
        Assertions.assertEquals(getLen(node1), 1);

        ListNode node2 = new ListNode(2);
        node1.next = node2;
        Assertions.assertEquals(getLen(node1), 2);

        ListNode node3 = new ListNode(3);
        node2.next = node3;
        Assertions.assertEquals(getLen(node1), 3);

        // --- test getBigInteger()
        BigInteger b1 = getBigInter(node1);
        Assertions.assertEquals(b1, new BigInteger("321"));

        ListNode node0 = new ListNode(0);
        node0.next = node1;
        Assertions.assertEquals(getBigInter(node0), new BigInteger("3210"));

        node3.next = new ListNode(0);
        Assertions.assertEquals(getBigInter(node1), new BigInteger("0321"));


        BigInteger sum = b1.add(new BigInteger("1"));
//        System.out.println("sum = " + sum);

        System.out.println(getBigInter(node1));
        System.out.println(getBigInter(node2));
        System.out.println(addTwoNumbers(node1, node2));
    }
}


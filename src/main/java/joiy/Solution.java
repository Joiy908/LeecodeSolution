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
        // way2: just calculate
        ListNode ptr1 = l1;
        ListNode ptr2 = l2;
        ListNode rst = new ListNode(); // uninitialized
        ListNode curr = rst;
        // 进位值
        int addedNum = 0;
        int thisDigit;
        while (ptr1 != null || ptr2 != null) {
            int sum;
            // get sum and update ptr1 and ptr2
            if (ptr1 == null) {
                sum = ptr2.val + addedNum;
                ptr2 = ptr2.next;
            }else if (ptr2 == null) {
                sum = ptr1.val + addedNum;
                ptr1 = ptr1.next;
            }else{// else both != null
                sum = ptr1.val + ptr2.val + addedNum;
                ptr1 = ptr1.next;
                ptr2 = ptr2.next;
            }

            // update thisDigit and addedNum
            if (sum > 9) {
                thisDigit = sum - 10;
                addedNum = 1;
            }else {
                thisDigit = sum;
                addedNum = 0;
            }

            // put thisDigit to curr and update curr
            // case1: extreme case
            if (ptr1 == null && ptr2 == null) {
                curr.val = thisDigit;
                if (addedNum > 0) {
                    curr.next = new ListNode(addedNum);
                }
                return rst;
            }// case2: normal
            curr.val = thisDigit;
            curr.next = new ListNode();
            curr = curr.next;
        }
        return rst;
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
        ListNode node1 = new ListNode(2);
        Assertions.assertEquals(getLen(node1), 1);

        ListNode node2 = new ListNode(4);
        node1.next = node2;
        Assertions.assertEquals(getLen(node1), 2);

        ListNode node3 = new ListNode(3);
        node2.next = node3;
        Assertions.assertEquals(getLen(node1), 3);

        // --- test getBigInteger()
        BigInteger b1 = getBigInter(node1);
//        Assertions.assertEquals(b1, new BigInteger("321"));

        ListNode node0 = new ListNode(0);
        node0.next = node1;
//        Assertions.assertEquals(getBigInter(node0), new BigInteger("3210"));

        node3.next = new ListNode(0);
//        Assertions.assertEquals(getBigInter(node1), new BigInteger("0321"));


        BigInteger sum = b1.add(new BigInteger("1"));
//        System.out.println("sum = " + sum);

//        System.out.println(getBigInter(node1));
//        System.out.println(getBigInter(node2));
        ListNode b2 = makeListNode(new BigInteger("342"));
        ListNode b3 = makeListNode(new BigInteger("465"));


        System.out.println(addTwoNumbers(b2, b3));
    }
}


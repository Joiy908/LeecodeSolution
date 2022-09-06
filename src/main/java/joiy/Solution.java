package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    /**
     * 本题 用一个 queue 来维护最长不重复char substring(移动窗口)
     * 由于 string 的特殊性, 可以用两个指针+queueLen变量, 来模拟queue,
     * 从而节约内存。
     * big O = square(n)
     */
    public int lengthOfLongestSubstring(String s) {
        // queue start idx, queue end idx
        int qs = 0, qe = 0, max = 0;
        // s length
        int len = s.length();
        // special case
        if (len == 1) return 1;
        // queue length
        int queueLen = 0;
        for (;qe < len; qe++) { // 用qe作为下标,遍历 s
            // 获取当前char
            char curr = s.charAt(qe);
            // 查看 curr 是否出现在 queue 中
            boolean psIncr = false;
            for (int j = qs; j < qe; j++) {
                if (s.charAt(j) == curr) {
                    // if curr in queue, update qs
                    qs= j+1;
                    // when qs increases, update queueLen
                    queueLen= qe - qs + 1;
                    psIncr = true;
                    break;
                }
            }
            if (!psIncr) {
                // if curr not in queue (when ps is not changed):
                // increase queue length
                queueLen++;
                // when queueLen incr, update max
                max = Math.max(queueLen, max);
            }
        }
        return max;
    }

    @Test
    public void main() {
        int len = lengthOfLongestSubstring("abcdea");
        Assertions.assertEquals(5, len);
        int len1 = lengthOfLongestSubstring("abcabc");
        Assertions.assertEquals(3, len1);
        int len2 = lengthOfLongestSubstring("aaaaa");
        Assertions.assertEquals(1, len2);

        int len3 = lengthOfLongestSubstring("");
        Assertions.assertEquals(0, len3);
        int len4 = lengthOfLongestSubstring("au");
        Assertions.assertEquals(2, len4);

        int len5 = lengthOfLongestSubstring("pwwkew");
        Assertions.assertEquals(3, len5);

        int len6 = lengthOfLongestSubstring("abcabcbb");
        Assertions.assertEquals(3, len6);
        int len7 = lengthOfLongestSubstring(" ");
        Assertions.assertEquals(1, len7);
        int len8 = lengthOfLongestSubstring("aab");
        Assertions.assertEquals(2, len8);

        int len9 = lengthOfLongestSubstring("bwf");
        Assertions.assertEquals(3, len9);
    }

}


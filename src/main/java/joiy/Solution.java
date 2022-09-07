package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;

public class Solution {
    /**
     * 本题 用一个 queue 来维护最长不重复char substring(移动窗口)
     * way2: use hashmap to speed up search
     */
    public int lengthOfLongestSubstring(String s) {
        // s length
        int len = s.length();
        if (len == 0) return 0;
        HashMap<Character, Integer> hash = new HashMap<>(36);
        // queue start idx, queue end idx
        int left = 0, max = 0;

        for (int i = 0; i < len; i++) {
            if (hash.containsKey(s.charAt(i))) {
                left = Math.max(left,hash.get(s.charAt(i)) + 1);
            }
            hash.put(s.charAt(i), i);
            // update max length
            max = Math.max(max, i-left+1);
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


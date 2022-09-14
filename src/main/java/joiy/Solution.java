package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    /**
     * 思路1:
     *  1 把每一个 char 当做对称轴, 求最长 palindrome
     *  2 把每个 btw char 当做对称轴，求最长 palindrome
     */
    public String longestPalindrome(String s) {
        if (s.length() == 1) return s;
        char[] chars = s.toCharArray();
        // use 2 ptr to maintain longest*
        // longestPalindromeStart = 0
        int maxStart = 0, maxEnd = 0, maxLen = 0;
        for (int i = 1; i < chars.length; i++) {
            // use c as Symmetry axis
            int j = 1;
            int currLen;
            while (i-j > -1 && i+j < chars.length && chars[i-j] == chars[i+j]) {
                currLen = 2 * j + 1;
                if (currLen > maxLen) {
                    maxStart = i - j;
                    maxEnd = i + j;
                    maxLen = currLen;
                }
                j++;
            }
        }
        for (int i = 0; i < chars.length; i++) {
            // use near right-of-c as Symmetry axis
            int j = 0;
            int currLen;
            while (i-j > -1 && i+j+1 < chars.length && chars[i-j] == chars[i+j+1]) {
                currLen = 2 * ( j + 1);
                if (currLen > maxLen) {
                    maxStart = i - j;
                    maxEnd = i + j + 1;
                    maxLen = currLen;
                }
                j++;
            }
        }
        return maxLen == 0 ? s.substring(0, 1) : s.substring(maxStart, maxEnd+1);
    }

    @Test
    public void main() {
        String rst1 = longestPalindrome("babad");
        Assertions.assertEquals(rst1, "bab");

        String rst2 = longestPalindrome("cbbd");
        Assertions.assertEquals(rst2, "bb");

        final String rst = longestPalindrome("a");
        Assertions.assertEquals(rst, "a");

        final String s = longestPalindrome("ac");
        Assertions.assertEquals(s, "a");

        final String s1 = longestPalindrome("bb");
        Assertions.assertEquals(s1, "bb");
    }

}


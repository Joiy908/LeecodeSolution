package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数
     * version2: subtlety:
     * rst = rst * 10 + nextDigit
     * 这样会 overflow,
     * solution1: long rst;算后判断(rst*10 + nextDigit > Integer.Max)
     * solution2: 可以把 overflow 的部分变成减法
     * rst > (Integer.Max - nextDigit)/10
     * 省去了用 long, great.
     */
    public int myAtoi(String s) {
        if (s.equals("")) return 0;
        // 1. trim: find start and end
        int start = 0, end, sign;
        // 1.1 find start
        char[] chars = s.toCharArray();
        for (char c : chars) {
            if (c == ' ') start++;
            else if(isDigit(c) || isSign(c)) break;
            else return 0;
        }
        if (start > s.length() -1) return 0;
        if (isSign(chars[start])) {
            sign = chars[start] == '-' ? -1 : 1;
            start++;
        }else sign = 1;
        // rm all 0 before other
        for (int i = start; i < s.length(); i++) {
            if (chars[i] == '0') start++;
            else break;
        }
        // 1.2 find end
        end = start;
        while(end < s.length()) {
            if (!isDigit(chars[end])) break;
            end++;
        }
        end--;

        int rst = 0;
        // 2. get rst
        for (int i = start; i <= end ; i++) {
            int digit = chars[i] - '0';
            if (rst  > (Integer.MAX_VALUE - digit)/10) { // overflow
                return -1 == sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
            rst = rst*10 + digit;
        }
        return sign * rst;
    }

    public boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }
    public boolean isSign(char c) {
        return c == '-' || c == '+';
    }

    @Test
    public void main() {

        String s;
        int ex;
        s = "42";
        ex = 42;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "    -42";
        ex = -42;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "21 with word";
        ex = 21;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "sdf21 with word";
        ex = 0;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "";
        Assertions.assertEquals(ex, myAtoi(s));

        s = "+1";
        ex = 1;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "-000000000000000000000000000000000000000000000000001";
        ex = -1;
        Assertions.assertEquals(ex, myAtoi(s));

        s = "    10522545459";
        String s1 = "10000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000522545459";
        ex = Integer.MAX_VALUE;
        Assertions.assertEquals(ex, myAtoi(s));
        Assertions.assertEquals(ex, myAtoi(s1));
    }

}


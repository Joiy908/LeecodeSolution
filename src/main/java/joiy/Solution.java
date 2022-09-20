package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Solution {
    /**
     * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数
     */
    public int myAtoi(String s) {
        if (s.equals("")) return 0;
        // 1. trim: find start and end
        int start = 0, end = 0, sign;
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

        long rst = 0;
        // 2. reversely get rst
        int i = end;
        for (long j = 1; i >= start ; i--,j*=10) {
            rst += (long) (chars[i] - '0') * j;
            if (rst > Integer.MAX_VALUE || j> Integer.MAX_VALUE) { // overflow
                return -1 == sign ? Integer.MIN_VALUE : Integer.MAX_VALUE;
            }
        }
        return (int) (sign * rst);
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


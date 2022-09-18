package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Solution {
    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 ，就返回 0。
     */
    public int reverse(int x) {
        long n = 0;
        while(x != 0) {
            n = n*10 + x%10;

            x = x/10;
        }
        return (int)n==n? (int)n:0;
    }

    @Test
    public void main() {

        int x = 123, ex = 321;
        Assertions.assertEquals(ex, reverse(x));

        x = -123;
        ex = -321;
        Assertions.assertEquals(ex, reverse(x));

        x = 120;
        ex = 21;
        Assertions.assertEquals(ex, reverse(x));

        x = 0;
        ex = 0;
        Assertions.assertEquals(ex, reverse(x));




    }

}


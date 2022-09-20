package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Solution {
    /**
     * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
     * 如果反转后整数超过 32 位的有符号整数的范围 ，就返回 0。
     * deal with overflow: way2
     */
    public int reverse(int x) {
        int n = 0;
        while(x != 0) {
            if (x > 0 && n > (Integer.MAX_VALUE - x%10)/10)
                return 0;
            if (x < 0 && n < (Integer.MIN_VALUE - x%10)/10)
                return 0;
            n = n*10 + x%10;
            x = x/10;
        }
        return n;
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


package joiy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;

public class Solution {
    /**
     * 将一个给定字符串 s 根据给定的行数 numRows ，以从上往下、从左到右进行 Z 字形排列
     *
     */
    public String convert(String s, int numRows) {
        if (numRows == 1) return s;
        // new n list
        // use a flag to decide curr item to which list

        LinkedList<StringBuilder> bin = new LinkedList<>();
        for (int i = 0; i < numRows; i++) {
            bin.add(new StringBuilder());
        }
        int flag = -1, target = 1;
        for (char c : s.toCharArray()) {
            target += flag;
            bin.get(target).append(c);
            if (target == 0 || target == numRows - 1) flag = -flag;
        }
        StringBuilder rst = new StringBuilder(s.length());
        for (StringBuilder sb : bin) {
            rst.append(sb);
        }
        return rst.toString();
    }

    @Test
    public void main() {
        String s = "PAYPALISHIRING";
        int numRows = 3;
        final String rst = convert(s, numRows);
        Assertions.assertEquals(rst, "PAHNAPLSIIGYIR");
    }

}


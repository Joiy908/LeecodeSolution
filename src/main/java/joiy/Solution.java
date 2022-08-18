package joiy;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        // 1 put nums:idx to a hashmap
        // 2 for i in nums: if (target-i) in hm, return [i.idx, (target-i).idx]
        // 1
        HashMap<Integer, Integer> map = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            if (map.containsKey(target - nums[i])) {
                return new int[] {map.get(target - nums[i]),i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 3}, 6);
        System.out.println("ints = " + Arrays.toString(ints));
    }
}

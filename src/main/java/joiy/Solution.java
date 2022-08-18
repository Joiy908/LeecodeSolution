package joiy;

import sun.rmi.server.InactiveGroupException;

import java.util.Arrays;
import java.util.HashMap;

public class Solution {
    public static int[] twoSum(int[] nums, int target) {
        // 1 put nums:idx to a hashmap
        // 2 for i in nums: if (target-i) in hm, return [i.idx, (target-i).idx]
        // 1
        HashMap<Integer, Integer> hashMap = new HashMap<>(nums.length);
        for (int i = 0; i < nums.length; i++) {
            // if contains, use idx of the num first appearing
            if (!hashMap.containsKey(nums[i])) {
                hashMap.put(nums[i], i);
            }
            // 2022-08-18-modify: merge 1 and 2
            int part = target - nums[i];
            Integer idx = hashMap.get(part);
            if (idx != null && idx != i) {
                return new int[]{i, idx};
            }
        }
        return null;
    }

    public static void main(String[] args) {
        int[] ints = twoSum(new int[]{3, 3}, 6);
        System.out.println("ints = " + Arrays.toString(ints));
    }
}

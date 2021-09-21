package algo_4;

import java.util.HashMap;

/**
 * @author xuekg
 * @description 数组中两数求和
 * @date 2021/9/11 8:30
 **/
public class TwoSum {

    public int[] towSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] >= target) {
                continue;
            }
            if (map.containsKey(nums[i])) {
                return new int[]{map.get(nums[i]), i};
            }
            map.put(target - nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {

    }
}

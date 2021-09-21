package algo_5;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author xuekg
 * @description 三数之和
 * @date 2021/9/12 17:03
 **/
public class ThreeSum {

    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        returnList.add(Arrays.asList(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }

        return returnList;
    }

    /**
     * 双指针
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> returnList = new ArrayList<>();
        int n = nums.length;

        Arrays.sort(nums);

        //遍历每一个元素，作为当前三元组中最小的那个
        for (int i = 0; i < n; i++) {
            //如果当前数已经大于0，直接break
            if (nums[i] > 0) {
                break;
            }
            //如果当前数据已经出现过，直接跳过
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            //常规情况，以当前数作为最小数，定义左右指针
            int left = i + 1;
            int right = n - 1;

            //左右指针不重叠，就继续移动指针
            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];
                if (sum == 0) {
                    returnList.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;
                    //如果移动之后的元素相同，直接跳过
                    while (left < right && nums[left] == nums[left - 1]) {
                        left++;
                    }
                    while (left < right && nums[right] == nums[right + 1]) {
                        right++;
                    }
                } else if (sum < 0) {
                    //较小的数增大，左指针右移
                    left++;
                } else {
                    right--;
                }
            }

        }
        return returnList;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{-1, 0, -2, 3, 4, -2, 1};

        List<List<Integer>> lists = threeSum2(nums);

        System.out.println(lists);
    }
}

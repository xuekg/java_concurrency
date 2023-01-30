package itcast.zheng.d0119;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

/**
 * @author xuekg
 * @description
 * @date 2023/1/19 14:36
 **/
public class Test {
    public static void main(String[] args) {
        String s = "anagram", t = "nagaram";

        Solution1 solution1 = new Solution1();
        solution1.isAnagram(s, t);

    }
}

class Solution {
    /**
     * 避免拷贝过程中数据被覆盖
     *
     * @param A
     * @param m
     * @param B
     * @param n
     */
    public void merge(int[] A, int m, int[] B, int n) {
        int k = m + n - 1;
        int i = m - 1;
        int j = n - 1;
        while (i >= 0 && j >= 0) {
            if (A[i] >= B[j]) {
                A[k--] = A[i--];
            } else {
                A[k--] = B[j--];
            }
        }
        while (i >= 0) {
            A[k--] = A[i--];
        }
        while (j >= 0) {
            A[k--] = B[j--];
        }
    }
}

class Solution1 {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }
        char[] str1 = s.toCharArray();
        char[] str2 = t.toCharArray();
        Arrays.sort(str1);
        Arrays.sort(str2);
        return new String(str1).equals(new String(str2));
    }

    public boolean canMakeArithmeticProgression(int[] arr) {
        Arrays.sort(arr);
        int diff = arr[1] - arr[0];
        for (int i = 2; i < arr.length; i++) {
            if (arr[i] - arr[i - 1] != diff) {
                return false;
            }
        }
        return true;
    }

    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        List<int[]> result = new ArrayList<>();
        int curLeft = intervals[0][0];
        int curRight = intervals[0][1];
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] <= curRight) {
                if (intervals[i][1] > curRight) {
                    curRight = intervals[i][1];
                }
            } else {
                result.add(new int[]{curLeft, curRight});
                curLeft = intervals[i][0];
                curRight = intervals[i][1];
            }
        }
        result.add(new int[]{curLeft, curRight});
        return result.toArray(new int[result.size()][]);
    }

    public int[] exchange(int[] nums) {
        int i = 0;
        int j = nums.length - 1;
        while (i < j) {
            if (nums[i] % 2 == 1) {
                i++;
                continue;
            }
            if (nums[j] % 2 == 0) {
                j--;
                continue;
            }
            int tmp = nums[i];
            nums[i] = nums[j];
            nums[j] = tmp;
            i++;
            j--;
        }
        return nums;
    }

    public void sortColors(int[] nums) {
        int p = 0;
        int q = nums.length - 1;
        while (p < q) {
            if (nums[p] != 2) {
                p++;
                continue;
            }
            if (nums[q] == 2) {
                q--;
                continue;
            }
            swap(nums, p, q);
            p++;
            q--;
        }
        int i = 0;
        int j = p;
        if (nums[j] == 2) j--;
        while (i < j) {
            if (nums[i] == 0) {
                i++;
                continue;
            }
            if (nums[j] == 1) {
                j--;
                continue;
            }
            swap(nums, i, j);
            i++;
            j--;
        }
    }


    private void swap(int[] nums, int p, int q) {
        int tmp = nums[p];
        nums[p] = nums[q];
        nums[q] = tmp;
    }

    public ListNode insertionSortList(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode newHead = new ListNode(Integer.MIN_VALUE, null);
        //遍历节点
        ListNode p = head;
        while (p != null) {
            ListNode tmp = p.next;
            //寻找p节点插入的位置，插入到哪个节点后面
            ListNode q = newHead;
            while (q.next != null && q.next.val <= p.val) {
                q = q.next;
            }
            //将p节点插入
            p.next = q.next;
            q.next = p;

            p = tmp;
        }
        return newHead.next;
    }
}
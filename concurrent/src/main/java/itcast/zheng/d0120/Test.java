package itcast.zheng.d0120;

/**
 * @author xuekg
 * @description 二分查找正确的编写姿势：
 * 1.查找区间永远是闭区间[low,high]
 * 2.循环条件永远是：low <= high
 * 3.对于low==high的情况，必要时候特殊处理，在while内部补充退出条件
 * 4.返回值永远是mid，不是low，high
 * 5.low、high的更新永远是low=mid+1;high=mid-1
 * 6.对应非确定性查找，使用前后探测法，来确定搜索区间
 * 1.第一个、最后一个相等的
 * 2.第一个大于等于的、最后一个小于等于的
 * 3.循环数组寻找最小值
 * 4.寻找峰值
 * 7.先处理命中情况，再处理在左右半部分查找的情况
 * @date 2023/1/20 8:26
 **/
public class Test {
    public static void main(String[] args) {
        Solution solution = new Solution();
        solution.mySqrt(5);
    }
}

class Solution {

    /**
     * 二分查找，非递归实现
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (a[mid] == value) {
                return mid;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int bsearch_r(int[] a, int n, int value) {
        return bsearch_rr(a, 0, n - 1, value);
    }

    private int bsearch_rr(int[] a, int low, int high, int value) {
        if (low > high) return -1;
        int mid = (low + high) / 2;
        if (a[mid] == value) {
            return mid;
        } else if (a[mid] < value) {
            return bsearch_rr(a, mid + 1, high, value);
        } else {
            return bsearch_rr(a, low, mid - 1, value);
        }
    }

    /**
     * 查找第一个值等于给定值的元素
     *
     * @param a
     * @param n
     * @param target
     * @return
     */
    public int bsearch_1(int[] a, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                if (mid == 0 || a[mid - 1] != target) {
                    return mid;
                }
            } else if (a[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个等于给定值的元素
     *
     * @param a
     * @param n
     * @param target
     * @return
     */
    public int bsearch_2(int[] a, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                if (mid == n - 1 || a[mid + 1] != target) {
                    return mid;
                } else {
                    low = mid + 1;//a[mid+1]=target
                }
            } else if (a[mid] > target) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找第一个大于等于给定值的元素
     *
     * @param a
     * @param n
     * @param target
     * @return
     */
    public int bsearch_3(int[] a, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] >= target) {
                if (mid == 0 || a[mid - 1] < target) {
                    return mid;
                } else {
                    high = mid - 1;
                }
            } else { //a[mid] < target
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 查找最后一个小于等于给定值的元素
     *
     * @param a
     * @param n
     * @param target
     * @return
     */
    public int bsearch_4(int[] a, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] <= target) {
                if (mid == n - 1 || a[mid + 1] > target) {
                    return mid;
                } else {
                    low = mid + 1;
                }
            } else { //a[mid] > target
                high = mid - 1;
            }
        }
        return -1;
    }

    /**
     * 无重复数据的循环有序数组中查找给定值
     * 7 9 10 11 15  1 2 3 5 6
     *
     * @param a
     * @param n
     * @param target
     * @return
     */
    public int bsearch_5(int[] a, int n, int target) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (a[mid] == target) {
                return mid;
            } else if (a[low] <= a[mid]) {//左边有序
                if (target >= a[low] && target <= a[mid]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {//右边有序
                if (target > a[mid] && target <= a[high]) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
        }
        return -1;
    }

    /**
     * 852. 山脉数组的峰顶索引
     *
     * @param arr
     * @return
     */
    public int peakIndexInMountainArray(int[] arr) {
        int n = arr.length;
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (mid == 0) {
                low = mid + 1;
            } else if (mid == n - 1) {
                high = mid - 1;
            } else if (arr[mid] > arr[mid - 1] && arr[mid] > arr[mid + 1]) {
                return mid;
            } else if (arr[mid] > arr[mid - 1]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    public int mySqrt(int x) {
        if (x == 1) {
            return 1;
        }
        int min = 0;
        int max = x;
        while (max - min > 1) {
            int m = (max + min) / 2;
            if (x / m < m) {
                max = m;
            } else {
                min = m;
            }
        }
        return min;
    }
}

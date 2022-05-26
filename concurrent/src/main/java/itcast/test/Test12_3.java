package itcast.test;

import java.util.Arrays;

/**
 * @author xuekg
 * @description 将一个整数转化为一个十进制数组
 * @date 2022/5/26 17:41
 **/
public class Test12_3 {

    public static void main(String[] args) {
        int[] ints = convertToDecimalArray(2467);
    }

    public static int[] convertToDecimalArray(int a) {
        //int类型最大值2147483647 10位数
        int[] arr = new int[10];
        int i = 0;
        while (a != 0) {
            arr[i] = a % 10;
            a = a / 10;
            i++;
        }

        swap(arr, i);
        return arr;
    }

    private static void swap(int[] arr, int n) {
        int i = 0;
        int j = n - 1;
        while (i < j) {
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}

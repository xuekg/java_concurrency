package itcast.zheng.d1123;

import java.util.Stack;

/**
 * @author xuekg
 * @description 每日温度
 * @date 2022/11/23 21:31
 **/
public class DrabTest {

    public static void main(String[] args) {
        int[] temp = new int[]{23,32,1,32,54,34,32,66};

        DrabTest d = new DrabTest();

        int[] drab = d.drab(temp);

        System.out.println(drab.toString());
    }

    /**
     * 单调栈法
     *
     * @param T
     * @return
     */
    public int[] drab(int[] T) {
        int n = T.length;
        int[] result = new int[n];
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && T[stack.peek()] < T[i]) {
                int idx = stack.peek();
                result[idx] = i - idx;
                stack.pop();
            }
            stack.push(i);
        }
        return result;
    }

    /**
     * 暴力解法
     *
     * @param T
     * @return
     */
    public int[] dailyTemp(int[] T) {
        int n = T.length;
        int[] result = new int[n];

        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (T[j] > T[i]) {
                    result[i] = j - i;
                    break;
                }
            }
        }
        return result;
    }

}

package itcast.zheng.d1201;

import java.util.Stack;

/**
 * @author xuekg
 * @description
 * @date 2022/12/1 20:59
 **/
public class Test {

}

class MinStack {

    private Stack<Integer> data;
    private Stack<Integer> minval;

    public MinStack() {
        data = new Stack<>();
        minval = new Stack<>();
    }

    public void push(int val) {

        if (data.empty()) {
            data.push(val);
            minval.push(val);
        } else {
            Integer peek = minval.peek();
            if (val < peek) {
                minval.push(val);
            } else {
                minval.push(peek);
            }
            data.push(val);
        }
    }

    public void pop() {
        data.pop();
        minval.pop();
    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
        return minval.peek();
    }
}

class TripleInOne {

    private int[] array;
    private int n;
    private int[] top;//保存每个栈的栈顶元素


    public TripleInOne(int stackSize) {
        array = new int[3 * stackSize];
        n = 3 * stackSize;
        top = new int[3];
        top[0] = -3;
        top[1] = -2;
        top[2] = -1;

    }

    public void push(int stackNum, int value) {
        if (top[stackNum] + 3 >= n) {
            return;
        }
        top[stackNum] += 3;
        array[top[stackNum]] = value;
    }

    public int pop(int stackNum) {
        if (top[stackNum] < 0) {
            return -1;
        }
        int ret = array[top[stackNum]];
        top[stackNum] -= 3;
        return ret;
    }

    public int peek(int stackNum) {
        if (top[stackNum] < 0) {
            return -1;
        }
        return array[top[stackNum]];
    }

    public boolean isEmpty(int stackNum) {
        return top[stackNum] < 0;
    }
}
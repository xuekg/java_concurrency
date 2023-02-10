package itcast.zheng.d1125;


import java.util.*;

/**
 * @author xuekg
 * @description
 * @date 2022/11/25 21:33
 **/
public class MyStackTest {

    public static void main(String[] args) {
        SortedStack ss = new SortedStack();
//        ["SortedStack", "push", "push", "peek", "pop", "peek"]

        ss.push(1);
        ss.push(2);
        ss.peek();
        ss.pop();
        ss.peek();
    }


}


class MyStack {

    private Queue<Integer> q1;
    private Queue<Integer> q2;

    public MyStack() {
        q1 = new LinkedList<>();
        q2 = new LinkedList<>();
    }

    public void push(int x) {
        q2.offer(x);
        while (!q1.isEmpty()) {
            q2.offer(q1.poll());
        }
        // 交换a和b,使得q2队列没有在push()的时候始终为空队列
        Queue<Integer> tmp = q2;
        q2 = q1;
        q1 = tmp;
    }

    public int pop() {
        return q1.poll();
    }

    public int top() {
        return q1.peek();
    }

    public boolean empty() {
        return q2.isEmpty();
    }
}


class SortedStack {

    private Queue<Integer> sort;
    private Queue<Integer> tmp1;

    public SortedStack() {
        sort = new LinkedList<>();
        tmp1 = new LinkedList<>();
    }

    public void push(int val) {
        if (sort.isEmpty()) {
            sort.offer(val);
        } else {
            while (sort.peek() >= val) {
                Integer poll = sort.poll();
                tmp1.offer(poll);
                if (sort.isEmpty()) {
                    break;
                }
            }
            sort.offer(val);
            while (!tmp1.isEmpty()) {
                sort.offer(tmp1.poll());
            }
        }
    }

    public void pop() {
        sort.poll();
    }

    public int peek() {
        if (!sort.isEmpty()) {
            return sort.peek();
        }
        return -1;
    }

    public boolean isEmpty() {
        return sort.isEmpty();
    }
}


class MinStack {

    private int min = Integer.MAX_VALUE;
    private Stack<Integer> stack;

    public MinStack() {
        stack = new Stack<>();
    }

    public void push(int val) {
        if (min >= val) {
            stack.push(min);
            min = val;
        }
        stack.push(val);
    }

    public void pop() {
        if (stack.pop() == min) {
            min = stack.pop();
        }
    }

    public int top() {
        return stack.peek();
    }

    public int getMin() {
        return min;
    }
}

class TripleInOne {

    int N = 3;
    int sizeNumber;
    private int[] data;
    private int[] locations;

    public TripleInOne(int stackSize) {
        sizeNumber = stackSize;
        data = new int[stackSize * 3];
        for (int i = 0; i < N; i++) {
            locations[i] = i * stackSize;
        }
    }

    public void push(int stackNum, int value) {
        int idx = locations[stackNum];
        if(idx < (stackNum + 1) * sizeNumber){
            data[idx] = value;
            locations[stackNum]++;
        }
    }

    public int pop(int stackNum) {
        int idx = locations[stackNum];
        if(idx > stackNum * sizeNumber){
            locations[stackNum]--;
            return data[idx];
        }else{
            return -1;
        }
    }

    public int peek(int stackNum) {
        int idx = locations[stackNum];
        if(idx > stackNum * sizeNumber){
            return data[idx];
        }else{
            return -1;
        }
    }

    public boolean isEmpty(int stackNum) {
        return locations[stackNum] == stackNum * sizeNumber;
    }
}

class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (Character c : s.toCharArray()){
            if(c == '('){
                stack.push(')');
            }else if(c == '{'){
                stack.push('}');
            }else if(c == '['){
                stack.push(']');
            }else if (stack.isEmpty() || !c.equals(stack.pop())){
                return false;
            }
        }
        return stack.isEmpty();
    }
}
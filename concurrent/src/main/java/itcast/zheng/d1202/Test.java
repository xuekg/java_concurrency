package itcast.zheng.d1202;

import java.util.Stack;

/**
 * @author xuekg
 * @description
 * @date 2022/12/3 17:13
 **/
public class Test {

}

class Solution {
    public String removeDuplicates(String s) {
        char[] chars = s.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : chars) {
            if (stack.isEmpty()) {
                stack.push(c);
            } else {
                Character peek = stack.peek();
                if (c == peek) {
                    stack.pop();
                } else {
                    stack.push(c);
                }
            }
        }

        int size = stack.size();
        char[] result = new char[size];

        while (!stack.isEmpty()){
            result[size--] = stack.pop();
        }
        return new String(result);
    }
}
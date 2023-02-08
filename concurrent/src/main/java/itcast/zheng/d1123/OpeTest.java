package itcast.zheng.d1123;


import java.util.Stack;

/**
 * @author xuekg
 * @description 计算器/表达式求值
 * @date 2022/11/23 21:06
 **/
public class OpeTest {

    public static void main(String[] args) {
        String s = "456+33*32-453/23";
        OpeTest opeTest = new OpeTest();
        int calculate = opeTest.calculate(s);
        System.out.println(calculate);
    }

    public int calculate(String s) {
        Stack<Integer> nums = new Stack<>();
        Stack<Character> ops = new Stack<>();

        int i = 0;
        int n = s.length();

        while (i < n) {
            char c = s.charAt(i);
            if (c == ' ') {
                i++;
            } else if (isDigit(c)) {
                int number = 0;
                while (i < n && isDigit(s.charAt(i))) {
                    number = number * 10 + (s.charAt(i) - '0');
                    i++;
                }
                nums.push(number);
            } else {
                if (ops.isEmpty() || prior(c, ops.peek())) {
                    ops.push(c);
                } else {
                    while (!ops.isEmpty() && !prior(c, ops.peek())) {
                        fetchAndCal(nums, ops);
                    }
                    ops.push(c);
                }
                i++;
            }
        }
        while (!ops.isEmpty()) {
            fetchAndCal(nums, ops);
        }
        return nums.pop();
    }

    private boolean prior(char a, char b) {
        if ((a == '*' || a == '/')
                && (b == '+' || b == '-')) {
            return true;
        }
        return false;
    }

    private int cal(char op, int number1, int number2) {
        switch (op) {
            case '+':
                return number1 + number2;
            case '-':
                return number1 - number2;
            case '*':
                return number1 * number2;
            case '/':
                return number1 / number2;
        }
        return -1;
    }

    private boolean isDigit(char c) {
        return c >= '0' && c <= '9';
    }

    private void fetchAndCal(Stack<Integer> nums, Stack<Character> ops) {
        int num1 = nums.pop();
        int num2 = nums.pop();
        char op = ops.pop();
        int ret = cal(op, num1, num2);
        nums.push(ret);
    }

}

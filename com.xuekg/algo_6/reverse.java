package algo_6;

/**
 * @author xuekg
 * @description 整数反转 有限制
 * @date 2021/9/13 21:52
 **/
public class reverse {

    public static void main(String[] args) {
        int reverse = reverse(1234567898);
        System.out.println(reverse);
    }

    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            int tmp = res * 10 + x % 10;
            if (tmp / 10 != res) { // 溢出!!!
                return 0;
            }
            res = tmp;
            x /= 10;
        }
        return res;
    }

    public static String reverse(String originStr) {
        if (originStr == null || originStr.length() <= 1) {
            return originStr;
        }
        return reverse(originStr.substring(1)) + originStr.charAt(0);
    }
}

package algo_7;

/**
 * @author xuekg
 * @description 判断一个数是不是回文数
 * @date 2021/9/14 22:08
 **/
public class isPalindrome {

    public static void main(String[] args) {
        int x = 0;
        isPalindrome palindrome = new isPalindrome();
        System.out.println(palindrome.isPalindrome(x));
    }

    public boolean isPalindrome(int x) {
        boolean flag = true;
        String s = Integer.toString(x);
        int left = 0;
        int right = s.length() - 1;
        while (left <= right) {
            if (s.charAt(left) != s.charAt(right)) {
                flag = false;
            }
            left++;
            right--;
        }
        return flag;
    }

    public boolean isPalindrome2(int x) {
        if (x == 0) {
            return true;
        }
        if (x < 0 || x % 10 == 0) {
            return false;
        }
        int reversed = 0;
        while (x > reversed) {
            reversed = reversed * 10 + x % 10;
            x /= 10;
        }
        return x == reversed || x == reversed / 10;
    }
}

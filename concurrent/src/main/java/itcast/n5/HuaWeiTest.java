package itcast.n5;

/**
 * @author xuekg
 * @description
 * @date 2022/9/26 15:37
 **/
public class HuaWeiTest {

    public static void main(String[] args) {

        char diff = findDiff("ca", "abec");
        System.out.println(diff);
    }

    public static char findDiff(String s, String t) {
        int xor = 0;
        for (int i = 0; i < s.length(); i++) {
            xor = xor ^ s.charAt(i);
        }

        for (int i = 0; i < t.length(); i++) {
            xor = xor ^ t.charAt(i);
        }
        return (char) xor;
    }
}

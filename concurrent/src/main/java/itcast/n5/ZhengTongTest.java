package itcast.n5;

/**
 * @author xuekg
 * @description
 *
 * 现在有一个数字与字母的映射表，且有以下规则：
 *
 * 数字   字母
 *  3     A
 *  5     B
 *  7     C
 *
 * 规则：
 *
 * 1.	碰到当前数字时，使用字母替换，例如，3-> A
 * 2.	碰到当前数字的倍数时，使用字母替换， 例如：6->A
 * 3.	碰到多个数字的倍数时，使用多个对应的字母替代，例如：15 -> AB，21->AC
 * 请根据映射表和规则，给出0-100之间的转换结果，提示：不要直接在for循环中使用if判断，尽可能的使用设计模式。
 *
 * @date 2022/10/10 11:19
 **/
public class ZhengTongTest {

    public static void main(String[] args) {
        int x = Integer.MAX_VALUE;

        Long aLong = new Long(x);
        System.out.println(aLong);
        System.out.println(Long.valueOf(x));
    }
}

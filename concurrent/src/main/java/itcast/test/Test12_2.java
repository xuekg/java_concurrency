package itcast.test;

/**
 * @author xuekg
 * @description
 * @date 2022/5/25 14:45
 **/
public class Test12_2 {

    public static void main(String[] args) {
        int i = 100;
        int j = 100;
        compare(i, j);
    }

    public static void compare(Integer o1, Integer o2) {
        Integer o3 = o1 + 1;
        Integer o4 = o2 + 1;
        // 先装箱 -> 拆箱 -> 装箱 。都是常量池中的Integer
        System.out.println(o3 == o4);
    }
}

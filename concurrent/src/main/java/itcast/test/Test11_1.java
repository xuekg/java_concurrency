package itcast.test;

/**
 * @author xuekg
 * @description
 * @date 2022/5/23 18:25
 **/
public class Test11_1 {

    public static void main(String[] args) {
        Integer a = 10;

        fun(a);
        System.out.println(a);
    }

    private static void fun(Integer a) {
        a = 4;
    }
}

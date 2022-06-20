package itcast.n7;

/**
 * @author xuekg
 * @description
 * @date 2022/6/13 12:47
 **/
public class Test {

    public static String parentStr = "parent static string";

    static {
        System.out.println("parent static fields");
        System.out.println(parentStr);
    }

    public Test() {
        System.out.println("parent instance initialization");
    }
}

class Sub extends Test {
    public static String subStr = "sub static string";

    static {
        System.out.println("sub static fields");
        System.out.println(subStr);
    }

    public Sub() {
        System.out.println("sub instance initialization");
    }

    public static void main(String[] args) {
        System.out.println("sub main");
        new Sub();
    }
}
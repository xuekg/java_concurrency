package itcast.test;

/**
 * @author xuekg
 * @description String.intern();
 * @date 2022/5/27 16:08
 **/
public class Test12_5 {

    public static void main(String[] args) {
        /**
         * 在JDK 1.7及以后下，当执行str2.intern();时，因为常量池中没有“str01”这个字符串，
         * 所以会在常量池中生成一个对堆中的“str01”的引用(注意这里是引用 ，就是这个区别于JDK 1.6的地方。在JDK1.6下是生成原字符串的拷贝)，
         * 而在进行String str1 = “str01”;字面量赋值的时候，常量池中已经存在一个引用，所以直接返回了该引用，
         * 因此str1和str2都指向堆中的同一个字符串，返回true。
         */
        String str2 = new String("str") + new String("01");
        str2.intern();
        String str1 = "str01";
        System.out.println(str2 == str1);

        /**
         * 将中间两行调换位置以后，因为在进行字面量赋值（String str4 = “str01″）的时候，常量池中不存在，
         * 所以str4指向的常量池中的位置，而str3指向的是堆中的对象，再进行intern方法时，对str3和str4已经没有影响了，所以返回false。
         */
        String str3 = new String("str") + new String("01");
        String str4 = "str01";
        str3.intern();
        System.out.println(str3 == str4);
    }
}

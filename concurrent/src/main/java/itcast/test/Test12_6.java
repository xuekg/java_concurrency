package itcast.test;

import java.util.LinkedList;

/**
 * @author xuekg
 * @description
 * @date 2022/5/27 16:31
 **/
public class Test12_6 {

    public static void main(String[] args) {
        LinkedList linkedList = new LinkedList();
//        String s1 = new String("1") + new String("1");
//        s1.intern();
//        String s2 = "11";
//
        String s1 = new String("11");
        s1.intern();
        String s2 = "11";
//
        System.out.println(s1 == s2);
    }
}

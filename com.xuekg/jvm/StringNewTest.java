package jvm;

public class StringNewTest {

  public static void main(String[] args) {
    /**
     * 创建两个对象 对象1:new关键字在堆空间创建的 对象2:字符串常量池中的对象，字节码指令：ldc
     */
    String str = new String("ab");

    /**
     * 产生几个对象 1，new StringBuilder() 2，new String("a") 3，常量池中的"a" 4，new String("b")
     * 5，常量池中的"b" 深入 6，StringBuilder的toString() new String("ab")
     * toString()的调用，在字符串常量池中，没有生成"ab"
     */
    String str_ab = new String("a") + new String("b");
  }

}
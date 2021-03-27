package jvm;

public class StringExer1 {

  public static void main(String[] args) {
    String s = new String("a") + new String("b");

    String s2 = s.intern();// jdk6 在字符串常量池中创建一个字符串"ab
                           // jdk8 创建一个引用，指向new String("ab")

    System.out.println(s == "ab");// jdk6:true jdk8:true
    System.out.println(s2 == "ab");// jdk6:false jdk8:true
  }

}
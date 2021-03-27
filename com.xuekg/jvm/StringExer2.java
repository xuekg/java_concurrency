package jvm;

public class StringExer2 {
  public static void main(String[] args) {

    String s = new String("ab");// 执行后，会在字符串常量池中生成"ab"
    // String s = new String("a") + new String("b");//执行后，不会在字符串常量池中生成"ab"
    s.intern();
    String s2 = "ab";
    System.out.println(s == s2);
  }

}
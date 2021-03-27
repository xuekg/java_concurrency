package jvm;

/**
 * 扩展
 */
public class StringIntern1 {

  public static void main(String[] args) {
    String s3 = new String("1") + new String("1");
    String s4 = "11";
    s3.intern();
    System.out.println(s3 == s4);
  }

}
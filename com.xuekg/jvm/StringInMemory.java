package jvm;

public class StringInMemory {
  public static void main(String[] args) {
    int i = 1;
    Object obj = new Object();
    StringInMemory stringInMemory = new StringInMemory();
    stringInMemory.foo(obj);
  }

  public void foo(Object param) {
    String str = param.toString();
    System.out.println(str);
  }

}
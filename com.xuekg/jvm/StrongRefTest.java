package jvm;

public class StrongRefTest {

  public static void main(String[] args) {
    StringBuffer stringBuffer = new StringBuffer("hello");
    StringBuffer str = stringBuffer;

    stringBuffer = null;
    System.gc();
    try {
      Thread.sleep(3000);
    } catch (Exception e) {
      e.printStackTrace();
    }

    System.out.println(str);
  }

}
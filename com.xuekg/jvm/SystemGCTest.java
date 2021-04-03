package jvm;

public class SystemGCTest {
  public static void main(String[] args) {
    new SystemGCTest();
    System.gc();

    // 强制执行finalize
    System.runFinalization();
  }

  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("调用当前类重写的finalize方法");
  }

}
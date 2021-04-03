package jvm;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;

public class PhantomRefTest {

  public static PhantomRefTest obj;// 当前类对象的声明
  static ReferenceQueue<PhantomRefTest> phantomQueue = null;// 引用队列

  public static class CheckRefQueue extends Thread {
    @Override
    public void run() {
      while (true) {
        if (phantomQueue != null) {
          PhantomReference<PhantomRefTest> objt = null;
          try {
            objt = (PhantomReference<PhantomRefTest>) phantomQueue.remove();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
          if (objt != null) {
            System.out.println("追踪垃圾回收过程,PhantomRefTest实例被gc了");
          }
        }
      }
    }
  }

  /**
   * 只能被调用一次
   */
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("finalize方法被调用");
    obj = this;
  }

  public static void main(String[] args) {
    Thread t = new CheckRefQueue();
    t.setDaemon(true);// 设置守护线程
    t.start();

    phantomQueue = new ReferenceQueue<>();
    obj = new PhantomRefTest();

    PhantomReference<PhantomRefTest> phantomReference = new PhantomReference<PhantomRefTest>(obj, phantomQueue);

    try {

      // 不可获取虚引用中的对象
      System.out.println(phantomReference.get());
      // 去除强引用
      obj = null;
      System.gc();
      Thread.sleep(1000);
      if (obj == null) {
        System.out.println("obj dead");
      } else {
        System.out.println("obj active");
      }
      System.out.println("第二次gc");
      obj = null;
      System.gc();
      Thread.sleep(1000);
      if (obj == null) {
        System.out.println("obj dead");
      } else {
        System.out.println("obj active");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
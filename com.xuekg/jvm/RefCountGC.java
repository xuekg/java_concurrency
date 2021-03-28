package jvm;

/**
 * 验证java中GC标记使用的不是 引用计数算法 -XX:+PrintGCDetails python中采用了此方法来做垃圾回收 如何解决循环引用
 * 1.手动解除 2.使用弱引用weakRef
 */
public class RefCountGC {
  // 占一点空间
  private byte[] bigSize = new byte[5 * 1024 * 1024];

  Object ref = null;

  public static void main(String[] args) {
    RefCountGC ref1 = new RefCountGC();
    RefCountGC ref2 = new RefCountGC();

    ref1.ref = ref2;
    ref2.ref = ref1;

    ref1 = null;
    ref2 = null;

    // 注释此方法执行前后，查看新生代占用情况
    System.gc();
  }

}
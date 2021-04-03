package jvm;

/**
 * 测试Object类中finalize()方法
 */
public class CanReliveObj {

  public static CanReliveObj obj;// 类变量，属于GC ROOT

  // 此方法只能被调用一次
  @Override
  protected void finalize() throws Throwable {
    super.finalize();
    System.out.println("调用当前类重写的finalize方法");
    obj = this;// 当前待回收的对象在方法中与引用连上的一个对象obj建立了联系
  }

  public static void main(String[] args) {
    try {
      obj = new CanReliveObj();
      // 对象第一次成功拯救自己
      obj = null;
      System.gc();
      System.out.println("第一次gc");
      // 因为finalizer线程优先级很低，暂停2秒，等待执行
      Thread.sleep(2000);
      if (obj == null) {
        System.out.println("obj is dead");
      } else {
        System.out.println("obj is still alive");
      }
      // 下面的代码和上面一样，但是自救失败
      obj = null;
      System.gc();
      System.out.println("第二次gc");
      Thread.sleep(2000);
      if (obj == null) {
        System.out.println("obj is dead");
      } else {
        System.out.println("obj is still alive");
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
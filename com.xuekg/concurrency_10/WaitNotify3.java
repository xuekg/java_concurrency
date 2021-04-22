package concurrency_10;

/**
 * 有可能发生 线程1 是正常恢复的，虽然发生了中断，它的中断状态也确实是 true， 但是它没有抛出
 * InterruptedException，而是正常返回。此时，thread2 将得不到唤醒，一直 wait。
 */
public class WaitNotify3 {

  volatile int a = 0;

  public static void main(String[] args) {

    Object object = new Object();

    WaitNotify3 waitNotify = new WaitNotify3();

    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {

        synchronized (object) {
          System.out.println("线程1 获取到监视器锁");
          try {
            object.wait();
            System.out.println("线程1 正常恢复啦。");
          } catch (InterruptedException e) {
            System.out.println("线程1 wait方法抛出了InterruptedException异常");
          }
        }
      }
    }, "线程1");
    thread1.start();

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {

        synchronized (object) {
          System.out.println("线程2 获取到监视器锁");
          try {
            object.wait();
            System.out.println("线程2 正常恢复啦。");
          } catch (InterruptedException e) {
            System.out.println("线程2 wait方法抛出了InterruptedException异常");
          }
        }
      }
    }, "线程2");
    thread2.start();

    // 这里让 thread1 和 thread2 先起来，然后再起后面的 thread3
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
    }

    new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (object) {
          System.out.println("线程3 拿到了监视器锁。");
          System.out.println("线程3 设置线程1中断");
          thread1.interrupt(); // 1
          waitNotify.a = 1; // 这行是为了禁止上下的两行中断和notify代码重排序
          System.out.println("线程3 调用notify");
          object.notify(); // 2
          System.out.println("线程3 调用完notify后，休息一会");
          try {
            Thread.sleep(3000);
          } catch (InterruptedException e) {
          }
          System.out.println("线程3 休息够了，结束同步代码块");
        }
      }
    }, "线程3").start();
  }
}
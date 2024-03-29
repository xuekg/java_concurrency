package aqs;

public class NonReentrantLockTest {

  private static int j = 0;

  public static void main(String[] agrs) throws InterruptedException {
    NonReentrantLock nonReentrantLock = new NonReentrantLock();

    Runnable runnable = () -> {
      // 获取锁
      nonReentrantLock.lock();
      for (int i = 0; i < 100000; i++) {
        j++;
      }
      // 释放锁
      nonReentrantLock.unlock();
    };

    Thread thread = new Thread(runnable);
    Thread threadTwo = new Thread(runnable);

    thread.start();
    threadTwo.start();

    thread.join();
    threadTwo.join();

    System.out.println(j);
  }
}
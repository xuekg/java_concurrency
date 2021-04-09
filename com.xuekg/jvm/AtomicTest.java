package jvm;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Atomic 变量自增运算测试 之前解决方案 将race++或increase()方法加锁
 * 
 * CAS有ABA问题，尽管可以通过控制变量的版本来保证cas的正确性 不过比较鸡肋，真正出现问题的话改为传统的互斥同步可能会比原子类更为高效
 */
public class AtomicTest {

  public static AtomicInteger race = new AtomicInteger(0);

  public static void increase() {
    race.incrementAndGet();
  }

  // incrementAndGet源码
  /*
   * public final int incrementAndGet(){ for(;;){ int current = get(); int next =
   * current +1; if(compareAndSet(current,next)){ return next; } } }
   */

  public static final int THREAD_COUNT = 20;

  public static void main(String[] args) {
    Thread[] threads = new Thread[THREAD_COUNT];

    for (int i = 0; i < THREAD_COUNT; i++) {
      threads[i] = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int j = 0; j < 10000; j++) {
            increase();
          }
        }
      });
      threads[i].start();
    }

    while (Thread.activeCount() > 1) {
      Thread.yield();
    }
    System.out.println(race);
  }
}
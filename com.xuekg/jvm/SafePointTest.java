package jvm;

/**
 * @author xuekg
 * @description 当 sleep 1000ms 的时候，jvm 尝试在安全点停止 Java
 *              线程以进行定期清理，此时安全点需要一直等待子线程都执行结束后才能继续执行。 jdk8中添加启动参数
 *              -XX:+UnlockDiagnosticVMOptions
 *              -XX:GuaranteedSafepointInterval=2000 可以不等待循环结束直接执行 jdk10自从 JDK10
 *              以来，HotSpot 实现 Loop Strip Mining 优化，解决了在 counted loop
 *              中安全点轮询的问题，而且没有太多开销。 另外，如果你将 int i 更改为 long i ，循环将不再被视为 counted
 *              loop，因此你将不会看到前面说的 safepoint 导致的现象
 *              参考链接：https://juejin.cn/post/6844903878765314061
 *              https://bugs.openjdk.java.net/browse/JDK-8223051
 *              https://zhuanlan.zhihu.com/p/286110609
 * @date 2021/4/15 8:45
 **/
public class SafePointTest {

  // public static AtomicInteger num = new AtomicInteger(0);
  // public static int num = 0;
  public volatile static int num = 0;

  public static void main(String[] args) throws InterruptedException {
    Runnable runnable = () -> {
      for (int i = 0; i < 1000000000; i++) {
        // num.getAndAdd(1);
        num++;
      }
    };

    Thread t1 = new Thread(runnable);
    Thread t2 = new Thread(runnable);
    t1.start();
    t2.start();
    Thread.sleep(1000);
    System.out.println("num = " + num);
  }

  /**
   * 添加 -XX:+SafepointTimeout 和 -XX:SafepointTimeoutDelay=2000 两个参数，
   * 让虚拟机在等到线程进入安全点的时间超过 2000 毫秒时就认定为超时，这样就会输出导致问题的线程名称，得到的日志如下所示
   *
   * # SafepointSynchronize::begin: Timeout detected: #
   * SafepointSynchronize::begin: Timed out while spinning to reach a safepoint. #
   * SafepointSynchronize::begin: Threads which did not reach the safepoint: #
   * "Thread-1" #13 prio=5 os_prio=0 tid=0x00000000328a2000 nid=0x14bc runnable
   * [0x0000000000000000] java.lang.Thread.State: RUNNABLE
   *
   * # "Thread-0" #12 prio=5 os_prio=0 tid=0x00000000328a1000 nid=0x1c84 runnable
   * [0x0000000000000000] java.lang.Thread.State: RUNNABLE
   *
   * # SafepointSynchronize::begin: (End of list) num = 957515541
   * 我们已经知道安全点是以“是否具有让程序长时间执行的特征”为原则进行选定的。
   * 所以方法调用、循环跳转、异常跳转这些位置都可能会设置有安全点，但是HotSpot虚拟机为了避免安全点过多带来过重的负担，
   * 对循环还有一项优化措施，认为循环次数较少的话，执行时间应该也不会太长， 所以使用int类型或范围更小的数据类型作为索引值的循环默认是不会被放置安全点的
   *
   * 这种循环被称为可数循环（Counted Loop）。 相对应地，使用 long
   * 或者范围更大的数据类型作为索引值的循环就被称为不可数循环（Uncounted Loop），将会被放置安全点。。
   *
   * HotSpot原本提供了-XX:+UseCountedLoopSafepoints 参数去强制在可数循环中也放置安全点，不过这个参数在 JDK 8 下有
   * Bug，有导致虚拟机崩溃的风险
   *
   */
}

package concurrency_3;

/**
 * 优化二： 同步消除。如果发现某个对象只能从一个线程可访问，那么在这个对象上的操作可以不需要同步
 * 虚拟机配置参数：-XX:+PrintGC -Xms500M -Xmn500M -XX:+DoEscapeAnalysis。配置500M是保证不触发GC。
 * 把逃逸分析关掉：-XX:+PrintGC -Xms500M -Xmn500M -XX:-DoEscapeAnalysis
 * 说明了逃逸分析把锁消除了，并在性能上得到了很大的提升。这里说明一下Java的逃逸分析是方法级别的，因为JIT的即时编译是方法级别。
 * 
 * 优点三 分离对象或标量替换。
 * 这个简单来说就是把对象分解成一个个基本类型，并且内存分配不再是分配在堆上，而是分配在栈上。
 * 这样的好处有，一、减少内存使用，因为不用生成对象头。 二、程序内存回收效率高，并且GC频率也会减少，总的来说和上面优点一的效果差不多。
 */
public class EscapeOptimize2 {
    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5_000_000; i++) {
            createObject();
        }
        System.out.println("cost = " + (System.currentTimeMillis() - start) + "ms");
    }

    public static void createObject() {
        synchronized (new Object()) {

        }
    }
}
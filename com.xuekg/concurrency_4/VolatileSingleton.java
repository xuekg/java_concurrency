package concurrency_4;

/**
 * 演示volatile 单例模式应用（双边检测）
 * 典型的用法：检查某个状态标记以判断是否退出循环
 * 注意：当且仅当满足以下所有条件时，才应该用volatile变量
    对变量的写入操作不依赖变量的当前值，或者你能确保只有单个线程更新变量的值。
    该变量不会与其他的状态一起纳入不变性条件中。
    在访问变量时不需要加锁。
 */
public class VolatileSingleton {
    private static VolatileSingleton instance = null;

    private VolatileSingleton() {
        System.out.println(Thread.currentThread().getName() + "\t 我是构造方法SingletonDemo");
    }

    public static VolatileSingleton getInstance() {
        // 第一重检测
        if (instance == null) {
            // 锁定代码块
            synchronized (VolatileSingleton.class) {
                // 第二重检测
                if (instance == null) {
                    // 实例化对象
                    instance = new VolatileSingleton();
                    //上述代码可以看做三个操作
                    /**
                     * memory = allocate(); // 1、分配对象内存空间
                       instance(memory); // 2、初始化对象
                       instance = memory; // 3、设置instance指向刚刚分配的内存地址，此时instance != null
                       1-2-3 可以变成 1-3-2
                       如果另外一个线程执行：if(instance == null)时，则返回刚刚分配的内存地址，但是对象还没有初始化完成，拿到的instance是个假的
                     */
                }
            }
        }
        return instance;
    }
}
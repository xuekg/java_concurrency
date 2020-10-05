package concurrency_3;

/*
    锁粗化
    JIT编译器在执行动态编译时，若发现前后相邻的synchronized块使用的是同一个锁对象，那么它就会把这几个synchronized块给合并为一个较大
    的同步块，这样做的好处在于线程在执行这些代码时，就无需频繁申请与释放锁了，从而达到申请与释放锁一次，就可以执行完全部的同步代码块，从而
    提升了性能
 */
public class MyTest5 {
    private Object object = new Object();

    public void method() {

        synchronized (object) {
            System.out.println("hello world");
        }

        synchronized (object) {
            System.out.println("welcome");
        }

        synchronized (object) {
            System.out.println("person");
        }
    }
}

/**
 * public void method();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=5, args_size=1
         0: aload_0
         1: getfield      #3                  // Field object:Ljava/lang/Object;
         4: dup
         5: astore_1
         6: monitorenter
         7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: ldc           #5                  // String hello world
        12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        15: aload_1
        16: monitorexit
        17: goto          25
        20: astore_2
        21: aload_1
        22: monitorexit
        23: aload_2
        24: athrow
        25: aload_0
        26: getfield      #3                  // Field object:Ljava/lang/Object;
        29: dup
        30: astore_1
        31: monitorenter
        32: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        35: ldc           #7                  // String welcome
        37: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        40: aload_1
        41: monitorexit
        42: goto          50
        45: astore_3
        46: aload_1
        47: monitorexit
        48: aload_3
        49: athrow
        50: aload_0
        51: getfield      #3                  // Field object:Ljava/lang/Object;
        54: dup
        55: astore_1
        56: monitorenter
        57: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        60: ldc           #8                  // String person
        62: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        65: aload_1
        66: monitorexit
        67: goto          77
        70: astore        4
        72: aload_1
        73: monitorexit
        74: aload         4
        76: athrow
        77: return
 * 
 */
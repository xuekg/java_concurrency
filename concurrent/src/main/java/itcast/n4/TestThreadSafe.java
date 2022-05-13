package itcast.n4;

import java.util.ArrayList;

public class TestThreadSafe {

    static final int THREAD_NUMBER = 2;
    static final int LOOP_NUMBER = 200;

    public static void main(String[] args) {
        ThreadSafeSubClass test = new ThreadSafeSubClass();
        for (int i = 0; i < THREAD_NUMBER; i++) {
            new Thread(() -> {
                test.method1(LOOP_NUMBER);
            }, "Thread" + (i + 1)).start();
        }
    }
}

class ThreadUnsafe {

    //成员变量
    ArrayList<String> list = new ArrayList<>();

    public void method1(int loopNumber) {
        for (int i = 0; i < loopNumber; i++) {
            method2();
            method3();
        }
    }

    private void method2() {
        list.add("1");
    }

    private void method3() {
        list.remove(0);
    }
}

class ThreadSafe {

    public final void method1(int loopNumber) {
        //局部变量
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < loopNumber; i++) {
            method2(list);
            method3(list);
        }
    }

    public void method2(ArrayList<String> list) {
        list.add("1");
    }

    public void method3(ArrayList<String> list) {
        System.out.println(1);
        list.remove(0);
    }
}

class ThreadSafeSubClass extends ThreadSafe {

    /**
     * 这是线程不安全的
     * Exception in thread "Thread-376" java.lang.IndexOutOfBoundsException: Index: 0, Size: 0
     * 	at java.util.ArrayList.rangeCheck(ArrayList.java:653)
     * 	at java.util.ArrayList.remove(ArrayList.java:492)
     * 	at itcast.n4.ThreadSafeSubClass.lambda$method3$1(TestThreadSafe.java:68)
     * 	at java.lang.Thread.run(Thread.java:745)
     *
     * 	新开了个线程来操作list，list就是被多线程共享了，就会有安全问题
     * @param list
     */
    @Override
    public void method3(ArrayList<String> list) {
        System.out.println(2);
        new Thread(() -> {
            list.remove(0);
        }).start();
    }
}
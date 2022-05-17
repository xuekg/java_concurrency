package itcast.test;

/**
 * @author xuekg
 * @description 参见test32
 * @date 2022/5/17 21:31
 **/
public class Test32_1 {

    private static boolean isStop = false;

    /**
     * JVM 会尽力保证内存的可见性，即便这个变量没有被同步关键字修饰。
     * 也就是说，只要 CPU 有时间，JVM 会尽力去保证变量值的更新
     *
     * 使用Thread.sleep(500);模拟System.out.println()同步输出时CPU的空闲时间，
     * 可以发现程序也可以正常退出，并不会一直死循环，这是由于此时CPU有时间去保证内存的可见性。
     *
     * @param args
     */
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                while (!isStop) {
                    // System.out.println("hello");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
        }.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        isStop = true;
    }

}

package itcast.n8.d_0714;

/**
 * @author xuekg
 * @description 这个例子一般用来解释可见性问题
 * t1,t2运行在不同的CPU上，running作为共享数据，被加载到不同的CPU缓存中
 * t2在缓存中对running进行的修改对t1来说是不可见的，因为不会使t1中的while循环结束
 *
 * 作者说，t1中的while循环一直运行不退出，和可见性没有一点关系，那是因为什么呢？
 * @date 2022/7/14 15:10
 **/
public class VolatileTest {

    private static boolean running = true;
    private static int count = 0;

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (running) {
                    count++;
                }
                System.out.println("count: " + count);
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                running = false;
            }
        });

        t1.start();
//        Thread.sleep(1000);
        t2.start();
        t1.join();
        t2.join();
    }
}

package concurrency_2;

public class MyThreadTest {
    public static void main(String[] args) {
        MyThread myThread = new MyThread();
        Thread t1 = new Thread(myThread);
        Thread t2 = new Thread(myThread);

        t1.start();
        t2.start();
        /**
         * 可能出现的情况
         * result:0
         * result:0
         * result:1
         * result:2
         * result:3
         * ......
         */
    }
}

class MyThread implements Runnable {
    int x;

    @Override
    public void run() {
        x = 0;
        while (true) {
            System.out.println("result:" + x++);
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            if (x == 30) {
                break;
            }
        }
    }
}
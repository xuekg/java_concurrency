package aqs;

/**
 * @author xuekg
 * @description
 * @date 2021/8/23 19:43
 **/
public class ReentrantLockDemo {

    public volatile int inc = 0;

    public void increase() {
        inc++;
    }

    public static void main(String[] args) {
        final ReentrantLockDemo test = new ReentrantLockDemo();
        for(int i=0;i<10;i++){
            new Thread(){
                @Override
                public void run() {
                    for(int j=0;j<1000;j++) {
                        test.increase();
                    }
                };
            }.start();
        }

        while(Thread.activeCount()>2)  //保证前面的线程都执行完
        {
            Thread.yield();
        }
        System.out.println(test.inc);
    }
}

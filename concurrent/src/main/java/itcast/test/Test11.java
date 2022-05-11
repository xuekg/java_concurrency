package itcast.test;

import lombok.extern.slf4j.Slf4j;

@Slf4j(topic = "c.Test11")
public class Test11 {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            log.debug("sleep...");
            try {
                Thread.sleep(5000); // wait, join
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        },"t1");

        t1.start();
        Thread.sleep(1000);
        log.debug("interrupt");
        /**
         * 1.调用正常运行线程的isInterrupted方法返回false，即打断标记默认为false
         * 2.调用正常运行中线程的interrupt方法会将打断标记置为true并抛出打断异常interrupted exception
         * 3.interrupt一个被sleep，wait，join锁住的方法，会将打断标记置为true，然后清除打断标记，然后throw interrupted exception
         */
        t1.interrupt();
        // TODO: 2022/5/11 这里可能是true，也可能是false ？？
        //调用t1.interrupt()后立即调用t1.isInterrupted()，有可能在t1还未完成清除打断标记并抛异常的时候就查看打断标记，此时仍然为true，
        // 留给清除打断标记时间
        Thread.sleep(500);
        log.debug("打断标记:{}", t1.isInterrupted());
    }
}

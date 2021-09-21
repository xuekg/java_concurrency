package countdownlatch;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author xuekg
 * @description
 * @date 2021/9/11 11:48
 **/
public class LinkedBlockingQueueDemo {

    public static void main(String[] args) throws InterruptedException {

        LinkedBlockingQueue<String> queue = new LinkedBlockingQueue<>();

        queue.put("d");
        queue.put("de");
        queue.put("df");

        String take = queue.take();
        System.out.println(take);
    }
}

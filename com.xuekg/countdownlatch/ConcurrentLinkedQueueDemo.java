package countdownlatch;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * @author xuekg
 * @description
 * @date 2021/9/11 10:14
 **/
public class ConcurrentLinkedQueueDemo {

    public static void main(String[] args) {
        ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        queue.offer("a");
        queue.offer("b");
        queue.offer("c");

        String poll = queue.poll();

        String poll1 = queue.poll();

        boolean c = queue.remove("c");
        /*ConcurrentLinkedQueue<String> queue = new ConcurrentLinkedQueue<>();
        print(queue);
        queue.offer("aaa");
        print(queue);
        queue.offer("bbb");
        print(queue);
        queue.offer("ccc");
        print(queue);*/
    }

    /*head: 1018081122
    head: 1018081122
    head: 242131142
    head: 242131142*/
    /**
     * 打印并发队列head属性的identityHashCode
     *
     * @param queue
     */
    private static void print(ConcurrentLinkedQueue queue) {
        Field field = null;
        boolean isAccessible = false;
        try {
            field = ConcurrentLinkedQueue.class.getDeclaredField("head");
            isAccessible = field.isAccessible();
            if (!isAccessible) {
                field.setAccessible(true);
            }
            System.out.println("head: " + System.identityHashCode(field.get(queue)));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            field.setAccessible(isAccessible);
        }
    }
}

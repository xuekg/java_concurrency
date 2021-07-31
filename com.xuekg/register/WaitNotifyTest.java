package register;

import java.util.LinkedList;

/**
 * @author xuekg
 * @description 存在虚假唤醒问题
 * @date 2021/7/31 15:44
 **/
public class WaitNotifyTest {

    private final static int MAX_SIZE = 100;

    private LinkedList<String> queue = new LinkedList<>();

    public synchronized void offer(String element) {
        try {
            if (queue.size() == MAX_SIZE) {
                // 一个线程只要执行到这一步，说白了，已经获取到了一把锁
                // 就是说让线程进入一个等待的状态，释放掉锁
                wait();
            }
            queue.addLast(element);
            notifyAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public synchronized String take() {
        // 别的线程就可以在这里去从队列里take数据
        String element = null;

        try {
            // TODO: 2021/7/31 虚假唤醒
            if (queue.size() == 0) {
                wait(); // 将这个锁释放掉，陷入等待中，等待别的线程在队列里放入数据
            }
            element = queue.removeFirst();
            notifyAll(); // 唤醒当前在等待这个锁的那些线程
        } catch (Exception e) {
            e.printStackTrace();
        }

        return element;
    }
}

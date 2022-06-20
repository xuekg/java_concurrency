package itcast.n7;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuekg
 * @description
 * 生产者和消费者都在竞争同一把锁，而实际上两者没有同步关系，
 * 由于 Condition 能够支持多个等待队列以及不响应中断，
 * 所以我们可以将生产者和消费者的等待条件和锁资源分离，从而进一步优化系统并发性能
 * @date 2022/6/20 17:40
 **/
public class LockConditionTest {

    private LinkedList<String> product = new LinkedList<String>();

    private int maxInventory = 10; // 最大库存

    private Lock lock = new ReentrantLock();// 资源锁

    private Condition condition = lock.newCondition();// 库存非满和非空条件

    /**
     * 新增商品库存
     *
     * @param e
     */
    public void produce(String e) {
        lock.lock();
        try {
            while (product.size() == maxInventory) {
                condition.await();
            }

            product.add(e);
            System.out.println("放入一个商品库存，总库存为：" + product.size());
            condition.signalAll();

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 消费商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        lock.lock();
        try {
            while (product.size() == 0) {
                condition.await();
            }

            result = product.removeLast();
            System.out.println("消费一个商品，总库存为：" + product.size());
            condition.signalAll();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

        return result;
    }

    /**
     * 生产者
     *
     * @author admin
     */
    private class Producer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                produce("商品" + i);
            }
        }

    }

    /**
     * 消费者
     *
     * @author admin
     */
    private class Customer implements Runnable {

        @Override
        public void run() {
            for (int i = 0; i < 20; i++) {
                consume();
            }
        }
    }

    public static void main(String[] args) {

        LockConditionTest lc = new LockConditionTest();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }
}

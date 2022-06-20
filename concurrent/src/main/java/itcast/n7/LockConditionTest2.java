package itcast.n7;

import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xuekg
 * @description
 * 我们分别创建 productLock 以及 consumerLock 两个锁资源，
 * 前者控制生产者线程并行操作，后者控制消费者线程并发运行；
 * 同时也设置两个条件变量，一个是 notEmptyCondition，负责控制消费者线程状态，
 * 一个是 notFullCondition，负责控制生产者线程状态。
 *
 * 这样优化后，可以减少消费者与生产者的竞争，实现两者并发执行。
 *
 * 我们这里是基于 LinkedList 来存取库存的，虽然 LinkedList 是非线程安全，
 * 但我们新增是操作头部，而消费是操作队列的尾部，理论上来说没有线程安全问题。
 * 而库存的实际数量 inventory 是基于 AtomicInteger（CAS 锁）线程安全类实现的，
 * 既可以保证原子性，也可以保证消费者和生产者之间是可见的。
 * @date 2022/6/20 17:43
 **/
public class LockConditionTest2 {

    private LinkedList<String> product = new LinkedList<String>();
    private AtomicInteger inventory = new AtomicInteger(0);//实时库存

    private int maxInventory = 10; // 最大库存

    private Lock consumerLock = new ReentrantLock();// 资源锁
    private Lock productLock = new ReentrantLock();// 资源锁

    private Condition notEmptyCondition = consumerLock.newCondition();// 库存满和空条件
    private Condition notFullCondition = productLock.newCondition();// 库存满和空条件

    /**
     * 新增商品库存
     *
     * @param e
     */
    public void produce(String e) {
        productLock.lock();
        try {
            while (inventory.get() == maxInventory) {
                notFullCondition.await();
            }

            product.add(e);

            System.out.println("放入一个商品库存，总库存为：" + inventory.incrementAndGet());

            if (inventory.get() < maxInventory) {
                notFullCondition.signalAll();
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            productLock.unlock();
        }

        if (inventory.get() > 0) {
            try {
                consumerLock.lockInterruptibly();
                notEmptyCondition.signalAll();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                consumerLock.unlock();
            }
        }

    }

    /**
     * 消费商品
     *
     * @return
     */
    public String consume() {
        String result = null;
        consumerLock.lock();
        try {
            while (inventory.get() == 0) {
                notEmptyCondition.await();
            }

            result = product.removeLast();
            System.out.println("消费一个商品，总库存为：" + inventory.decrementAndGet());

            if (inventory.get() > 0) {
                notEmptyCondition.signalAll();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            consumerLock.unlock();
        }

        if (inventory.get() < maxInventory) {

            try {
                productLock.lockInterruptibly();
                notFullCondition.signalAll();
            } catch (InterruptedException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            } finally {
                productLock.unlock();
            }
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

        LockConditionTest2 lc = new LockConditionTest2();
        new Thread(lc.new Producer()).start();
        new Thread(lc.new Customer()).start();

    }
}

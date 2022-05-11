package aqs;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuekg
 * @description 当读锁被使用时，如果有线程尝试获取写锁，是会被阻塞的
 * 但反之是可以的
 * @date 2021/9/25 0:12
 **/
public class LockDownGradingDemo {

    public static void main(String[] args) {

        ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();

        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();

        writeLock.lock();
        System.out.println("--------111");

        readLock.lock();
        System.out.println("-------2222");

        writeLock.unlock();
        writeLock.lock();
        readLock.unlock();
        readLock.unlock();
    }
}

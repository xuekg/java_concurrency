package aqs;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author xuekg
 * @description
 * @date 2021/8/24 20:32
 **/
public class ReadWriteLockDemo {
    public static void main(String[] args) {
        ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

        lock.readLock().lock();
        lock.readLock().unlock();

        lock.writeLock().lock();
        lock.writeLock().lock();
        lock.writeLock().unlock();
    }
}

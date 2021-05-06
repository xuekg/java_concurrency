package com.zoo;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.framework.recipes.locks.Locker;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @author xuekg
 * @description
 *
 * Curator 内置了几种锁给我们使用，并且都可以通过 Locker 包装使用
 *
 * InterProcessMultiLock 可以同时对几个路径加锁，释放也是同时的
 * InterProcessMutex 可重入排他锁
 * InterProcessReadWriteLock 读写锁
 * InterProcessSemaphoreMutex 不可重入排他锁
 * 如果想看看优秀的 ZK 分布式锁如何写的话，直接翻 curator-recipes 它的源码吧～
 *
 * Curator 提供的还不止是分布式锁，它还提供了分布式队列，分布式计数器，分布式屏障，分布式原子类等，
 * @date 2021/4/30 18:50
 **/
public class CuratorTest {

    public static void main(String[] args) throws Exception {
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("127.0.0.1:2181", retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/lock");
        try (Locker locker = new Locker(lock)) {
            // 使用 try-with-resources 语法糖自动释放锁
            System.out.println("获取到锁后的业务逻辑");
        }
        client.close();
    }
}

package com.zoo;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @author xuekg
 * @description
 * 非公平锁的缺点在故事中也体现了，就是当前一个持有锁的进程释放之后，其他所有等待锁的进程都会被通知，这个就是经常在面试题中提到的“羊群效应”，从而再去争抢该锁，
 * 但是因为又只有一个进程能抢到锁，其他的进程会重新继续等待循环下去，所以在应对高并发场景的情况下该方案有较严重的性能问题，极大的增大了服务端的压力。
 *
 * 而公平锁的话，每一个没有获取到锁的进程往往只需要关心排在它的前一个进程的情况，每次也只有一个进程会被唤醒，所以如果采用 ZK 作为分布式锁的中间件的话，建议采用公平锁的方式。
 * @date 2021/4/30 18:43
 **/
public class ZookeeperUnfairLock {

    /**
     * ZK 的非公平锁用到了相同路径无法重复创建加上临时节点的特性，用临时节点是因为如果当获取锁的进程崩溃后，
     * 没来得及释放锁的话会造成死锁，但临时节点会在客户端的连接断开后自动删除，所以规避了死锁的这个风险。
     */
    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        //非公平锁
        ZooKeeper client = new ZooKeeper("127.0.0.1:2181", 3000, null);
        try {
            // 之前有提过必须保证 鸡太美 的路径存在
            client.create("/鸡太美/演唱会", "Data 没有用随便写".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
            // 创建成功的话就是获取到锁了, 之后执行业务逻辑
            System.out.println("我是拿到锁以后的业务逻辑");
            // 处理完业务记得一定要删除该节点，表示释放锁，实际场景中这一步删除应该是在 finally 块中
            client.delete("/鸡太美/演唱会", -1);
        } catch (KeeperException.NodeExistsException e) {
            // 如果报 NodeExistsException 就是没获取到锁
            System.out.println("锁被别人获取了");
            // 对这个节点进行监听
            client.exists("/鸡太美/演唱会", new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType().equals(Event.EventType.NodeDeleted)) {
                        // 如果监听到了删除事件就是上一个进程释放了锁, 尝试重新获取锁
                        // 这里就牵涉到这次再获取失败要继续监听的递归过程, 其实需要一个封装好的类似 lock 方法，伪代码这里就不继续演示了
                        try {
                            client.create("/鸡太美/演唱会", "Data 没有用随便写".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
                        } catch (KeeperException keeperException) {
                            keeperException.printStackTrace();
                        } catch (InterruptedException interruptedException) {
                            interruptedException.printStackTrace();
                        }
                    }
                }
            });
        }
    }
}

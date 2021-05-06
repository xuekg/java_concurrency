package com.zoo;

import org.apache.zookeeper.*;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * @author xuekg
 * @description ZK 的公平锁用到了临时顺序节点，序号无法重复的特性，当前的最小子节点才视为获取锁成功。
 * @date 2021/4/30 18:47
 **/
public class ZookeeperFairLock {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {
        ZooKeeper client = new ZooKeeper("127.0.0.1:2181", 3000, null);
        String currentPath = client.create("/鸡太美/演唱会", "Data 没有用随便写".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        // 因为有序号的存在，所以一定会创建成功
        // 然后就是获取父节点下的所有子节点的名称
        List<String> children = client.getChildren("/鸡太美", false);
        // 先排序
        Collections.sort(children);
        if (children.get(0).equals(currentPath)) {
            // 当前路径是最小的那个节点，获取锁成功
            System.out.println("我是拿到锁以后的业务逻辑");
            // 同样记得业务处理完一定要删除该节点
            client.delete(currentPath, -1);
        } else {
            // 不是最小节点，获取锁失败
            // 根据当前节点路径在所有子节点中获取序号相比自己小 1 的那个节点
            String preNode = getPreNode(currentPath, children);
            // 对该节点进行监听
            client.exists(preNode, new Watcher() {
                @Override
                public void process(WatchedEvent event) {
                    if (event.getType().equals(Event.EventType.NodeDeleted)) {
                        // 和非公平锁一样，再次尝试获取锁，由于顺序节点的缘故，所以此次获取锁应该是不会失败的
                    }
                }
            });
        }
    }
}

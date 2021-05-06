package com.zoo;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

;import java.io.IOException;
import java.util.List;

/**
 * @author xuekg
 * @description
 * @date 2021/4/30 18:22
 **/
public class ZookeeperBase {

    public static void main(String[] args) throws IOException, KeeperException, InterruptedException {

        //ZooKeeper第一个字符串就是连接的服务端地址，/ 后面就是 chroot， 就是小故事里的标签，之后该客户端所有的操作都会以/鸡太美作为顶层路径去处理
        ZooKeeper client = new ZooKeeper("127.0.0.1:2181/鸡太美", 3000, null);

        //官方的客户端是没有递归创建的功能的，所以在创建多级路径的时候，客户端需要自己确保路径中的父级节点是存在的！
        //直接运行是会报错的，所以需要逐级创建 20201101 的父路径，最终才能成功，
        client.create("/更新视频/跳舞/20201101", "这是Data，既可以记录一些业务数据也可以随便写".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);

        /**
         * 最后的 CreateMode.PERSISTENT 代表当前节点是一个持久类型的节点，3.6.2 中一共有 7 种类型，下面列出并且给出简单解释：
         *
         * PERSISTENT									// 持久节点，一旦创建成功不会被删除，除非客户端主动发起删除请求
         * PERSISTENT_SEQUENTIAL 					     // 持久顺序节点，会在用户路径后面拼接一个不会重复的字增数字后缀，其他同上
         * EPHEMERAL									// 临时节点，当创建该节点的客户端链接断开后自动被删除
         * EPHEMERAL_SEQUENTIAL						    // 临时顺序节点，基本同上，也是增加一个数字后缀
         * CONTAINER									// 容器节点，一旦子节点被删除完就会被服务端删除
         * PERSISTENT_WITH_TTL							// 带过期时间的持久节点，带有超时时间的节点，如果超时时间内没有子节点被创建，就会被删除
         * PERSISTENT_SEQUENTIAL_WITH_TTL	            // 带过期时间的持久顺序节点，基本同上，多了一个数字后缀
         *
         * 大家可能比较熟悉前四种，对后三种不太熟悉，特别是最后两种带 TTL 的类型，这两种类型在 ZK 默认配置下还是不支持的，
         * 需要在 zoo.cfg 配置中添加 extendedTypesEnabled=true 启用扩展功能，否则的话就会收到 Unimplemented for 的错误。
         */

        //官方的客户端也不支持递归删除，需要确保删除的节点是叶子节点，否则就会收到错误，我们这里把 20201101 给删除：
        client.delete("/更新视频/跳舞/20201101", -1);
        //-1 是一个 version 字段，相当于 ZK 提供的乐观锁机制，如果是 -1 的话就是无视节点的版本信息。

        //由于创建和删除都不支持递归，所以需要对目标路径进行判断是否存在来决定是否进行下一步
        Stat stat = client.exists("/更新视频", false);
        System.out.println(stat != null ? "存在" : "不存在"); // 存在
        //false 意思是不进行订阅，关于订阅之后会一起说。

        //ZK 是一个树形的结构，有父子节点概念，所以可以查询某一个节点下面的所有子节点
        List<String> children = client.getChildren("/更新视频", false);
        System.out.println(children); // [跳舞]

        /*上面介绍的三个方法：判断路径是否存在、获取数据、获取子节点列表，这三种方法（包括他们的重载方法），都可以对路径进行订阅，订阅的方式有两种：

        传递一个 boolean 值，如果使用此方式的话，回调对象就是创建 ZooKeeper 时的第三个参数 defaultWatcher，只不过之前示例中是 null
        直接在方法中传入一个 Watcher 的实现类，此实现类会作为此路径之后的回调对象（推荐）
        下面分别演示下：*/
        // 方式1
        ZooKeeper client1 = new ZooKeeper("127.0.0.1:2181/鸡太美", 3000, new Watcher() {
            // 这个就是 defaultWatcher 参数，是当前客户端默认的回调实现
            @Override
            public void process(WatchedEvent event) {
                System.out.println("这是本客户端全局的默认回调对象");
            }
        });
        // exists
        client1.exists("/更新视频", true);
        // getData
        client1.getData("/更新视频/跳舞", true, null);
        // getChildren
        client1.getChildren("/更新视频", true);

        // 方式2

        // exists
        client1.exists("/更新视频", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("我是回调对象的实现");
            }
        });
        // getData
        client1.getData("/更新视频/跳舞", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("我是回调对象的实现");
            }
        }, null);
        // getChildren
        client1.getChildren("/更新视频", new Watcher() {
            @Override
            public void process(WatchedEvent event) {
                System.out.println("我是回调对象的实现");
            }
        });

        // TODO: 2021/4/30 回调是怎么每次能触发到对应的方法的
    }
}

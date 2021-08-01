package register.atomic;

import java.util.concurrent.atomic.LongAdder;

/**
 * @author xuekg
 * @description 大量线程并发更新一个原子类的时候，还是有一个自旋的问题，并发性能还是有待提升
 * LongAdder 使用了一个比较简单的原理，解决了 AtomicLong 类，在极高竞争下的性能问题。
 * 但是 LongAdder 的具体实现却非常精巧和细致，分散竞争，逐步升级竞争的解决方案，相当漂亮，值得我们细细品味。
 * @date 2021/8/1 18:21
 **/
public class LongAdderDemo {

    public static void main(String[] args) {
        LongAdder longAdder = new LongAdder();

    }
}

package concurrency_4;

/**
 * * 计算机在执行程序时，为了提高性能，编译器和处理器常常会对指令做重排序。 指令重排是要保证单线程下程序结果不变的情况下做重排。
 * 
 * 定义了变量num=0和变量flag=false，线程1调用初始化函数init()执行后，线程调用add()方法，
 * 当另外线程判断flag=true后，执行num+100操作，那么我们预期的结果是num会等于101，
 * 但因为有指令重排的可能，num=1和flag=true执行顺序可能会颠倒，以至于num可能等于100
 */
public class VolatileResort {
    static int num = 0;
    //在volatile生成的指令序列前后插入内存屏障（Memory Barries）来禁止处理器重排序。
    static volatile boolean flag = false;

    public static void init() {
        num = 1;
         /**
         * 在每个volatile写操作的前面插入一个StoreStore屏障（写-写 屏障）。
         在每个volatile写操作的后面插入一个StoreLoad屏障（写-读 屏障）。
            StoreStore屏障可以保证在volatile写（flag赋值操作flag=true）之前，其前面的所有普通写（num的赋值操作num=1)
            操作已经对任意处理器可见了，保障所有普通写在volatile写之前刷新到主内存。
        */
        flag = true;
    }

    public static void add() {
        /**
         * 在每个volatile读操作的后面插入一个LoadLoad屏障（读-读 屏障）。
           在每个volatile读操作的后面插入一个LoadStore屏障（读-写 屏障）。
            LoadStore屏障可以保证其后面的所有普通写（num的赋值操作num=num+5) 操作必须在volatile读（if(flag)）之后执行。
         */
        if (flag) {
            //num是否为1是不确定的，因为init方法里面会进行指令重排 可能num还是0的时候 下面的语句就执行了
            num = num + 5;
            System.out.println("num:" + num);
        }
    }

    public static void main(String[] args) {
        init();
        new Thread(() -> {
            add();
        }, "子线程").start();
    }
}
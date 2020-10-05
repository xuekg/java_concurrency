package concurrency_3;

/**
 * 优化一：
 * 将堆分配转化为栈分配。如果某个对象在子程序中被分配，
 * 并且指向该对象的指针永远不会逃逸，该对象就可以在分配在栈上，而不是在堆上。
 * 在有垃圾收集的语言中，这种优化可以降低垃圾收集器运行的频率。
 * 虚拟机配置参数：-XX:+PrintGC -Xms5M -Xmn5M -XX:+DoEscapeAnalysis
    -XX:+DoEscapeAnalysis表示开启逃逸分析，JDK8是默认开启的
    -XX:+PrintGC 表示打印GC信息
    -Xms5M -Xmn5M 设置JVM内存大小是5M

    把虚拟机参数改成 -XX:+PrintGC -Xms5M -Xmn5M -XX:-DoEscapeAnalysis。关闭逃逸分析得到结果，说明了进行了GC，并且次数还不少。
    这说明了JVM在逃逸分析之后，将对象分配在了方法createObject()方法栈上。方法栈上的对象在方法执行完之后，栈桢弹出，
    对象就会自动回收。这样的话就不需要等内存满时再触发内存回收。这样的好处是程序内存回收效率高，并且GC频率也会减少，程序的性能就提高了。
 */
public class EscapeOptimize{
    public static void main(String[] args){
        for(int i = 0; i < 5_000_000; i++){
            createObject();
        }
    }

    public static void createObject(){
        new Object();
    }
}
package concurrency_3;

/**
 * 当我们使用synchronized关键字来修饰代码块时，字节码层面上是通过monitorenter与monitorexit指令来实现的锁的获取与释放动作。
 * 当线程进入到monitorenter指令后，线程将会持有Monitor对象，退出monitorenter指令后，线程将会释放Monitor对象
 * 
 */
public class MyTest1 {

    private Object object = new Object();

    public void method() {
        synchronized (object) {
            System.out.println("hello world");
            //抛出异常后，反编译后就只有一个monitoeexit了，只会走抛异常结束方法这一个步骤
            //不会多一个monitorexit来确保同步能结束
            throw new RuntimeException();
        }
    }
    public void method2() {
        synchronized (object) {
            /**
             * 注意1：
             * 可以看到上面反编译的结果里有两个monitorexit,第二个monitorexit其实是为了在出现异常时退出monitor使用的。
             * 注意2：
             * synchronized依赖于monitor来实现同步，Object中的wait和notify其实也是依赖于monitor实现的，所以当不在同步块执行这两个方法时会报错：
             * java.lang.IllegalMonitorStateException
             */
            System.out.println("welcome");
        }
    }
}

/**
 * public class com.shengsiyuan.concurrency3.MyTest1
  minor version: 0
  major version: 52
  flags: ACC_PUBLIC, ACC_SUPER
Constant pool:
   #1 = Methodref          #2.#28         // java/lang/Object."<init>":()V
   #2 = Class              #29            // java/lang/Object
   #3 = Fieldref           #10.#30        // com/shengsiyuan/concurrency3/MyTest1.object:Ljava/lang/Object;
   #4 = Fieldref           #31.#32        // java/lang/System.out:Ljava/io/PrintStream;
   #5 = String             #33            // hello world
   #6 = Methodref          #34.#35        // java/io/PrintStream.println:(Ljava/lang/String;)V
   #7 = Class              #36            // java/lang/RuntimeException
   #8 = Methodref          #7.#28         // java/lang/RuntimeException."<init>":()V
   #9 = String             #37            // welcome
  #10 = Class              #38            // com/shengsiyuan/concurrency3/MyTest1
  #11 = Utf8               object
  #12 = Utf8               Ljava/lang/Object;
  #13 = Utf8               <init>
  #14 = Utf8               ()V
  #15 = Utf8               Code
  #16 = Utf8               LineNumberTable
  #17 = Utf8               LocalVariableTable
  #18 = Utf8               this
  #19 = Utf8               Lcom/shengsiyuan/concurrency3/MyTest1;
  #20 = Utf8               method
  #21 = Utf8               StackMapTable
  #22 = Class              #38            // com/shengsiyuan/concurrency3/MyTest1
  #23 = Class              #29            // java/lang/Object
  #24 = Class              #39            // java/lang/Throwable
  #25 = Utf8               method2
  #26 = Utf8               SourceFile
  #27 = Utf8               MyTest1.java
  #28 = NameAndType        #13:#14        // "<init>":()V
  #29 = Utf8               java/lang/Object
  #30 = NameAndType        #11:#12        // object:Ljava/lang/Object;
  #31 = Class              #40            // java/lang/System
  #32 = NameAndType        #41:#42        // out:Ljava/io/PrintStream;
  #33 = Utf8               hello world
  #34 = Class              #43            // java/io/PrintStream
  #35 = NameAndType        #44:#45        // println:(Ljava/lang/String;)V
  #36 = Utf8               java/lang/RuntimeException
  #37 = Utf8               welcome
  #38 = Utf8               com/shengsiyuan/concurrency3/MyTest1
  #39 = Utf8               java/lang/Throwable
  #40 = Utf8               java/lang/System
  #41 = Utf8               out
  #42 = Utf8               Ljava/io/PrintStream;
  #43 = Utf8               java/io/PrintStream
  #44 = Utf8               println
  #45 = Utf8               (Ljava/lang/String;)V
{
  public com.shengsiyuan.concurrency3.MyTest1();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=3, locals=1, args_size=1
         0: aload_0
         1: invokespecial #1                  // Method java/lang/Object."<init>":()V
         4: aload_0
         5: new           #2                  // class java/lang/Object
         8: dup
         9: invokespecial #1                  // Method java/lang/Object."<init>":()V
        12: putfield      #3                  // Field object:Ljava/lang/Object;
        15: return
      LineNumberTable:
        line 9: 0
        line 11: 4
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      16     0  this   Lcom/shengsiyuan/concurrency3/MyTest1;

  public void method();
    descriptor: ()V
    flags: ACC_PUBLIC
    Code:
      stack=2, locals=3, args_size=1
         0: aload_0
         1: getfield      #3                  // Field object:Ljava/lang/Object;
         4: dup
         5: astore_1
         6: monitorenter
         7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
        10: ldc           #5                  // String hello world
        12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
        15: new           #7                  // class java/lang/RuntimeException
        18: dup
        19: invokespecial #8                  // Method java/lang/RuntimeException."<init>":()V
        22: athrow
        23: astore_2
        24: aload_1
        25: monitorexit
        26: aload_2
        27: athrow
      Exception table:
         from    to  target type
             7    26    23   any
      LineNumberTable:
        line 14: 0
        line 15: 7
        line 16: 15
        line 17: 23
      LocalVariableTable:
        Start  Length  Slot  Name   Signature
            0      28     0  this   Lcom/shengsiyuan/concurrency3/MyTest1;
      StackMapTable: number_of_entries = 1
        frame_type = 255 
        offset_delta = 23
        locals = [ class com/shengsiyuan/concurrency3/MyTest1, class java/lang/Object ]
        stack = [ class java/lang/Throwable ]

public void method2();
  descriptor: ()V
  flags: ACC_PUBLIC
  Code:
    stack=2, locals=3, args_size=1
       0: aload_0
       1: getfield      #3                  // Field object:Ljava/lang/Object;
       4: dup
       5: astore_1
       6: monitorenter
       7: getstatic     #4                  // Field java/lang/System.out:Ljava/io/PrintStream;
      10: ldc           #9                  // String welcome
      12: invokevirtual #6                  // Method java/io/PrintStream.println:(Ljava/lang/String;)V
      15: aload_1
      16: monitorexit
      17: goto          25
      20: astore_2
      21: aload_1
      22: monitorexit
      23: aload_2
      24: athrow
      25: return
    Exception table:
       from    to  target type
           7    17    20   any
          20    23    20   any
    LineNumberTable:
      line 21: 0
      line 22: 7
      line 23: 15
      line 24: 25
    LocalVariableTable:
      Start  Length  Slot  Name   Signature
          0      26     0  this   Lcom/shengsiyuan/concurrency3/MyTest1;
    StackMapTable: number_of_entries = 2
      frame_type = 255  full_frame 
        offset_delta = 20
        locals = [ class com/shengsiyuan/concurrency3/MyTest1, class java/lang/Object ]
        stack = [ class java/lang/Throwable ]
      frame_type = 250  chop 
        offset_delta = 4
}
SourceFile: "MyTest1.java"
 * 
 */
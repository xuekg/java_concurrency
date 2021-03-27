package jvm;

public class StringIntern {

  public static void main(String[] args) {

    String s = new String("1");
    s.intern();// 调用此方法之前，字符串常量池中已经有了"1"
    String s2 = "1";
    System.out.println(s == s2);// jdk6:false jdk7/8:false

    String s3 = new String("1") + new String("1");// s3变量记录的地址为 new String("11")
    s3.intern();// 在常量池中生成11，jdk6:创建一个新的对象11，新的地址
                // jdk7/8:此时常量池中并没有创建11，而是创建一个指向堆空间的new String("11")的地址
                // 6之后，字符串常量池放到了堆空间中，s3也是在堆空间创建了对象，为了节省空间，此处用一个地址
    String s4 = "11";// s4变量记录的地址：使用的是上一行代码执行时，在常量池中生成的11的地址

    System.out.println(s3 == s4);// jdk6:false jdk7/8:true

  }

}
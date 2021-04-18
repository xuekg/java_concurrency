package jvm;

/**
 * 成员变量（非静态的）赋值过程 1.默认初始化 2.显示初始化/代码块中初始化 3.构造器中初始化 4.有了对象后，对象.属性或对象.方法来
 * 对成员变量进行赋值
 */
class Father {
  int x = 10;

  public Father() {
    this.print();
    x = 20;
  }

  public void print() {
    System.out.println("Father.x=" + x);
  }
}

class Son extends Father {
  int x = 30;

  public Son() {
    this.print();
    x = 40;
  }

  public void print() {
    System.out.println("Son.x=" + x);
  }
}

public class SonTest {
  public static void main(String[] args) {
    // 子类初始化，先调用父类初始化方法，父类初始化方法中this.print()
    // 因为子类重写了父类的此方法，所以调用子类的方法，子类中x变量还未赋值，所以是0
    Father f = new Son();
    // 属性不存在多态性 f.x调用父类中的
    System.out.println(f.x);
  }
}
package jvm;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxyTest {

  interface IHello {
    void sayHello();
  }

  static class Hello implements IHello {
    @Override
    public void sayHello() {
      System.out.println("hello world");
    }
  }

  static class DynamicProxy implements InvocationHandler {

    Object originalObj;

    Object bind(Object originaObject) {
      this.originalObj = originaObject;
      return Proxy.newProxyInstance(originaObject.getClass().getClassLoader(), originaObject.getClass().getInterfaces(),
          this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
      System.out.println("welcome");
      return method.invoke(originalObj, args);
    }
  }

  public static void main(String[] args) {
    // 运行时产生的代理类
    System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");

    IHello hello = (IHello) new DynamicProxy().bind(new Hello());
    hello.sayHello();
  }

}
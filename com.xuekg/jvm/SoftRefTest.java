package jvm;

import java.lang.ref.SoftReference;

/**
 * 设置堆内存为10m
 */
public class SoftRefTest {

  public static class User {
    private int id;
    private String name;

    public User(int id, String name) {
      this.id = id;
      this.name = name;
    }

    @Override
    public String toString() {
      return "User [id=" + id + ", name=" + name + "]";
    }

    public static void main(String[] args) {
      // 创建对象，建立软引用
      SoftReference<User> uSoftReference = new SoftReference<SoftRefTest.User>(new User(1, "a"));
      // 上面一行代码等价于下面三行
      // User user = new User(1, "a");
      // SoftReference<User> uSoftReference = new
      // SoftReference<SoftRefTest.User>(user);
      // user = null;
      System.out.println(uSoftReference.get());

      System.gc();
      System.out.println("after gc");

      System.out.println(uSoftReference.get());

      try {
        // 让系统认为资源紧张
        byte[] b = new byte[1024 * 1024 * 7];
      } catch (Throwable e) {
        e.printStackTrace();
      } finally {
        // 再次获取软引用中的对象 内存不够时，就会回收
        System.out.println(uSoftReference.get());
      }
    }

  }

}
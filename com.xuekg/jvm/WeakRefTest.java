package jvm;

import java.lang.ref.WeakReference;

public class WeakRefTest {
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
      WeakReference<User> weakReference = new WeakReference<WeakRefTest.User>(new User(1, "a"));
      System.out.println(weakReference.get());

      System.gc();
      System.out.println("after gc");

      System.out.println(weakReference.get());

    }

  }

}
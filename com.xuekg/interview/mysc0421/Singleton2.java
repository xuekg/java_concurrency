package interview.mysc0421;

import java.lang.reflect.Constructor;

public class Singleton2 {
  private static Singleton2 instance = new Singleton2();

  private Singleton2() {

  }

  public static Singleton2 getInstance() {
    return instance;
  }

  public static void main(String[] args) throws Exception {
    Singleton2 singleton2 = Singleton2.getInstance();

    Constructor<Singleton2> constructor = Singleton2.class.getDeclaredConstructor();
    constructor.setAccessible(true);

    Singleton2 reflectSingleton = constructor.newInstance();

    System.out.println(singleton2 == reflectSingleton);
  }
}
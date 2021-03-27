package jvm;

import java.lang.reflect.Field;
import sun.misc.Unsafe;

public class MaxDirectMemorySizeTest {

  private static Field unsafeField;

  private static final long _1MB = 1024 * 1024;

  public static void main(String[] args) throws IllegalAccessException {
    unsafeField = Unsafe.class.getDeclaredFields()[0];
    unsafeField.setAccessible(true);
    Unsafe unsafe = (Unsafe) unsafeField.get(null);
    while (true) {
      unsafe.allocateMemory(_1MB);
    }
  }
}
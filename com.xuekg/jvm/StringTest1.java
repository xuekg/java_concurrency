package jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * StringTable 为什么要移动 从永久代到堆中 jdk6: -XX:PermSize = 6m -XX:MaxPermSize = 6m
 * -Xms6m -Xmx6m
 * 
 * jdk8: -XX:MetaspaceSize = 6m -XX:MaxMetaspaceSize = 6m -Xms6m -Xmx6m
 */
public class StringTest1 {
  public static void main(String[] args) {
    // 使用set保持常量池的引用，避免full gc回收常量池
    Set<String> set = new HashSet<>();
    // 在short范围内足以让6MB的PermSize或heap产生OOM了
    short i = 0;
    while (true) {
      set.add(String.valueOf(i++).intern());
    }
  }
}
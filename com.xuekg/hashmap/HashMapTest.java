package hashmap;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class HashMapTest {

  public static void main(String[] args) {
    HashMap<String, String> hashMap = new HashMap<>();

    hashMap.put("key", "value");

    ConcurrentHashMap<String,String> concurrentHashMap = new ConcurrentHashMap<String,String>(29);
    concurrentHashMap.put("dfsa","fds");

    String s = "拣货任务：930006303328-0001，所属出库单：300002643811，复核中，不可拣选";
    String s1 = "拣货任务：930006305135-0001，所属出库单：300002723594，复核完成，不可拣选";
    System.out.println(s.length());
    System.out.println(s1.length());
  }

}
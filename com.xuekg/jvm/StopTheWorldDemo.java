package jvm;

import java.util.ArrayList;
import java.util.List;

public class StopTheWorldDemo {

  public static class WorkThread extends Thread {

    List<byte[]> list = new ArrayList<>();

    @Override
    public void run() {
      try {
        while (true) {
          for (int i = 0; i < 1000; i++) {
            byte[] buffer = new byte[1024];
            list.add(buffer);
          }

          if (list.size() > 10000) {
            list.clear();
            System.gc();// 出发stw
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static class PrintThread extends Thread {

    private final long startTime = System.currentTimeMillis();

    @Override
    public void run() {
      try {
        while (true) {
          long t = System.currentTimeMillis() - startTime;
          System.out.println(t / 10000 + "." + t % 1000);
          Thread.sleep(1000);
        }
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    WorkThread workThread = new WorkThread();
    PrintThread printThread = new PrintThread();
    workThread.start();
    printThread.start();
  }
}
package jvm;

/**
 * vm配置添加 PrintGCDetail
 */
public class LocalVarGC {

  public void localGc1() {
    byte[] buffer = new byte[1024 * 1024 * 10];
    System.gc();
  }

  public void localGc2() {
    byte[] buffer = new byte[1024 * 1024 * 10];
    buffer = null;
    System.gc();
  }

  // todo 3 4是需要关注的
  public void localGc3() {
    {
      byte[] buffer = new byte[1024 * 1024 * 10];
    }
    System.gc();
  }

  public void localGc4() {
    {
      byte[] buffer = new byte[1024 * 1024 * 10];
    }
    int value = 10;
    System.gc();
  }

  public void localGc5() {
    localGc1();
    System.gc();
  }

  public static void main(String[] args) {
    LocalVarGC localVarGC = new LocalVarGC();
    localVarGC.localGc1();
  }

}
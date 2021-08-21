package jvm;

/**
 * @author xuekg
 * @description
 * @date 2021/8/6 9:49
 **/
public class Demo1 {

    public static void main(String[] args) throws InterruptedException {
        Thread.sleep(30 * 1000);
        while (true) {
            loadData();
        }
    }

    private static void loadData() throws InterruptedException {
        byte[] data = null;
        for (int i = 0; i < 50; i++) {
            data = new byte[100 * 1024];
        }
        data = null;

        Thread.sleep(1000);
    }
}

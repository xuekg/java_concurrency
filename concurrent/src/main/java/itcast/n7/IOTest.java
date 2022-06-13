package itcast.n7;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * @author xuekg
 * @description I/O 密集型任务：这种任务应用起来，系统会用大部分的时间来处理 I/O 交互，
 * 而线程在处理 I/O 的时间段内不会占用 CPU 来处理，这时就可以将 CPU 交出给其它线程使用。
 * 因此在 I/O 密集型任务的应用中，我们可以多配置一些线程，具体的计算方法是 2N
 * @date 2022/6/10 8:52
 **/
public class IOTest implements Runnable {

    //整体执行时间，包括在队列中等待的时间
    Vector<Long> wholeTimeList;
    //真正执行时间
    Vector<Long> runTimeList;

    private long initStartTime = 0;

    /**
     * 构造函数
     *
     * @param runTimeList
     * @param wholeTimeList
     */
    public IOTest(Vector<Long> runTimeList, Vector<Long> wholeTimeList) {
        initStartTime = System.currentTimeMillis();
        this.runTimeList = runTimeList;
        this.wholeTimeList = wholeTimeList;
    }

    /**
     * IO操作
     *
     * @return
     * @throws IOException
     */
    public void readAndWrite() throws IOException {
        File sourceFile = new File("D:/test.txt");
        //创建输入流
        BufferedReader input = new BufferedReader(new FileReader(sourceFile));
        //读取源文件,写入到新的文件
        String line = null;
        while ((line = input.readLine()) != null) {
            //System.out.println(line);
        }
        //关闭输入输出流
        input.close();
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        try {
            readAndWrite();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long end = System.currentTimeMillis();


        long wholeTime = end - initStartTime;
        long runTime = end - start;
        wholeTimeList.add(wholeTime);
        runTimeList.add(runTime);
        System.out.println("单个线程花费时间：" + (end - start));
    }
}

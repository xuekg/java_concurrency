package concurrency_8;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

public class MyTest2 {
    public static void main(final String[] args) {
        final ExecutorService executorService = new ThreadPoolExecutor(3, 5, 0, TimeUnit.SECONDS,
                new LinkedBlockingQueue(3), new ThreadPoolExecutor.CallerRunsPolicy());

        IntStream.range(0, 9).forEach(i -> {
            executorService.submit(() -> {

                try {
                    Thread.sleep(100);
                } catch (final InterruptedException e) {
                    e.printStackTrace();
                }

                IntStream.range(0, 1).forEach(j -> {
                    System.out.println(Thread.currentThread().getName());
                });
            });
        });

        executorService.shutdown();
    }
}
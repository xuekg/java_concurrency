package futuretask;

import java.util.concurrent.*;

/**
 * @author xuekg
 * @description
 * @date 2021/8/21 12:41
 **/
public class CompletableFutureDemo {

    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());

        CompletableFuture.supplyAsync(() -> {
            //暂停几秒钟线程
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 1;
        }, threadPoolExecutor).thenApply(f -> {
            return f + 2;
        }).whenComplete((v, e) -> {
            if (e == null) {
                System.out.println("----relust: " + v);
            }
        }).exceptionally(e -> {
            e.printStackTrace();
            return null;
        });


        System.out.println("----------main over");

        //主线程不要立刻结束，否则CompletableFuture默认使用的线程池会立刻关闭:暂停3秒钟线程
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        threadPoolExecutor.shutdown();
    }

    public static void m1() throws InterruptedException, ExecutionException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(1, 20, 1L, TimeUnit.SECONDS, new LinkedBlockingQueue<>(50), Executors.defaultThreadFactory(), new ThreadPoolExecutor.AbortPolicy());


        CompletableFuture<Void> future1 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
        });
        System.out.println(future1.get());

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
        }, threadPoolExecutor);
        System.out.println(future2.get());

        CompletableFuture<Integer> future3 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            return 11;
        });
        System.out.println(future3.get());

        CompletableFuture<Integer> future4 = CompletableFuture.supplyAsync(() -> {
            System.out.println(Thread.currentThread().getName() + "\t" + "-----come in");
            return 12;
        }, threadPoolExecutor);
        System.out.println(future4.get());

        threadPoolExecutor.shutdown();
    }
}

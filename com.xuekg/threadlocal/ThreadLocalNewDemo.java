package threadlocal;

/**
 * @author xuekg
 * @description
 * @date 2021/9/20 13:29
 **/
public class ThreadLocalNewDemo {
    public static void main(String[] args) {
        /*MovieTicket movieTicket = new MovieTicket();


        for (int i = 1; i <=3; i++) {
            new Thread(() -> {
                for (int j = 1; j <=20; j++) {
                    movieTicket.saleTicket();
                }
            },String.valueOf(i)).start();
        }*/

        House house = new House();

        new Thread(() -> {
            try {
                for (int j = 1; j <= 3; j++) {
                    house.saleHouse();
                    house.saleHouse2();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "---卖出： " + house.threadLocal.get());
                System.out.println(Thread.currentThread().getName() + "\t" + "---卖出： " + house.threadLocal2.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                house.threadLocal.remove();
                house.threadLocal2.remove();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                for (int j = 1; j <= 5; j++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "---卖出： " + house.threadLocal.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                house.threadLocal.remove();
            }
        }, "t2").start();

        new Thread(() -> {
            try {
                for (int j = 1; j <= 8; j++) {
                    house.saleHouse();
                }
                System.out.println(Thread.currentThread().getName() + "\t" + "---卖出： " + house.threadLocal.get());
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                house.threadLocal.remove();
            }
        }, "t3").start();


        System.out.println(Thread.currentThread().getName() + "\t" + "---卖出： " + house.threadLocal.get());


        House bighouse = new House();

        new Thread(() -> {
            bighouse.saleHouse();
        }, "t1").start();

        new Thread(() -> {
            bighouse.saleHouse();
        }, "t2").start();

    }
}

class MovieTicket {
    int number = 50;

    public synchronized void saleTicket() {
        if (number > 0) {
            System.out.println(Thread.currentThread().getName() + "\t" + "---卖出第： " + (number--));
        } else {
            System.out.println("----卖光了");
        }
    }
}

class House {
    private String houseName;

    ThreadLocal<Integer> threadLocal = ThreadLocal.withInitial(() -> 0);

    public void saleHouse() {
        Integer value = threadLocal.get();
        ++value;
        threadLocal.set(value);
    }

    ThreadLocal<Integer> threadLocal2 = ThreadLocal.withInitial(() -> 100);

    public void saleHouse2() {
        Integer value = threadLocal2.get();
        ++value;
        threadLocal2.set(value);
    }
}
package concurrency_1;

public class MyObject {

    private int counter;

    public synchronized void increase() {
        while (counter != 0) {
            try {
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        counter++;
        System.out.println(counter);
        notify();
    }

    public synchronized void decrease() {
        while (counter == 0) {
            try {
                //从wait()中醒来后，不能确定当前counter还是零，多线程下会出bug
                // 把if (counter == 0) 改为 while (counter == 0) 就可以了 
                wait();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        counter--;
        System.out.println(counter);
        notify();
    }

}
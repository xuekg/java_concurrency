package concurrency_6;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockDemo {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t1").start();

        new Thread(() -> {
            try {
                phone.sendSms();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, "t2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println();
        Thread t1 = new Thread(phone, "t3");
        Thread t2 = new Thread(phone, "t4");
        t1.start();
        t2.start();
    }
}

class Phone implements Runnable {
    public synchronized void sendSms() throws Exception {
        System.out.println(Thread.currentThread().getName() + " \t invoked sendSms");
        sendEmail();
    }

    public synchronized void sendEmail() throws Exception {
        System.out.println(Thread.currentThread().getName() + " \t invoked sendemail");
    }

    Lock Lock = new ReentrantLock();

    @Override
    public void run() {
        get();
    }

    public void get() {
        Lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " \t invoked get");
            set();
        } finally {
            Lock.unlock();
        }
    }

    public void set() {
        Lock.lock();
        try {
            System.out.println(Thread.currentThread().getName() + " \t invoked set");
        } finally {
            Lock.unlock();
        }
    }
}
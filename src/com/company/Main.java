package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName() + " has started");

        MyThreadTest t = new MyThreadTest("My test thread");
        Thread tRunnable = new Thread(new MyThreadTestRunnable(), "My test thread from runnable");

        t.start();
        tRunnable.start();

        try {
            Thread.sleep(5000);
            t.interrupt();

            t.join();
            tRunnable.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " has st");
    }

    public static class MyThreadTestRunnable implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " has started");
            try {
                Thread.sleep(500);
            } catch (Throwable e) {
                System.out.println(e.toString() + " has occurred");
            }
            System.out.println(Thread.currentThread().getName() + " has stopped");
        }
    }

    public static class MyThreadTest extends Thread {
        MyThreadTest(String threadName) {
            super(threadName);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " has started");

            while (!isInterrupted()) {
                try {
                    Thread.sleep(50);
                    System.out.println(Thread.currentThread().getName() + " is working");
                } catch (Throwable e) {
                    System.out.println(e.toString() + " has occurred");
                    interrupt();
                }
            }

            System.out.println(Thread.currentThread().getName() + " has stopped");
        }

    }

}

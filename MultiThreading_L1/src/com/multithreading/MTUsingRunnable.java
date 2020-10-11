package com.multithreading;

public class MTUsingRunnable {

    public static void main(String[] args) {

        MyThread obj = new MyThread();
        Thread thread = new Thread(obj);
        thread.start();
    }

    private static class MyThread implements Runnable {

        @Override
        public void run() {
            System.out.println(Thread.currentThread());
        }
    }
}

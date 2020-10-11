package com.multithreading;

public class MTUsingThread {

    public static void main(String[] args) throws InterruptedException {

        System.out.println(Thread.currentThread());

        MTUsingThread o = new MTUsingThread();
        MyThread thread = o.new MyThread();
        thread.start();

//        Thread.sleep(3000);

        System.out.println("Returning from thread execution");

    }

    public class MyThread extends Thread{

        @Override
        public void run(){
            System.out.println(Thread.currentThread());
            try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

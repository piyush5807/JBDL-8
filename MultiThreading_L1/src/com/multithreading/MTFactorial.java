package com.multithreading;

import java.math.BigInteger;

public class MTFactorial {

    public static void main(String[] args) throws InterruptedException {

        int[]arr = {10000, 22000, 23000, 40000, 30000, 20000, 45000, 30000, 50000,20000};
        MyThread[]threads = new MyThread[arr.length];

        long start = System.currentTimeMillis();

        for(int i=0;i<arr.length;i++){
            threads[i] = new MyThread(arr[i]);
            threads[i].start();
//            System.out.println(arr[i] + " - " + threads[i].result);
        }

        for(int i=0;i<arr.length;i++){
//            threads[i].join();
            System.out.println(arr[i] + " - " + threads[i].result);
        }

        Thread.sleep(8000);

        System.out.println("time = " + (System.currentTimeMillis() - start));
    }

    private static class MyThread extends Thread{

        private int num;
        private BigInteger result;

        public MyThread(int num) {
            this.result = BigInteger.valueOf(1);
            this.num = num;
        }

        @Override
        public void run() {
            for(int i=1;i<=this.num;i++){
                this.result = this.result.multiply(BigInteger.valueOf(i));
            }
        }
    }
}

package com.multithreading;

import java.math.BigInteger;

public class Factorial {

    public static void main(String[] args) {

        int[]arr = {10000, 22000, 23000, 40000, 30000, 20000, 45000, 30000, 50000,20000};

        long start = System.currentTimeMillis();
        for(Integer num : arr) {
            System.out.println(num + " - " + calculateFactorial(num));
        }

        System.out.println("time - " + (System.currentTimeMillis() - start));

    }

    public static BigInteger calculateFactorial(int num){

        BigInteger result = BigInteger.valueOf(1);

        for(int i=1;i<=num;i++){
            result = result.multiply(BigInteger.valueOf(i));
        }

        return result;
    }

}

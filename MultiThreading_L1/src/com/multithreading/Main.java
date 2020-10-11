package com.multithreading;

public class Main {

    public static void main(String[] args) {

        int[]arr = {1, 2, 3, 4, 10, 23, 20, 50};

        System.out.println(findSum(arr));
    }

    public static int findSum(int[] arr){

        int sum = 0;
        for(int i=0;i<arr.length;i++){
            sum += arr[i];
        }

        return sum;
    }
}

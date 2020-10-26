package com.multithreading;

public class BankOperations {

    public static void main(String[] args) throws InterruptedException {

        BankObj obj1 = new BankObj(1, 400);
        BankObj obj2 = new BankObj(2, 500);


        // for obj1, perform 3 operations 2 deposit and 1 withdraw
        // for obj2, perform 3 operations, 2 withdraw and 1 deposit

        // obj1 - final 650
        // obj2 - final 200

        MyThread[] threads = new MyThread[6];

        threads[0] = new MyThread(obj1, true, 200);
        threads[1]  = new MyThread(obj1, false, 100);
        threads[2]  = new MyThread(obj1, true, 150);

        threads[3] = new MyThread(obj2, false, 200);
        threads[4] = new MyThread(obj2, true, 50);
        threads[5] = new MyThread(obj2, false, 150);

        for(int i=0;i<6;i++){
            threads[i].start();
        }

        for(int i=0;i<6;i++){
            threads[i].join();
        }

        System.out.println(obj1);
        System.out.println(obj2);

    }


    private static class MyThread extends Thread{

        private BankObj bankObj;
        private boolean isDeposit;
        private int amount;

        public MyThread(BankObj obj, boolean isDeposit, int amount){

            this.bankObj = obj;
            this.isDeposit = isDeposit;
            this.amount = amount;
        }


        @Override
        public void run() {

            if(this.isDeposit){
                deposit();
            }else{
                withdraw();
            }
        }

        public void deposit() {

            synchronized (this.bankObj) {
                System.out.println("In deposit function, Thread - " + currentThread().getName() + ", bankObj = " + this.bankObj);

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }


                int prev_amount = this.bankObj.getAccountBal();
                int final_amount = prev_amount + this.amount;

                this.bankObj.setAccountBal(final_amount);

                System.out.println("In deposit function end, Thread - " + currentThread().getName() + ", bankObj = " + this.bankObj);
            }
        }

        public void withdraw() {

            synchronized (this.bankObj) {
                System.out.println("In withdraw function, Thread - " + currentThread().getName() + ", bankObj = " + this.bankObj);

                try {
                    Thread.sleep(20000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                int prev_amount = this.bankObj.getAccountBal();
                int final_amount = prev_amount - this.amount;

                this.bankObj.setAccountBal(final_amount);

                System.out.println("In withdraw function end, Thread - " + currentThread().getName() + ", bankObj = " + this.bankObj);

            }
        }
    }


}

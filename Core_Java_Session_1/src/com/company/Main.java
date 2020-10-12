package com.company;

import com.company.Classroom.*;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws MyCustomException {
	// write your code here

       // Student student1 = new Student(123,"student", 170);
       // Teacher techer = new Teacher();

        //System.out.println(student1.height);

       // walk(student1);

//        System.out.println(student1.compute(1));
//        System.out.println(student1.compute(new Float("2")));





        //Student student2 = new Student();

        //System.out.println(student2.hashCode());

//        Human human = new Student();
//
//        human.walk();

//        Study study = new Teacher();
//
//        study.read();
//        study.write();

        //Payment
        // UPI or Paypal or Credit cart

        // Interface Payment getPayment()

        // Class NetBanking getPayment() {} getOtp()
        //Class Paypal getPayment() {}


        Calculator calculator =  new Calculator();
//        try {
//            int result = calculator.divide(1, 0);
//            System.out.print(result);
//        }catch (ArithmeticException ex){
//            System.out.println("Divided by zero");
//
//        }finally {
//
//        }
        try {
            calculator.divide(1,0);
        } catch (IOException e) {
            System.out.print("IO Exception");
        }catch (ArithmeticException ex){
            System.out.print("Arithmetic Exception");

        }


        //calculator.divide(1,0);
        System.out.println("The End");




    }

    static void walk(Human human){
        human.walk();
    }


}

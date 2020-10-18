package com.company.Classroom;

public class Teacher extends Human implements Study {
    @Override
    public void read() {
        System.out.println("teacher is reading");
    }

    @Override
    public void write() {
        System.out.println("teacher is writing");

    }
    @Override
    public void walk(){
        System.out.print("Teacher is walking");
    }
}

package com.company.Classroom;

public interface Study {
    void read();
    void write();

    default void assignment(){
        System.out.print("assignment");
    }

    static void activity(){

    }
}

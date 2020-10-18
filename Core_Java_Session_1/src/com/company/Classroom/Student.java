package com.company.Classroom;


public class Student extends Human  implements Study, ExtraCurricular {
    //attributes

    //public protected private

    private Integer rollNumber;
    private String name;

//    public Student(Integer rollNumber,
//                   String name){
//        super();
//        this.rollNumber = rollNumber;
//        this.name = name;
//    }

    public Student(Integer rollNumber,
                   String name, int height, int weight){
        super(height,weight);
        this.rollNumber = rollNumber;
        this.name = name;


    }

//    public Student(){
//
//    }

    //methods or functions
    public Integer getRollNumber(){
        return this.rollNumber;
    }

    public String getName(){
        return this.name;
    }

    public void draw(){
        System.out.print("drawing");
    }


//    @Override
//    public void run() {
//        System.out.println("running");
//    }

    @Override
    public void read() {
        System.out.println("reading");
    }

    @Override
    public void write() {
        System.out.print("writing");

    }

    @Override
    public void assignment(){

    }

    public Integer compute (Integer i){

        return i*2;

    }

    public Float compute (Float i){

        return i*2;

    }

    public void walk(){
        super.walk();
    }
}
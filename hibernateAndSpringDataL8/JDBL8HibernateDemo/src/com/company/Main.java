package com.company;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        // write your code here
        //Movie movie =  new Movie("avneger","action",5,new Date(),180,"Eng");

        CRUDRepository crudRepository = new CRUDRepositoryImpl();

        Movie movie = (Movie) crudRepository.read(Movie.class,1l);
        movie.setGenre("comedy");
        try {
            crudRepository.update(1l, movie,Movie.class);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

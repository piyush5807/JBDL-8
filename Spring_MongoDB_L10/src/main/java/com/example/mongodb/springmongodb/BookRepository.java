package com.example.mongodb.springmongodb;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
import java.util.stream.Stream;

public interface BookRepository extends MongoRepository<Book, String> {

    @Query("{ cost : {$gt : ?0 }}")
    Stream<Book> getBookByPrice(int price);

}

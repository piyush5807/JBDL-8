package com.example.mongodb.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookOperations {


    @Autowired
    MongoOperations mongoOperations;

//    @Autowired
//    MongoTemplate mongoTemplate;

    public List<Book> findBooks(String author, int cost){


        Query query = new Query();

        Criteria criteria = new Criteria();
        criteria.orOperator(Criteria.where("cost").gt(cost), Criteria.where("author").is(author));
//        query.addCriteria(Criteria.where("author").is(author));
//        query.addCriteria(Criteria.where("cost").gt(cost).orOperator(Criteria.where("author").is(author)));
        query.addCriteria(criteria);
        return mongoOperations.find(query, Book.class);
    }

}

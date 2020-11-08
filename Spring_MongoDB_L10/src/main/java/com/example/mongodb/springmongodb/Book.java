package com.example.mongodb.springmongodb;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Book {


    private String name;
    private String author;
    private Integer cost;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getCost() {
        return cost;
    }

    public void setCost(Integer cost) {
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Book{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", cost=" + cost +
                '}';
    }
}

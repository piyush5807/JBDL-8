package com.example.mongodb.springmongodb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringMongodbApplication implements CommandLineRunner{

	@Autowired
	BookRepository bookRepository;

	@Autowired
	BookOperations bookOperations;

	public static void main(String[] args) {
		SpringApplication.run(SpringMongodbApplication.class, args);
	}
	@Override
	public void run(String... args) throws Exception {

//		Book book = new Book();
//		book.setAuthor("Max");
//		book.setName("Intro to Mongo");
//		book.setCost(100);

//		Stream<Book> books = bookRepository.getBookByPrice(50);
//
//		books.forEach(book -> System.out.println(book));
//
//		List<Book> my_books = bookRepository.findAll();
//
//		for(Book book : my_books){
//			if(book.getName() == "John"){
//				System.out.println(book);
//			}
//		}
//
//		Stream<Book> filtered_books = bookRepository.getBookByPrice(40);
//		filtered_books.filter(book -> book.getAuthor().equals("John"))
//				.forEach(book -> System.out.println(book));
//

		System.out.println(bookOperations.findBooks("Max", 100));

	}
}

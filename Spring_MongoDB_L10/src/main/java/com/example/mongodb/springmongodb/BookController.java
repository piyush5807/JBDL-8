package com.example.mongodb.springmongodb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {

    @GetMapping("/test")
    public String sayHello(){
        return "Hi";
    }
}

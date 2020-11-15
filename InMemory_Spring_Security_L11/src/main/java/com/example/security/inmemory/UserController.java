package com.example.security.inmemory;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @GetMapping("/user")
    public String greetUser(){
        return "Hello User";
    }

    @GetMapping("/admin")
    public String greetAdmin(){
        return "Hello Admin";
    }

    @GetMapping("/")
    public String greetPublic(){
        return "Hello People!";
    }

}

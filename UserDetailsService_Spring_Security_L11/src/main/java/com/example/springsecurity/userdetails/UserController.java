package com.example.springsecurity.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLIntegrityConstraintViolationException;

@RestController
public class UserController {

//    @Autowired
//    PasswordEncoder encoder;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    MyRepository repository;

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

    @GetMapping("/insertBook")
    public void insertBook(@RequestParam("name") String name){

        Book book = new Book();
        book.setName(name);

        bookRepository.save(book);
    }

    @GetMapping("/signup")
    public void signup(@RequestParam("name") String name, @RequestParam("pass")String pass, @RequestParam("authorities") String authorities) throws Exception{

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String enc_pass = encoder.encode(pass);

        MyUser user = new MyUser();
        user.setUsername(name);
        user.setPassword(enc_pass);
        user.setAuthorities(authorities);

        try {
            repository.save(user);
        } catch (Exception e){
            e.printStackTrace();
            if(e.equals(SQLIntegrityConstraintViolationException.class)){
                throw new Exception("Unique key needed");
            }else{
                throw new Exception("Some error occured while signing up, please try again");
            }
        }
    }

}
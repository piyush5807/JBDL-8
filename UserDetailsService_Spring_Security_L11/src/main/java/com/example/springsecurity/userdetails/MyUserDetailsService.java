package com.example.springsecurity.userdetails;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class MyUserDetailsService implements UserDetailsService {

//    public HashMap<String, UserDetails> hashMap;

    @Autowired
    MyRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        // define how to get the user info
//
//        return hashMap.get(s);

        return repository.getUser(s).get(0);
    }
}

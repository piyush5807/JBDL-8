package com.example.springsecurity.userdetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public interface MyRepository extends JpaRepository<MyUser, Integer> {

    @Query(value = "select * from my_user where my_user.username=:username", nativeQuery = true)
    public List<MyUser> getUser(String username);
}

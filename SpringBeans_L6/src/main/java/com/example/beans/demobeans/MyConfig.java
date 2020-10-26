package com.example.beans.demobeans;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Configuration
public class MyConfig {

    public MyUtil getUtilObject(){
        System.out.println("In MyConfig: getUtil function");
        return new MyUtil();
    }
}

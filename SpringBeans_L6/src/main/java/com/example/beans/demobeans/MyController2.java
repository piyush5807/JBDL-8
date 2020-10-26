package com.example.beans.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController2 {


    @Autowired
//    MyUtil util;
    MyConfig config;


    @ResponseStatus(HttpStatus.ACCEPTED)
    @RequestMapping(value = "/add_test", method = RequestMethod.GET)
    public int addNumbers(@RequestParam(value = "n1") int n1, @RequestParam(value = "n2") int n2){

        MyUtil util = config.getUtilObject();
        System.out.println(util);
        int sum = util.add(n1, n2);
        util.setNum(sum);
        return sum;
    }

}

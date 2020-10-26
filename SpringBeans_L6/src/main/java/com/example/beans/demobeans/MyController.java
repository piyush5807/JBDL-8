package com.example.beans.demobeans;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MyController {

    @Autowired
    MyConfig config;

    @GetMapping("/test")
    public String getHelloMsg(@RequestParam(value = "name") String name){
        System.out.println(this);
        return "Hi " + name;
    }

    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public int addNumbers(@RequestParam(value = "n1") int n1, @RequestParam(value = "n2") int n2){

        MyUtil util = config.getUtilObject();
        System.out.println(util);
        int sum = util.add(n1, n2);
        util.setNum(sum);
        return sum;
    }


    @RequestMapping(value = "/add2/{n1}/{n2}", method = RequestMethod.GET)
    public int addNumbers2(@PathVariable("n1") int n1, @PathVariable("n2") int n2){

        MyUtil util = config.getUtilObject();
        System.out.println(util);
        util.setNum(30);
        return util.add(n1, n2);
    }
}

package com.example.beans.demobeans;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class MyUtil {

    private int num;

    public int add(int a, int b){
        return a + b;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }
}

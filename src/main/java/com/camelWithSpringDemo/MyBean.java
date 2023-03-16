package com.camelWithSpringDemo;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component("myBean")
public class MyBean {

    private int counter;

    @Value("${greeting}")
    private String say;

    public String saySomething(String body) {
        System.out.println("Exchange body being processed by bean.....");
        return body.contains("http")? body:String.format("%s Http webpage not invoked %d times", say, ++counter);
    }

}

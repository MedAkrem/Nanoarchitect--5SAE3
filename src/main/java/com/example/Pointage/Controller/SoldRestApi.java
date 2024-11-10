package com.example.Pointage.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SoldRestApi {
    private String title ="hello , i'm the candidate MicroSerivce";
    @RequestMapping("/hello")
    public String sayHello()
    {System.out.println(title);
        return  title;



    }
}

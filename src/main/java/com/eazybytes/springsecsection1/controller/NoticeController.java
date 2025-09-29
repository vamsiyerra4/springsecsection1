package com.eazybytes.springsecsection1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {

    @GetMapping("/notices")
    public String sayWelcome(){
        return "Here are the notices deatils from DB";
    }

}

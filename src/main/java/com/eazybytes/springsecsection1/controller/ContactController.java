package com.eazybytes.springsecsection1.controller;

import com.eazybytes.springsecsection1.model.Contact;
import com.eazybytes.springsecsection1.repository.ContactRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

@RestController
@RequiredArgsConstructor
public class ContactController {

    private final ContactRepository contactRepository;

    @PostMapping("/contact")
    public Contact saveContactInquiryDetails(@RequestBody Contact contact){
        contact.setContactId(getServiceNumber());
        contact.setCreateDt(new Date(System.currentTimeMillis()));
        return contactRepository.save(contact);
    }


    public String getServiceNumber(){
        Random random = new Random();
        int ranNum = random.nextInt(99999999 - 9999) + 9999;
        return "SR" + ranNum;
    }
}

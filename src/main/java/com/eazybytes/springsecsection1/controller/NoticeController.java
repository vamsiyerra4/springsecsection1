package com.eazybytes.springsecsection1.controller;

import com.eazybytes.springsecsection1.model.Notices;
import com.eazybytes.springsecsection1.repository.NoticesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class NoticeController {

    private final NoticesRepository noticesRepository;

    @GetMapping("/notices")
    public ResponseEntity<List<Notices>> getNotices(){
        List<Notices> notices =  noticesRepository.findAllActiveNotices();

        if(notices != null){
            return ResponseEntity.ok().cacheControl(CacheControl.maxAge(60, TimeUnit.SECONDS)).body(notices);
        }else{
            return null;
        }
    }

}

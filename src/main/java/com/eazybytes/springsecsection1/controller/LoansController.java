package com.eazybytes.springsecsection1.controller;

import com.eazybytes.springsecsection1.model.Loans;
import com.eazybytes.springsecsection1.repository.LoansRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoansController {

    private final LoansRepository loansRepository;

    @GetMapping("/myLoans")
    public List<Loans> getLoanDetails(@RequestParam long id){
        List<Loans> loans = loansRepository.findByCustomerIdOrderByStartDtDesc(id);
        if(loans != null){
            return loans;
        }else {
            return null;
        }
    }
}

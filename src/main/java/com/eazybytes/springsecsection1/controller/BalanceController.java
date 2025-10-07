package com.eazybytes.springsecsection1.controller;

import com.eazybytes.springsecsection1.model.AccountTransactions;
import com.eazybytes.springsecsection1.repository.AccountsTrasactionsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class BalanceController {

    private final AccountsTrasactionsRepository accountsTrasactionsRepository;

    @GetMapping("/myBalance")
    public List<AccountTransactions> getBalanceDetails(@RequestParam long id){
        List<AccountTransactions> accountTransactions = accountsTrasactionsRepository.findByCustomerIdOrderByTransactionDtDesc(id);
        if(accountTransactions  != null){
            return accountTransactions;
        }else{
            return null;
        }

    }
}

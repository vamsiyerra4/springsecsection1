package com.eazybytes.springsecsection1.controller;


import com.eazybytes.springsecsection1.model.Accounts;
import com.eazybytes.springsecsection1.repository.AccountsRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {

    private final AccountsRepository accountsRepository;

    public AccountController(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    @GetMapping("/myAccount")
    public Accounts getAccountDetails(@RequestParam Long id){
        Accounts account = accountsRepository.findByCustomerId(id);
        if(account != null){
            return account;
        }else {
            return null;
        }
    }
}

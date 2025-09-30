package com.eazybytes.springsecsection1.controller;

import com.eazybytes.springsecsection1.model.Customer;
import com.eazybytes.springsecsection1.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {


    private final CustomerRepository customerRepository;

    private final PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer){
        try {
            String hshPwd = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hshPwd);
            Customer savedCustomer = customerRepository.save(customer);

            if(savedCustomer.getId() > 0){
                return ResponseEntity.status(HttpStatus.CREATED).body("Customer successfully registered");
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer registration failed");
            }
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An exception occurred " + ex.getMessage());
        }
    }
}

package com.eazybytes.springsecsection1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.sql.Date;

@Data
@Entity
public class Accounts {

    @Column(name = "customer_id")
    private long customerId;

    @Id
    @Column(name = "account_number")
    private long accountNumber;

    @Column(name = "account_type")
    private String accountType;

    @Column(name = "branch_address")
    private String branchAddress;

    @Column(name = "created_dt")
    private Date createdDate;

}

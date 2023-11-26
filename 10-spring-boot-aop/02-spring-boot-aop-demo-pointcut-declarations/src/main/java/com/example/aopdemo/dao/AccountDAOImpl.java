package com.example.aopdemo.dao;

import com.example.aopdemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository
public class AccountDAOImpl implements AccountDAO {

    private String name;
    private String serviceCode;

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public void doWork() {
        System.out.println("doWork()");
    }

    @Override
    public String getName() {
        System.out.println(getClass() +": in getName()");
        return name;
    }

    @Override
    public void setName(String name) {
        System.out.println(getClass() + ": in setName()");
        this.name = name;
    }

    @Override
    public String getServiceCode() {
        System.out.println(getClass() + ": in getServiceCode()");
        return serviceCode;
    }

    @Override
    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": in setServiceCode()");
        this.serviceCode = serviceCode;
    }

    @Override
    public List<Account> findAccounts() {
        List<Account> myAccounts = new ArrayList<>();
        myAccounts.add(new Account("John", "Silver"));
        myAccounts.add(new Account("Madhu", "Platinum"));
        myAccounts.add(new Account("Luca", "Gold"));
        return myAccounts;
    }
}

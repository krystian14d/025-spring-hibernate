package com.example.aopdemo.service;

import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class TrafficFortuneServiceImpl implements TrafficFortuneService{
    @Override
    public String getFortune() {

        //simulate a delay
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return "Expect heavy traffic this morning";
    }

    @Override
    public String getFortune(boolean shouldThrowException) {
        if(shouldThrowException){
            throw new RuntimeException("Major accident! Hoghway is closed!");
        }

        return getFortune();
    }
}

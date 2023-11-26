package com.example.aopdemo;

import com.example.aopdemo.dao.AccountDAO;
import com.example.aopdemo.dao.MembershipDAO;
import com.example.aopdemo.service.TrafficFortuneService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class AopdemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(AopdemoApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(AccountDAO theAccountDAO,
                                               MembershipDAO theMembershipDAO,
                                               TrafficFortuneService theTrafficFortuneService) {

        return runner -> {
//			demoTheBeforeAdvice(theAccountDAO, theMembershipDAO);
//			demoTheAfterReturningAdvice(theAccountDAO);
//          demoTheAfterThrowingAdvice(theAccountDAO);
//            demoTheAfterAdvice(theAccountDAO);
//            demoTheAroundAdvice(theTrafficFortuneService);
            demoTheAroundAdviceHandleException(theTrafficFortuneService);
        };
    }

    private void demoTheAroundAdviceHandleException(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundService");
        System.out.println("Calling getFortune()");

        boolean shouldThrowException = true;

        String data = theTrafficFortuneService.getFortune(shouldThrowException);

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAroundAdvice(TrafficFortuneService theTrafficFortuneService) {
        System.out.println("\nMain Program: demoTheAroundService");
        System.out.println("Calling getFortune()");

        String data = theTrafficFortuneService.getFortune();

        System.out.println("\nMy fortune is: " + data);
        System.out.println("Finished");
    }

    private void demoTheAfterAdvice(AccountDAO theAccountDAO) {
        List<Account> theAccounts = null;

        try {
            boolean shouldThrowException = true;
            theAccounts = theAccountDAO.findAccounts(shouldThrowException);
        } catch (Exception e) {
            System.out.println("\n\nMain Program: ... caught exception: " + e);
            System.out.println("----");
        }
        System.out.println(theAccounts);

        System.out.println("\n\nMain Program: demoTheAfterAdvice");
        System.out.println("----");
        System.out.println("\n");
    }

    private void demoTheAfterThrowingAdvice(AccountDAO theAccountDAO) {
        List<Account> theAccounts = null;

        try {
            boolean shouldThrowException = true;
            theAccounts = theAccountDAO.findAccounts(shouldThrowException);
        } catch (Exception e) {
            System.out.println("\n\nMain Program: ... caught exception: " + e);
            System.out.println("----");
        }
        System.out.println("\n\nMain Program: demoTheAfterThrowingAdvice");
        System.out.println("----");
        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheAfterReturningAdvice(AccountDAO theAccountDAO) {
        List<Account> theAccounts = theAccountDAO.findAccounts();
        System.out.println("\n\nMain Program: demoTheAfterReturningAdvice");
        System.out.println("----");

        System.out.println(theAccounts);

        System.out.println("\n");
    }

    private void demoTheBeforeAdvice(AccountDAO theAccountDAO, MembershipDAO theMembershipDAO) {
        //call the business method
        Account account = new Account("Madhu", "Platinum");
        theAccountDAO.addAccount(account, true);
        theAccountDAO.doWork();

        theAccountDAO.setName("foobar");
        theAccountDAO.setServiceCode("silver");

        String name = theAccountDAO.getName();
        String serviceCode = theAccountDAO.getServiceCode();

        theMembershipDAO.addSillyMember();
        theMembershipDAO.goToSleep();
    }
}

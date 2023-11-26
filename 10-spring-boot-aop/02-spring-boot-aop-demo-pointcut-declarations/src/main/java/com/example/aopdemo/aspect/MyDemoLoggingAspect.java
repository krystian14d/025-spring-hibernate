package com.example.aopdemo.aspect;

import com.example.aopdemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Order(2)
@Component
public class MyDemoLoggingAspect {

    private void convertAccountNameToUpperCase(List<Account> result) {
        for (Account account : result) {
            account.setName(account.getName().toUpperCase());
        }
    }

    @Before("com.example.aopdemo.aspect.LuvAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint) {
        System.out.println("\n=====>>> Executing @Before advice on addAccount()");

        //display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        //display method arguments
        Object[] args = theJoinPoint.getArgs();
        for (Object arg : args) {
            if (arg instanceof Account) {
                System.out.println(arg);
                Account account = (Account) arg;
                System.out.println("Account name: " + account.getName());
                System.out.println("Account level: " + account.getLevel());
            }
        }
    }

    @AfterReturning(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> Executing @AfterReturning on method: " + method);

        System.out.println("\n =====>>> result is: " + result);

        convertAccountNameToUpperCase(result);

        System.out.println("\n =====>>> result is: " + result);
    }

    @AfterThrowing(
            pointcut = "execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))",
            throwing = "exception"
    )
    public void afterThrowingFindAccountsAdvice(
            JoinPoint theJoinPoint, Throwable exception
    ) {
        //print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> Executing @AfterThrowing on method: " + method);
        //log the exception
        System.out.println("\n =====>>> The exception is: " + exception);
    }

    @After("execution(* com.example.aopdemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint) {
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> Executing @After (finally) on method: " + method);
    }

    @Around("execution(* com.example.aopdemo.service.*.getFortune(..))")
    public Object aroundGetFortune(
            ProceedingJoinPoint theProceedingJoinPoint) throws Throwable {
        //print out the metod we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n =====>>> Executing @Around on method: " + method);

        long begin = System.currentTimeMillis();

        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception e) {
            //log exception
            System.out.println(e.getMessage());
            //give user a custom message
//            result = "Major accident! But no worries, your private AOP heli is on the way to pick you up!";
            //or rethrow exception:
            throw e;
        }

        long end = System.currentTimeMillis();

        long duration = end - begin;
        System.out.println("\n=====>Duration: " + duration / 1000.0 + " seconds");
        return result;
    }
}
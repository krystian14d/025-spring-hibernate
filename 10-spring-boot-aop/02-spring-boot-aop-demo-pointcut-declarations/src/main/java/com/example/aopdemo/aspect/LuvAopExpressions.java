package com.example.aopdemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LuvAopExpressions {
    @Pointcut("execution( * com.example.aopdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    //pointcut for getter
    @Pointcut("execution( * com.example.aopdemo.dao.*.get*(..))")
    public void getter() {}

    //pointcut for setter
    @Pointcut("execution( * com.example.aopdemo.dao.*.set*(..))")
    public void setter() {}

    //create pointcut: include package, exclude getter and setter
    @Pointcut("forDaoPackage() && !(getter() || setter())")
    public void forDaoPackageNoGetterSetter(){}

}

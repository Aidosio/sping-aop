package com.example.spingaop.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {
    @Pointcut("execution(* com.example.spingaop.service.BookService.get*(..))")
    public void allGetMethods() {}


    @Pointcut("execution(* com.example.spingaop.service.BookService.add*(..))")
    public void allAddMethods() {}
}

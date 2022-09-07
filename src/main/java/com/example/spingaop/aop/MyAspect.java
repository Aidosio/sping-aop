package com.example.spingaop.aop;

import com.example.spingaop.entity.BookEntity;
import com.example.spingaop.util.CustomResponse;
import com.example.spingaop.util.CustomStatus;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Slf4j
public class MyAspect {

    @Around("Pointcuts.allAddMethods()")
    public Object aroundAddingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        BookEntity bookEntity = null;
        if (methodSignature.getName().equals("addBook")) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof BookEntity) {
                    bookEntity = (BookEntity) arg;
                    log.info("Попытка добавить книгу с названием {}", bookEntity.getTitle());
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        log.info("книга с названием {} добавлено", bookEntity.getTitle());
        return result;
    }

    @Around("Pointcuts.allGetMethods()")
    public Object aroundGettingAdvice(ProceedingJoinPoint joinPoint) {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String title = null;

        if (methodSignature.getName().equals("getAll")) {
            log.info("Попытка  получить все книги");
        }
        else if (methodSignature.getName().equals("getBookByTitle")) {
            Object[] args = joinPoint.getArgs();
            for (Object arg : args) {
                if (arg instanceof BookEntity) {
                    title = (String) arg;
                    log.info("Попытка получить книгу с названием {}", title);
                }
            }
        }

        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            result = new CustomResponse<>(null, CustomStatus.EXCEPTION);
        }

        if (methodSignature.getName().equals("getAll")) {
            log.info("Все книги получены");
        } else if (methodSignature.getName().equals("getBookByTitle")) {
            log.info("Книга с названием {}", title);
        }

        return result;
    }
}

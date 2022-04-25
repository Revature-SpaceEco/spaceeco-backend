package com.revature.spaceecobackend.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggerAspect {
  private static final Logger logger = LoggerFactory.getLogger(LoggerAspect.class);

  @Before(value = "execution(* com.revature.spaceecobackend.*.*.*(..))")
  public void beforeAdvice(JoinPoint joinPoint) {
    logger.info("Before method: {}", joinPoint.getSignature());
  }

  @After(value = "execution(* com.revature.spaceecobackend.*.*.*(..))")
  public void afterAdvice(JoinPoint joinPoint) {
    logger.info("After method: {}", joinPoint.getSignature());
  }

  @AfterThrowing(value = "execution(* com.revature.spaceecobackend.*.*.*(..))", throwing = "e")
  public void exceptionAdvice(JoinPoint joinPoint, Throwable e) {
    logger.warn("Exception: {}", e.getMessage());
  }
}
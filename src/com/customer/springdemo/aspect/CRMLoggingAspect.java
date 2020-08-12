package com.customer.springdemo.aspect;

import java.util.Objects;
import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CRMLoggingAspect {
	
	//Set Up Logger
	Logger myLogger = Logger.getLogger(getClass().getName());
	
	
	//Set Up Pointcut declarations
	@Pointcut("execution(* com.customer.springdemo.controller.*.*(..))")
	public void forControllerPackage() {
		
	}
	
	@Pointcut("execution(* com.customer.springdemo.service.*.*(..))")
	public void forServicePackage() {
		
	}
	
	@Pointcut("execution(* com.customer.springdemo.dao.*.*(..))")
	public void forDaoPackage() {
		
	}
	
	@Pointcut("forControllerPackage()||forServicePackage()||forDaoPackage()")
	public void forAppFlow() {
		
	}
	
	@Before("forAppFlow()")
	public void beforeMethods(JoinPoint theJoinPoint) {
		
		//display the methods we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===========> Before Method : "+theMethod);
		
		// Get the arguments
		Object[] arguments = theJoinPoint.getArgs();
		
			for(Object tempArgs: arguments) {
			myLogger.info("====>Arguments: "+tempArgs);
			}
	}
	
	@AfterReturning(pointcut = "forAppFlow()", returning = "theResults")
	public void afterMethods(JoinPoint theJoinPoint, Object theResults) {
		
		//display the return method we are calling
		String theMethod = theJoinPoint.getSignature().toShortString();
		myLogger.info("===========> Before Method : "+theMethod);
		
		// Display the return values
			myLogger.info("====>Return Values: "+theResults);
	}
	

}

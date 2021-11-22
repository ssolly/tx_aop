package com.care.root;

import java.lang.reflect.Method;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Component
@Aspect		//aop설정
@EnableAspectJAutoProxy
public class AOPConfig {
	
	//("execution(* com.care.root.controller.TestController.buy_form(..))
	@Around("execution(public String buy_form(..))")	// .. : 매개변수의 개수(0개 이상)
	   public void commonAop(ProceedingJoinPoint joinpoint) {	//around에서 반드시 필요한 매개변수
	      try {
	         System.out.println("--- 컨트롤러 공통기능 시작 ---");	//로직처리
	         joinpoint.proceed();								//연결시킨 메소드가 진행
	         System.out.println("--- 컨트롤러 공통기능 종료 ---");
	      } catch (Throwable e) {
	         e.printStackTrace();
	      }
	   }

	@Before("execution(* com.care.root.service.TestServiceImpl.buy(..))")	//buy 메소드 이후 실행해주세요
	public void commonAop02() {
		System.out.println("--- service 공통 기능(buy) 시작 ---");
	}
	
	@After("execution(* com.care.root.service.TestServiceImpl.dbResult(..))")
	public void commonAop03() {
		System.out.println("--- service 공통 기능(db_result) 종료 ---");
	}
	
	Logger LOG = Logger.getGlobal();
	   @Around("execution(* com.care.root.controller.*.*(..))")
	   public Object commonAop00(ProceedingJoinPoint joinpoint) {
	      MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
	      Method method = methodSignature.getMethod();
	      Object[] objects = joinpoint.getArgs();
	      for(Object param : objects) {
	         LOG.log(Level.INFO, "들어온 파라미터 값 : "+param);
	      }
	      LOG.log(Level.INFO,"실행된 메소드 : "+method.getName());
	      Object obj=null;
	      try {
	         obj = joinpoint.proceed();
	      } catch (Throwable e) {
	         e.printStackTrace();
	      }
	      return obj;
	   }
}

package com.library.utility;


import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOP {
	
	private static Log logger = LogFactory.getLog(AOP.class);
	
	@AfterThrowing(pointcut = "execution(* com.library.service.*Impl.*(..))", throwing = "exception")
	private void logServiceException(Exception exception) {
		logger.error(exception.getMessage(), exception);
	}


}

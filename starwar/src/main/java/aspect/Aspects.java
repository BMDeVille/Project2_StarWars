package aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component("aspect")
@Aspect
public class Aspects {
	final static Logger logger = Logger.getLogger(Aspects.class);

//	//login controller logging
//	@AfterReturning(pointcut="execution(* serlvet.LoginController.login(..))",returning="result") 
//	public void logReturn(JoinPoint jp, Object result) {
//		logger.info(jp.getSignature().getName() + " return ");
//		logger.info(result);
//	}
//	
//	@Around("execution(* serlvet.LoginController.login(..))")
//	public void logLogin(ProceedingJoinPoint jp) throws Throwable {
//		logger.info("called " + jp.getSignature().getName());
//		jp.proceed();
//		logger.info(jp.getSignature().getName() + " done");
//		logger.info("--------------------");
//	}
//	
//	@AfterThrowing(pointcut = "execution(* servlet.LoginController.login(..))",throwing="error")
//	public void logException(JoinPoint jp, Throwable error) {
//		logger.error(jp.getSignature().getName() + " " + error);
//		logger.info("--------------------");
//	}
//	
//	//register controller logging
//	@AfterReturning(pointcut="execution(* serlvet.RegisterController.createAccount(..))",returning="result") 
//	public void createReturn(JoinPoint jp, Object result) {
//		logger.info(jp.getSignature().getName() + " return ");
//		logger.info(result);
//	}
//	
//	@Around("execution(* serlvet.RegisterController.createAccount(..))")
//	public void logCreate(ProceedingJoinPoint jp) throws Throwable {
//		logger.info("called " + jp.getSignature().getName());
//		jp.proceed();
//		logger.info(jp.getSignature().getName() + " done");
//		logger.info("--------------------");
//	}
//	
//	@AfterThrowing(pointcut = "execution(* servlet.RegisterController.createAccount(..))",throwing="error")
//	public void createException(JoinPoint jp, Throwable error) {
//		logger.error(jp.getSignature().getName() + " " + error);
//		logger.info("--------------------");
//	}
//	
//	//reset password controller logging
//	@AfterReturning(pointcut="execution(* serlvet.ResetPasswordController.reset(..))",returning="result") 
//	public void resetReturn(JoinPoint jp, Object result) {
//		logger.info(jp.getSignature().getName() + " return ");
//		logger.info(result);
//	}
//	
//	@Around("execution(* serlvet.ResetPasswordController.reset(..))")
//	public void logReset(ProceedingJoinPoint jp) throws Throwable {
//		logger.info("called " + jp.getSignature().getName());
//		jp.proceed();
//		logger.info(jp.getSignature().getName() + " done");
//		logger.info("--------------------");
//	}
//	
//	@AfterThrowing(pointcut = "execution(* servlet.ResetPasswordController.reset(..))",throwing="error")
//	public void resetException(JoinPoint jp, Throwable error) {
//		logger.error(jp.getSignature().getName() + " " + error);
//		logger.info("--------------------");
//	}
}

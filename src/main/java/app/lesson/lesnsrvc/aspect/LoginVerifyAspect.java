package app.lesson.lesnsrvc.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.lesson.lesnsrvc.constant.ResponseCode;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.service.GenericUserService;

@Aspect
@Component
public class LoginVerifyAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private GenericUserService genericUserService;
	
	@Pointcut(value = "(execution(* app.lesson.lesnsrvc.service.StudentService.*(..)) && args(req)) "
			+ "|| (execution(* app.lesson.lesnsrvc.service.TeacherService.*(..)) && args(req)))", 
			argNames="req")
	public void verificationNeeded(CommonRequest req) {}
	
	@Around(value = "verificationNeeded(req)", argNames="req")
	public Object verifyLoginState(ProceedingJoinPoint pjp, CommonRequest req) {
		String loginState = req.getLoginState();
		logger.info("登录态检查：{}", loginState);
		LoginedUser user = null;
		if (loginState != null && !"".equals(loginState)) {
			user = genericUserService.getUserInfo(loginState);
		}
		req.setUserInfo(user);
		Object res = null;
		try {
			res = pjp.proceed();
		} catch (Throwable e) {
			logger.error("连接点方法执行异常：", e);
			MethodSignature ms = (MethodSignature) pjp.getSignature();
			Method m = ms.getMethod();
			Class<?> returnType = m.getReturnType();
			if (CommonResponse.class.isAssignableFrom(returnType)) {
				try {
					res = returnType.newInstance();
				} catch (Exception e1) {
					logger.error("aop Advice方法反射创建返回值实例异常：", e1);
					return res;
				}
				((CommonResponse) res).setResponse(ResponseCode.SERVER_ERROR);
			}
		}
		return res;
	}
}

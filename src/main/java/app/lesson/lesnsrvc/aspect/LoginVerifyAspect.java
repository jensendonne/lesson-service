package app.lesson.lesnsrvc.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
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
	
	@Pointcut(value = "(execution(app.lesson.lesnsrvc.response.CommonResponse app.lesson.lesnsrvc.service.StudentService.*(..)) && args(req)) "
			+ "|| (execution(app.lesson.lesnsrvc.response.CommonResponse app.lesson.lesnsrvc.service.TeacherService.*(..)) && args(req)))", 
			argNames="req")
	public void verificationNeeded(CommonRequest req) {}
	
	@Around(value = "verificationNeeded(req)", argNames="req")
	public CommonResponse verifyLoginState(ProceedingJoinPoint pjp, CommonRequest req) {
		String loginState = req.getLoginState();
		logger.info("登录态检查：{}", loginState);
		CommonResponse res = new CommonResponse();
		if (loginState == null || "".equals(loginState)) {
			logger.info("登录态为空。");
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		LoginedUser user = genericUserService.getUserInfo(loginState);
		if (user == null) {
			logger.info("登录态失效。");
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		logger.info("登录态正常。");
		req.setUserInfo(user);
		try {
			res = (CommonResponse)pjp.proceed();
		} catch (Throwable e) {
			logger.error("连接点方法执行异常：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
		}
		return res;
	}
}

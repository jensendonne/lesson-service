package app.lesson.lesnsrvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lesson.lesnsrvc.constant.ResponseCode;
import app.lesson.lesnsrvc.constant.UserRole;
import app.lesson.lesnsrvc.dao.TeachAuthCodeMapper;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.TeachAuthCode;
import app.lesson.lesnsrvc.model.Teacher;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.TeacherRegisterRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.service.DataPersistenceService;
import app.lesson.lesnsrvc.service.TeacherService;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeachAuthCodeMapper teachAuthCodeMapper;
	@Autowired
	private DataPersistenceService dataPersistenceService;
	
	@Override
	public CommonResponse register(TeacherRegisterRequest req) {
		logger.info("注册教师身份");
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Integer role = userInfo.getRole();
		if (role != null && UserRole.Student.idCode == role.intValue()) {
			res.setResponse(ResponseCode.REGISTERED);
			return res;
		} else if (role != null && UserRole.Teacher.idCode == role.intValue()) {
			res.setResponse(ResponseCode.SUCCESS);
			return res;
		}
		// 若走到这里，则未注册过，于是开始注册教师
		String authCode = req.getAuthCode();
		if (authCode == null || authCode.equals("")) {
			res.setResponse(ResponseCode.INVALID_AUTH_CODE);
			return res;
		}
		TeachAuthCode validAuthCode = teachAuthCodeMapper.selectValidAuthcode(authCode);
		if (validAuthCode == null) {
			res.setResponse(ResponseCode.INVALID_AUTH_CODE);
			return res;
		}
		Teacher teacher = new Teacher();
		teacher.setOpenid(userInfo.getOpenid());
		teacher.setAuthCode(authCode);
		if (req.getName() == null || req.getName().equals("")) {
			teacher.setName("老师");
		} else {
			teacher.setName(req.getName());
		}
		validAuthCode.setUsed(true);
		userInfo.setRole(UserRole.Teacher.idCode);
		try {
			dataPersistenceService.registerATeacher(teacher, validAuthCode, userInfo);
		} catch (Exception e) {
			logger.error("数据库异常-注册教师：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		logger.info("注册教师身份成功");
		res.setResponse(ResponseCode.SUCCESS);
		return res;
	}
	
	@Override
	public CommonResponse unregister(CommonRequest req) {
		logger.info("注销教师身份");
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		userInfo.setRole(UserRole.Nobody.idCode);
		try {
			dataPersistenceService.unregisterATeacher(userInfo);
		} catch (Exception e) {
			logger.error("数据库异常-注销教师：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		logger.info("注销教师身份成功");
		res.setResponse(ResponseCode.SUCCESS);
		return res;
	}
}

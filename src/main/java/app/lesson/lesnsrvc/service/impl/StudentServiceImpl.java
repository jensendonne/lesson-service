package app.lesson.lesnsrvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lesson.lesnsrvc.constant.ResponseCode;
import app.lesson.lesnsrvc.constant.UserRole;
import app.lesson.lesnsrvc.dao.StudentMapper;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.service.DataPersistenceService;
import app.lesson.lesnsrvc.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private DataPersistenceService dataPersistenceService;
	
	@Override
	public CommonResponse register(CommonRequest req) {
		logger.info("注册学生身份");
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Integer role = userInfo.getRole();
		if (role != null && UserRole.Teacher.idCode == role.intValue()) {
			res.setResponse(ResponseCode.REGISTERED);
			return res;
		} else if (role != null && UserRole.Student.idCode == role.intValue()) {
			res.setResponse(ResponseCode.SUCCESS);
			return res;
		}
		// 若走到这里，则未注册过，于是开始注册学生
		Student stu = new Student();
		stu.setOpenid(userInfo.getOpenid());
		stu.setLessonAmount(0);
		userInfo.setRole(UserRole.Student.idCode);
		try {
			dataPersistenceService.registerAStudent(stu, userInfo);
		} catch (Exception e) {
			logger.error("数据库异常-注册学生：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		logger.info("注册学生身份成功");
		res.setResponse(ResponseCode.SUCCESS);
		return res;
	}
	
	@Override
	public CommonResponse unregister(CommonRequest req) {
		logger.info("注销学生身份");
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Student student = studentMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (student != null) {
			int lessonAmount = student.getLessonAmount();
			if (lessonAmount > 0) {
				res.setResponse(ResponseCode.LESSON_REMAINED);
				return res;
			}
		}
		userInfo.setRole(UserRole.Nobody.idCode);
		try {
			dataPersistenceService.unregisterAStudent(student, userInfo);
		} catch (Exception e) {
			logger.error("数据库异常-注销学生：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		logger.info("注销学生身份成功");
		res.setResponse(ResponseCode.SUCCESS);
		return res;
	}
}

package app.lesson.lesnsrvc.service.impl;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.constant.DBConstant;
import app.lesson.lesnsrvc.constant.ResponseCode;
import app.lesson.lesnsrvc.constant.UserRole;
import app.lesson.lesnsrvc.dao.LessonLogMapper;
import app.lesson.lesnsrvc.dao.StudentMapper;
import app.lesson.lesnsrvc.dao.TeachAuthCodeMapper;
import app.lesson.lesnsrvc.dao.TeacherMapper;
import app.lesson.lesnsrvc.model.LessonLog;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.SimpleLessonLog;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.model.TeachAuthCode;
import app.lesson.lesnsrvc.model.Teacher;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.ConfirmSignedInRequest;
import app.lesson.lesnsrvc.request.HomeworkDTO;
import app.lesson.lesnsrvc.request.HomeworkRequest;
import app.lesson.lesnsrvc.request.ListQueryRequest;
import app.lesson.lesnsrvc.request.QueryByDateRequest;
import app.lesson.lesnsrvc.request.StuMainInfoRequest;
import app.lesson.lesnsrvc.request.TeacherArrangeRequest;
import app.lesson.lesnsrvc.request.TeacherQueryHisStudentLessonRequest;
import app.lesson.lesnsrvc.request.TeacherRegisterRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.response.QueryStudentsResponse;
import app.lesson.lesnsrvc.response.StuAndLessonInfoResponse;
import app.lesson.lesnsrvc.response.StuInfoAndLessonLogResponse;
import app.lesson.lesnsrvc.response.StuMainInfoResponse;
import app.lesson.lesnsrvc.model.StudentAndLessonInfo;
import app.lesson.lesnsrvc.service.DataPersistenceService;
import app.lesson.lesnsrvc.service.StudentService;
import app.lesson.lesnsrvc.service.TeacherService;
import app.lesson.lesnsrvc.util.UsefulTools;

@Service
public class TeacherServiceImpl implements TeacherService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeachAuthCodeMapper teachAuthCodeMapper;
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private LessonLogMapper lessonLogMapper;
	@Autowired
	private DataPersistenceService dataPersistenceService;
	@Autowired
	private StudentService studentService;
	
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
	
	@Override
	public QueryStudentsResponse queryAllStudents(ListQueryRequest req) {
		logger.info("查询全体学生列表：页码-{}，每页条数-{}", req.getPageNum(), req.getPageSize());
		QueryStudentsResponse res = new QueryStudentsResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查看所有学生名单
			res.setResponse(ResponseCode.USER_NOT_FOUND);
			return res;
		}
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<Student> dbList = studentMapper.selectAllStudents();
		PageInfo<Student> pageInfo = new PageInfo<>(dbList);
		List<QueryStudentsResponse.StudentInfo> resList = new ArrayList<>();
		// TODO 这里是默认排序，最好是按名字排序
		for (Student record: dbList) {
			QueryStudentsResponse.StudentInfo info = new QueryStudentsResponse.StudentInfo();
			info.setStudentId(record.getStudentId());
			info.setName(record.getName());
			info.setLessonLevel(record.getLessonLevel());
			info.setAvailLessonAmount(record.getAvailLessonAmount());
			info.setFrozenLessonAmount(record.getFrozenLessonAmount());
			resList.add(info);
		}
		res.setStudentList(resList);
		pageInfo.setList(null);
		res.setPageInfo(pageInfo);
		res.setTeacherName(teacher.getName());
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询全体学生列表成功");
		return res;
	}
	
	@Override
	public StuMainInfoResponse getStudentInfo(StuMainInfoRequest req) {
		logger.info("查询学生排课信息-" + req.getCurrentYearMonth());
		StuMainInfoResponse res = new StuMainInfoResponse();
		LoginedUser userInfo = req.getUserInfo(); //老师的用户信息
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		String studentId = req.getStudentId();
		if (studentId == null || studentId.equals("")) {
			res.setResponse(ResponseCode.PARAM_ERROR, "studentId不能为空");
			return res;
		}
		Student student = studentMapper.selectByStudentId(studentId);
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND);
			return res;
		}
		String yearMonth = req.getCurrentYearMonth();
		studentService.setLessonInfo(student, yearMonth, res);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询学生排课信息成功");
		return res;
	}
	
	@Override
	public CommonResponse arrangeLessons(TeacherArrangeRequest req) {
		logger.info("教师排课：{}-{}", req.getArrangeDate(), req.getArrangeTime());
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限排课
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		String arrangeDateStr = req.getArrangeDate();
		String arrangeTimeStr = req.getArrangeTime();
		int lessonUsed = req.getLessonUsed();
		Date arrangeDate = null;
		try {
			arrangeDate = UsefulTools.str2Date(arrangeDateStr);
		} catch (ParseException e) {
			res.setResponse(ResponseCode.PARAM_ERROR, "所选日期格式错误");
			return res;
		}
		if (!DBConstant.TIME_CONSTANT_LIST.contains(arrangeTimeStr)) {
			res.setResponse(ResponseCode.PARAM_ERROR, "所选时间格式错误");
			return res;
		}
		if (lessonUsed <= 0) {
			res.setResponse(ResponseCode.PARAM_ERROR, "使用课时必须大于0");
			return res;
		}
		String studentId = req.getStudentId();
		if (studentId == null || studentId.equals("")) {
			res.setResponse(ResponseCode.PARAM_ERROR, "studentId不能为空");
			return res;
		}
		Student student = studentMapper.selectByStudentId(studentId);
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND, "学生信息不存在");
			return res;
		}
		int availAmount = student.getAvailLessonAmount();
		if (availAmount < lessonUsed) {
			res.setResponse(ResponseCode.LESSON_LACK);
			return res;
		}
		LessonLog lessonLog = lessonLogMapper.selectByPrimaryKey(studentId, arrangeDate);
		if (lessonLog != null) {
			boolean studentConfirm = lessonLog.getStudentConfirm();
    		boolean teacherConfirm = lessonLog.getTeacherConfirm();
    		if (studentConfirm && teacherConfirm) {
    			res.setResponse(ResponseCode.CLASS_COMPLETED);
    			return res;
    		} else if (studentConfirm) {
    			res.setResponse(ResponseCode.CLASS_SIGNED_IN);
    			return res;
    		}
    		LessonLog updateLog = new LessonLog();
    		updateLog.setStudentId(studentId);
    		updateLog.setSigninDate(arrangeDate);
    		updateLog.setSigninTime(arrangeTimeStr);
    		updateLog.setLessonUsed(lessonUsed);
    		updateLog.setTeacherOpenid(teacher.getOpenid());
    		try {
				dataPersistenceService.updateLessonLogByPrimaryKeySelective(updateLog);
			} catch (Exception e) {
				logger.error("数据更新异常-教师排课：", e);
				res.setResponse(ResponseCode.SERVER_ERROR);
				return res;
			}
		} else {
			LessonLog newLog = new LessonLog();
			newLog.setStudentId(studentId);
			newLog.setSigninDate(arrangeDate);
			newLog.setSigninTime(arrangeTimeStr);
			newLog.setLessonUsed(lessonUsed);
			newLog.setTeacherOpenid(teacher.getOpenid());
			try {
				dataPersistenceService.insertLessonLogSelective(newLog);
			} catch (Exception e) {
				logger.error("数据插入异常-教师排课：", e);
				res.setResponse(ResponseCode.SERVER_ERROR);
				return res;
			}
		}
		//向客户端发送通知
		//TODO
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("教师排课成功");
		return res;
	}
	
	@Override
	public StuAndLessonInfoResponse queryTheSignedInStudents(QueryByDateRequest req) {
		logger.info("查询已签到学生：日期-{}", req.getDate());
		StuAndLessonInfoResponse res = new StuAndLessonInfoResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		String dateStr = req.getDate();
		if (UsefulTools.isStringEmpty(dateStr)) {
			res.setResponse(ResponseCode.PARAM_ERROR, "查询日期不能为空");
			return res;
		}
		Date date = null;
		try {
			date = UsefulTools.str2Date(dateStr);
		} catch (ParseException e) {
			res.setResponse(ResponseCode.PARAM_ERROR, "查询日期格式错误");
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查询
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<StudentAndLessonInfo> list = lessonLogMapper.selectTheSignedInByDate(date);
		PageInfo<StudentAndLessonInfo> pageInfo = new PageInfo<>(list);
		pageInfo.setList(null);
		res.setList(list);
		res.setPageInfo(pageInfo);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询已签到学生成功");
		return res;
	}
	
	@Override
	public CommonResponse confirmTheSignedInStudents(ConfirmSignedInRequest req) {
		logger.info("教师确认学生签到：日期-{}", req.getSigninDate());
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查询
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		String dateStr = req.getSigninDate();
		Date signinDate = null;
		try {
			signinDate = UsefulTools.str2Date(dateStr);
		} catch (ParseException e) {
			res.setResponse(ResponseCode.PARAM_ERROR, "查询日期格式错误");
			return res;
		}
		List<String> sidList = req.getStudentIdList();
		if (sidList == null || sidList.size() <= 0) {
			res.setResponse(ResponseCode.PARAM_ERROR, "学生id不能为空");
			return res;
		}
		List<Student> studentDataList = new ArrayList<>();
		List<LessonLog> lessonLogDataList = new ArrayList<>();
		for (String studentId : sidList) {
			Student currentStu = studentMapper.selectByStudentId(studentId);
			LessonLog currentLog = lessonLogMapper.selectByPrimaryKey(studentId, signinDate);
			if (currentStu != null && currentLog != null) {
				Student newStu = new Student();
				newStu.setOpenid(currentStu.getOpenid());
				newStu.setFrozenLessonAmount(currentStu.getFrozenLessonAmount() - currentLog.getLessonUsed());
				studentDataList.add(newStu);
				LessonLog newLog = new LessonLog();
				newLog.setStudentId(studentId);
				newLog.setSigninDate(signinDate);
				newLog.setTeacherConfirm(true);
				newLog.setTeacherOpenid(teacher.getOpenid());
				lessonLogDataList.add(newLog);
			}
		}
		try {
			dataPersistenceService.teacherConfirm(studentDataList, lessonLogDataList);
		} catch (Exception e) {
			logger.error("数据库异常-确认签到：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("教师确认学生签到成功");
		return res;
	}
	
	@Override
	public StuInfoAndLessonLogResponse queryLessonHistoryOfOneStudent(TeacherQueryHisStudentLessonRequest req) {
		logger.info("查询门下学生上课记录：学生id-{}", req.getStudentId());
		StuInfoAndLessonLogResponse res = new StuInfoAndLessonLogResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查询
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		Student student = studentMapper.selectByStudentId(req.getStudentId());
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND, "学生信息不存在");
			return res;
		}
		res.setStudentId(student.getStudentId());
		res.setStudentName(student.getName());
		res.setLessonLevel(student.getLessonLevel());
		res.setAvailAmount(student.getAvailLessonAmount());
		res.setFrozenAmount(student.getFrozenLessonAmount());
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<SimpleLessonLog> list = lessonLogMapper.selectCompletedLessonsBySidAndTid(
				req.getStudentId(), teacher.getOpenid());
		PageInfo<SimpleLessonLog> pageInfo = new PageInfo<>(list);
		pageInfo.setList(null);
		res.setList(list);
		res.setPageInfo(pageInfo);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询门下学生上课记录成功");
		return res;
	}
	
	@Override
	public CommonResponse assignHomework(HomeworkRequest req) {
		logger.info("教师布置作业");
		CommonResponse res = new CommonResponse();
		List<HomeworkDTO> homeworkList = req.getHomeworkList();
		if (homeworkList == null) {
			res.setResponse(ResponseCode.PARAM_ERROR, "主要参数不存在");
			return res;
		}
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限操作
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		List<LessonLog> updateList = new ArrayList<>();
		for (HomeworkDTO o : homeworkList) {
			if (!UsefulTools.isStringEmpty(o.getStudentId()) 
					&& !UsefulTools.isStringEmpty(o.getDate()) 
					&& !UsefulTools.isStringEmpty(o.getHomework())) {
				try {
					Date date = UsefulTools.yyyyMMdd2Date(o.getDate());
					LessonLog record = new LessonLog();
					record.setStudentId(o.getStudentId());
					record.setSigninDate(date);
					record.setHomework(o.getHomework());
					updateList.add(record);
				} catch (ParseException e) {
					logger.error("布置作业日期格式错误：{}", o.getDate());
					continue;
				}
			}
		}
		try {
			dataPersistenceService.updateLessonLogByPrimaryKeySelective(updateList);
		} catch (Exception e) {
			logger.error("数据库异常-布置作业：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("教师布置作业成功");
		return res;
	}
	
	@Override
	public CommonResponse commentHomework(HomeworkRequest req) {
		logger.info("教师评价作业");
		CommonResponse res = new CommonResponse();
		List<HomeworkDTO> homeworkList = req.getHomeworkList();
		if (homeworkList == null) {
			res.setResponse(ResponseCode.PARAM_ERROR, "主要参数不存在");
			return res;
		}
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限操作
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		List<LessonLog> updateList = new ArrayList<>();
		for (HomeworkDTO o : homeworkList) {
			if (!UsefulTools.isStringEmpty(o.getStudentId()) 
					&& !UsefulTools.isStringEmpty(o.getDate()) 
					&& !UsefulTools.isStringEmpty(o.getComment())) {
				try {
					Date date = UsefulTools.yyyyMMdd2Date(o.getDate());
					LessonLog record = new LessonLog();
					record.setStudentId(o.getStudentId());
					record.setSigninDate(date);
					record.setHwComment(o.getComment());
					updateList.add(record);
				} catch (ParseException e) {
					logger.error("评价作业日期格式错误：{}", o.getDate());
					continue;
				}
			}
		}
		try {
			dataPersistenceService.updateLessonLogByPrimaryKeySelective(updateList);
		} catch (Exception e) {
			logger.error("数据库异常-评价作业：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("教师评价作业成功");
		return res;
	}
	
	@Override
	public StuAndLessonInfoResponse queryAllLessonsOfATeacher(ListQueryRequest req) {
		logger.info("查询教师本人教授的所有课程：页码-{}，页大小-{}", req.getPageNum(), req.getPageSize());
		StuAndLessonInfoResponse res = new StuAndLessonInfoResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查询
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<StudentAndLessonInfo> list = lessonLogMapper.selectCompletedLessonsByTid(userInfo.getOpenid());
		PageInfo<StudentAndLessonInfo> pageInfo = new PageInfo<>(list);
		pageInfo.setList(null);
		res.setList(list);
		res.setPageInfo(pageInfo);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询教师本人教授的所有课程成功");
		return res;
	}
	
	@Override
	public StuAndLessonInfoResponse queryOwnLessonsByDate(QueryByDateRequest req) {
		logger.info("查询指定日期的已完成的课程：日期-{}", req.getDate());
		StuAndLessonInfoResponse res = new StuAndLessonInfoResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		String dateStr = req.getDate();
		if (UsefulTools.isStringEmpty(dateStr)) {
			res.setResponse(ResponseCode.PARAM_ERROR, "查询日期不能为空");
			return res;
		}
		Date date = null;
		try {
			date = UsefulTools.str2Date(dateStr);
		} catch (ParseException e) {
			res.setResponse(ResponseCode.PARAM_ERROR, "查询日期格式错误");
			return res;
		}
		Teacher teacher = teacherMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (teacher == null) {
			// 确保老师才有权限查询
			res.setResponse(ResponseCode.USER_NOT_FOUND, "教师信息不存在");
			return res;
		}
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<StudentAndLessonInfo> list = 
				lessonLogMapper.selectCompletedLessonsByDateAndTid(userInfo.getOpenid(), date);
		PageInfo<StudentAndLessonInfo> pageInfo = new PageInfo<>(list);
		pageInfo.setList(null);
		res.setList(list);
		res.setPageInfo(pageInfo);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询指定日期的已完成的课程成功");
		return res;
	}
}

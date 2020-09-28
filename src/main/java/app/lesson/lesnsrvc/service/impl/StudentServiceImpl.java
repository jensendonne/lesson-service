package app.lesson.lesnsrvc.service.impl;

import java.io.File;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.constant.DBConstant;
import app.lesson.lesnsrvc.constant.ResponseCode;
import app.lesson.lesnsrvc.constant.UserRole;
import app.lesson.lesnsrvc.dao.LessonLogMapper;
import app.lesson.lesnsrvc.dao.StudentMapper;
import app.lesson.lesnsrvc.model.LessonLog;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.LessonHistoryRequest;
import app.lesson.lesnsrvc.request.StuMainInfoRequest;
import app.lesson.lesnsrvc.request.StudentRegisterRequest;
import app.lesson.lesnsrvc.request.StudentSigninRequest;
import app.lesson.lesnsrvc.request.UploadVideoRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.response.LessonHistoryResponse;
import app.lesson.lesnsrvc.response.StuMainInfoResponse;
import app.lesson.lesnsrvc.service.DataPersistenceService;
import app.lesson.lesnsrvc.service.StudentService;
import app.lesson.lesnsrvc.util.UsefulTools;

@Service
public class StudentServiceImpl implements StudentService {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentMapper studentMapper;
	@Autowired
	private LessonLogMapper lessonLogMapper;
	@Autowired
	private DataPersistenceService dataPersistenceService;
	
	@Value("${path.video}")
	private String videoPath;
	
	@Override
	public CommonResponse register(StudentRegisterRequest req) {
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
		try {
			stu.setStudentId(UsefulTools.digestMD5(userInfo.getOpenid()));
		} catch (Exception e) {
			logger.error("生成student id异常：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		if (req.getName() != null) {
			stu.setName(req.getName());
		} else {
			stu.setName("同学" + UsefulTools.generateRandomNumber(4));
		}
		stu.setAvailLessonAmount(0);
		stu.setFrozenLessonAmount(0);
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
			int lessonAmount = student.getAvailLessonAmount() + student.getFrozenLessonAmount();
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
	
	@Override
	public StuMainInfoResponse getMainInfo(StuMainInfoRequest req) {
		logger.info("查询学生首页信息-" + req.getCurrentYearMonth());
		StuMainInfoResponse res = new StuMainInfoResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Student student = studentMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND);
			return res;
		}
		String yearMonth = req.getCurrentYearMonth();
		Date d;
		try {
			d = UsefulTools.yyyyMMdd2Date(yearMonth + "01");
		} catch (ParseException e) {
			d = new Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(d);
		List<Integer> signinedDatesList = lessonLogMapper.selectSigninedDatesByYearMonth(
				student.getStudentId(), c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
		List<Integer> toConfirmDatesList = lessonLogMapper.selectToConfirmDatesByYearMonth(
				student.getStudentId(), c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
		List<Integer> toSigninDatesList = lessonLogMapper.selectToSigninDatesByYearMonth(
				student.getStudentId(), c.get(Calendar.YEAR), c.get(Calendar.MONTH) + 1);
		if (signinedDatesList != null && signinedDatesList.size() > 0) {
			res.setSigninedDatesThisMonth(signinedDatesList.toArray(new Integer[signinedDatesList.size()]));
		} else {
			res.setSigninedDatesThisMonth(new Integer[0]);
		}
		if (toConfirmDatesList != null && toConfirmDatesList.size() > 0) {
			res.setToConfirmDatesThisMonth(toConfirmDatesList.toArray(new Integer[toConfirmDatesList.size()]));
		} else {
			res.setToConfirmDatesThisMonth(new Integer[0]);
		}
		if (toSigninDatesList != null && toSigninDatesList.size() > 0) {
			res.setToSigninDatesThisMonth(toSigninDatesList.toArray(new Integer[toSigninDatesList.size()]));
		} else {
			res.setToSigninDatesThisMonth(new Integer[0]);
		}
		res.setStudentId(student.getStudentId());
		res.setName(student.getName());
		res.setLessonLevel(student.getLessonLevel());
		res.setAvailLessonAmount(student.getAvailLessonAmount());
		res.setFrozenLessonAmount(student.getFrozenLessonAmount());
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询学生首页信息成功");
		return res;
	}
	
	@Override
	public CommonResponse signin(StudentSigninRequest req) {
		logger.info("学生签到：{}-{}", req.getSigninDate(), req.getSigninTime());
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		String signinDateStr = req.getSigninDate();
		String signinTimeStr = req.getSigninTime();
		int lessonUsed = req.getLessonUsed();
		Date signinDate = null;
		try {
			signinDate = UsefulTools.str2Date(signinDateStr);
		} catch (ParseException e) {
			res.setResponse(ResponseCode.PARAM_ERROR, "签到日期格式错误");
			return res;
		}
		if (!DBConstant.TIME_CONSTANT_LIST.contains(signinTimeStr)) {
			res.setResponse(ResponseCode.PARAM_ERROR, "签到时间格式错误");
			return res;
		}
		if (lessonUsed <= 0) {
			res.setResponse(ResponseCode.PARAM_ERROR, "使用课时必须大于0");
			return res;
		}
		Student student = studentMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND);
			return res;
		}
		int oldAvailAmount = student.getAvailLessonAmount();
		if (oldAvailAmount < lessonUsed) {
			res.setResponse(ResponseCode.LESSON_LACK);
			return res;
		}
		LessonLog record = new LessonLog();
		Student newStu = new Student();
		record.setStudentId(student.getStudentId());
		record.setSigninDate(signinDate);
		record.setSigninTime(signinTimeStr);
		record.setLessonUsed(lessonUsed);
		record.setStudentConfirm(true);
		int oldFrozenAmount = student.getFrozenLessonAmount();
		newStu.setAvailLessonAmount(oldAvailAmount - lessonUsed);
		newStu.setFrozenLessonAmount(oldFrozenAmount + lessonUsed);
		newStu.setOpenid(student.getOpenid());
		try {
			dataPersistenceService.studentSignin(record, newStu);
		} catch (Exception e) {
			logger.error("数据库异常-学生签到：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		logger.info("学生签到成功");
		res.setResponse(ResponseCode.SUCCESS);
		return res;
	}
	
	@Override
	public LessonHistoryResponse lessonHistory(LessonHistoryRequest req) {
		logger.info("查询学生上课记录：页码-{}，每页条数-{}", req.getPageNum(), req.getPageSize());
		LessonHistoryResponse res = new LessonHistoryResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		Student student = studentMapper.selectByPrimaryKey(userInfo.getOpenid());
		if (student == null) {
			res.setResponse(ResponseCode.USER_NOT_FOUND);
			return res;
		}
		PageHelper.startPage(req.getPageNum(), req.getPageSize());
		List<LessonLog> logList = lessonLogMapper.selectLessonHistoryByStudentId(student.getStudentId());
		PageInfo<LessonLog> pageInfo = new PageInfo<>(logList);
		List<LessonHistoryResponse.LessonRecord> hisList = new ArrayList<>();
		for (LessonLog e : logList) {
			LessonHistoryResponse.LessonRecord record = new LessonHistoryResponse.LessonRecord();
			String dateStr = UsefulTools.date2Str(e.getSigninDate());
			String timeChar = e.getSigninTime();
			String time = null;
			if (DBConstant.TIME_EVENING.equals(timeChar)) {
				time = "晚上";
			} else if (DBConstant.TIME_AFTERNOON.equals(timeChar)) {
				time = "下午";
			} else {
				time = "早上";
			}
			int lessonUsed = e.getLessonUsed();
			record.setStudentId(e.getStudentId());
			record.setTitle(dateStr + "，" + time + "，" + lessonUsed + "课时");
			record.setDate(dateStr);
			record.setTime(time);
			record.setLessonUsed(lessonUsed);
			record.setStudent(e.getsName());
			record.setTeacher(e.gettName());
			record.setHomework(e.getHomework());
			record.setHwCompleted(e.getHwCompleted());
			record.setHwComment(e.getHwComment());
			hisList.add(record);
		}
		res.setHistory(hisList);
		pageInfo.setList(null);
		res.setPageInfo(pageInfo);
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("查询学生上课记录成功");
		return res;
	}
	
	@Override
	public CommonResponse uploadHomework(UploadVideoRequest req) {
		logger.info("上传作业视频-{}", req.getDate());
		CommonResponse res = new CommonResponse();
		LoginedUser userInfo = req.getUserInfo();
		if (userInfo == null) {
			res.setResponse(ResponseCode.LOGIN_EXPIRED);
			return res;
		}
		String sid = req.getStudentId();
		if (sid == null || "".equals(sid)) {
			res.setResponse(ResponseCode.PARAM_ERROR, "学生id不能为空");
			return res;
		}
		String date = req.getDate();
		if (date == null || "".equals(date)) {
			// TODO 应改为正则表达式判断
			date = UsefulTools.date2yyyyMMdd(new Date());
		}
		MultipartFile uploadedFile = req.getFile();
		String savedFilePath = videoPath + date + "/" + sid + "-" + date + ".mp4";
		try {
			File savedFile = new File(savedFilePath);
			if (savedFile.exists()) {
				savedFile.delete();
			}
			if (savedFile.getParentFile() != null) {
				savedFile.getParentFile().mkdirs();
			}
			savedFile.createNewFile();
			uploadedFile.transferTo(savedFile);
		} catch (Exception e) {
			logger.error("存储用户上传文件异常：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		LessonLog log = new LessonLog();
		try {
			log.setStudentId(sid);
			log.setSigninDate(UsefulTools.yyyyMMdd2Date(date));
			log.setHwCompleted(true);
			dataPersistenceService.updateLessonLogByPrimaryKeySelective(log);
		} catch (Exception e) {
			logger.error("更新数据库异常-学生上传作业：", e);
			res.setResponse(ResponseCode.SERVER_ERROR);
			return res;
		}
		res.setResponse(ResponseCode.SUCCESS);
		logger.info("上传作业视频成功");
		return res;
	}
}

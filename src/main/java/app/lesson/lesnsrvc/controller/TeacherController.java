package app.lesson.lesnsrvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;

import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.ConfirmSignedInRequest;
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
import app.lesson.lesnsrvc.service.TeacherService;

@RestController
@RequestMapping("/teacher")
public class TeacherController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private TeacherService teacherService;
	
	@PostMapping("/register")
	@ResponseBody
	public CommonResponse register(@RequestBody TeacherRegisterRequest req) {
		// TODO 敏感信息
		logger.info(JSON.toJSONString(req));
		CommonResponse res = teacherService.register(req);
		logger.info("/teacher/register 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/unregister")
	@ResponseBody
	public CommonResponse unregister(@RequestBody CommonRequest req) {
		// TODO 敏感信息
		logger.info(JSON.toJSONString(req));
		CommonResponse res = teacherService.unregister(req);
		logger.info("/teacher/unregister 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/allstudents")
	@ResponseBody
	public QueryStudentsResponse queryAllStudents(@RequestBody ListQueryRequest req) {
		QueryStudentsResponse res = teacherService.queryAllStudents(req);
		logger.info("/teacher/allstudents 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/studentinfo")
	@ResponseBody
	public StuMainInfoResponse queryStudentInfo(@RequestBody StuMainInfoRequest req) {
		StuMainInfoResponse res = teacherService.getStudentInfo(req);
		logger.info("/teacher/studentinfo 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/arrange")
	@ResponseBody
	public CommonResponse arrangeLessons(@RequestBody TeacherArrangeRequest req) {
		CommonResponse res = teacherService.arrangeLessons(req);
		logger.info("/teacher/arrange 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/the-signed-in")
	@ResponseBody
	public StuAndLessonInfoResponse queryTheSignedIns(@RequestBody QueryByDateRequest req) {
		StuAndLessonInfoResponse res = teacherService.queryTheSignedInStudents(req);
		logger.info("/teacher/the-signed-in 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/confirm")
	@ResponseBody
	public CommonResponse confirmTheSignedIns(@RequestBody ConfirmSignedInRequest req) {
		CommonResponse res = teacherService.confirmTheSignedInStudents(req);
		logger.info("/teacher/confirm 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/lessonhistory-1")
	@ResponseBody
	public StuInfoAndLessonLogResponse queryLessonHistoryOfOneStudent(@RequestBody TeacherQueryHisStudentLessonRequest req) {
		StuInfoAndLessonLogResponse res = teacherService.queryLessonHistoryOfOneStudent(req);
		logger.info("/teacher/lessonhistory-1 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/assign")
	@ResponseBody
	public CommonResponse assignHomework(@RequestBody HomeworkRequest req) {
		CommonResponse res = teacherService.assignHomework(req);
		logger.info("/teacher/assign 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/comment")
	@ResponseBody
	public CommonResponse commentHomework(@RequestBody HomeworkRequest req) {
		CommonResponse res = teacherService.commentHomework(req);
		logger.info("/teacher/comment 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/alllessons")
	@ResponseBody
	public StuAndLessonInfoResponse queryAllLessons(@RequestBody ListQueryRequest req) {
		StuAndLessonInfoResponse res = teacherService.queryAllLessonsOfATeacher(req);
		logger.info("/teacher/alllessons 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
	
	@PostMapping("/lessons-by-date")
	@ResponseBody
	public StuAndLessonInfoResponse queryLessonsByDate(@RequestBody QueryByDateRequest req) {
		StuAndLessonInfoResponse res = teacherService.queryOwnLessonsByDate(req);
		logger.info("/teacher/lessons-by-date 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
}

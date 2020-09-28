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
import app.lesson.lesnsrvc.request.LessonHistoryRequest;
import app.lesson.lesnsrvc.request.StuMainInfoRequest;
import app.lesson.lesnsrvc.request.StudentRegisterRequest;
import app.lesson.lesnsrvc.request.StudentSigninRequest;
import app.lesson.lesnsrvc.request.UploadVideoRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.response.LessonHistoryResponse;
import app.lesson.lesnsrvc.response.StuMainInfoResponse;
import app.lesson.lesnsrvc.service.StudentService;

@RestController
@RequestMapping("/student")
public class StudentController {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private StudentService studentService;
	
	@PostMapping("/register")
	@ResponseBody
	public CommonResponse register(@RequestBody StudentRegisterRequest req) {
		// TODO 敏感信息
		logger.info(JSON.toJSONString(req));
		CommonResponse res = studentService.register(req);
		logger.info(JSON.toJSONString(res));
		return res;
	}
	
	@PostMapping("/unregister")
	@ResponseBody
	public CommonResponse unregister(@RequestBody CommonRequest req) {
		// TODO 敏感信息
		logger.info(JSON.toJSONString(req));
		CommonResponse res = studentService.unregister(req);
		logger.info(JSON.toJSONString(res));
		return res;
	}
	
	@PostMapping("/maininfo")
	@ResponseBody
	public StuMainInfoResponse getStudentMainInfo(@RequestBody StuMainInfoRequest req) {
		StuMainInfoResponse res = studentService.getMainInfo(req);
		return res;
	}
	
	@PostMapping("/signin")
	@ResponseBody
	public CommonResponse signin(@RequestBody StudentSigninRequest req) {
		return studentService.signin(req);
	}
	
	@PostMapping("/lessonhistory")
	@ResponseBody
	public LessonHistoryResponse lessonHistory(@RequestBody LessonHistoryRequest req) {
		return studentService.lessonHistory(req);
	}
	
	@PostMapping(value="/upload", headers="content-type=multipart/form-data")
	@ResponseBody
	public CommonResponse uploadHomework(UploadVideoRequest req) {
		return studentService.uploadHomework(req);
	}
}

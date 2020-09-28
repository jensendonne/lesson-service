package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.LessonHistoryRequest;
import app.lesson.lesnsrvc.request.StuMainInfoRequest;
import app.lesson.lesnsrvc.request.StudentRegisterRequest;
import app.lesson.lesnsrvc.request.StudentSigninRequest;
import app.lesson.lesnsrvc.request.UploadVideoRequest;
import app.lesson.lesnsrvc.response.CommonResponse;
import app.lesson.lesnsrvc.response.LessonHistoryResponse;
import app.lesson.lesnsrvc.response.StuMainInfoResponse;

public interface StudentService {

	public CommonResponse register(StudentRegisterRequest req);
	
	public CommonResponse unregister(CommonRequest req);
	
	public StuMainInfoResponse getMainInfo(StuMainInfoRequest req);
	
	public CommonResponse signin(StudentSigninRequest req);
	
	public LessonHistoryResponse lessonHistory(LessonHistoryRequest req);
	
	public CommonResponse uploadHomework(UploadVideoRequest req);
}

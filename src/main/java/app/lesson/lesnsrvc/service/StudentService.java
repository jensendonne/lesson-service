package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.ListQueryRequest;
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
	
	public LessonHistoryResponse lessonHistory(ListQueryRequest req);
	
	public CommonResponse uploadHomework(UploadVideoRequest req);
	
	public void setLessonInfo(Student student, String yearMonth, StuMainInfoResponse res);
}

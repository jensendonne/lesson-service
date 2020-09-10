package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.TeacherRegisterRequest;
import app.lesson.lesnsrvc.response.CommonResponse;

public interface TeacherService {

	public CommonResponse register(TeacherRegisterRequest req);
	
	public CommonResponse unregister(CommonRequest req);
}

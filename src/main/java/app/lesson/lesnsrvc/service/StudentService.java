package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.request.CommonRequest;
import app.lesson.lesnsrvc.request.StudentRegisterRequest;
import app.lesson.lesnsrvc.response.CommonResponse;

public interface StudentService {

	public CommonResponse register(StudentRegisterRequest req);
	
	public CommonResponse unregister(CommonRequest req);
}

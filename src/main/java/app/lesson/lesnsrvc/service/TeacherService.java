package app.lesson.lesnsrvc.service;

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

public interface TeacherService {

	public CommonResponse register(TeacherRegisterRequest req);
	
	public CommonResponse unregister(CommonRequest req);
	
	/**
	 * 返回全体学生的列表（分页）
	 * @param req 请求参数里的pageSize=0时可不分页返回全部记录
	 * @return
	 */
	public QueryStudentsResponse queryAllStudents(ListQueryRequest req);
	
	public StuMainInfoResponse getStudentInfo(StuMainInfoRequest req);
	
	public CommonResponse arrangeLessons(TeacherArrangeRequest req);
	
	public StuAndLessonInfoResponse queryTheSignedInStudents(QueryByDateRequest req);
	
	public CommonResponse confirmTheSignedInStudents(ConfirmSignedInRequest req);
	
	public StuInfoAndLessonLogResponse queryLessonHistoryOfOneStudent(TeacherQueryHisStudentLessonRequest req);
	
	public CommonResponse assignHomework(HomeworkRequest req);
	
	public CommonResponse commentHomework(HomeworkRequest req);
	
	public StuAndLessonInfoResponse queryAllLessonsOfATeacher(ListQueryRequest req);
	
	public StuAndLessonInfoResponse queryOwnLessonsByDate(QueryByDateRequest req);
}

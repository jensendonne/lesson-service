/**
 * 
 */
package app.lesson.lesnsrvc.service;

import java.util.List;

import app.lesson.lesnsrvc.model.LessonLog;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.model.TeachAuthCode;
import app.lesson.lesnsrvc.model.Teacher;

/**
 * @author Rocketman
 *
 */
public interface DataPersistenceService {

	public void insertOrUpdateLoginedUser(LoginedUser user) throws Exception;

	public void registerAStudent(Student student, LoginedUser user) throws Exception;

	public void unregisterAStudent(Student student, LoginedUser user) throws Exception;

	public void registerATeacher(Teacher teacher, TeachAuthCode authCode, LoginedUser user) throws Exception;

	public void unregisterATeacher(LoginedUser user) throws Exception;

	public void studentSignin(LessonLog record, Student student) throws Exception;

	public void updateLessonLogByPrimaryKeySelective(LessonLog record) throws Exception;
	
	public void updateLessonLogByPrimaryKeySelective(List<LessonLog> list) throws Exception;
	
	public void insertLessonLogSelective(LessonLog record) throws Exception;
	
	public void teacherConfirm(List<Student> studentList, List<LessonLog> lessonLogList) throws Exception;
}

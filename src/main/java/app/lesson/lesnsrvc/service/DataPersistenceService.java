/**
 * 
 */
package app.lesson.lesnsrvc.service;

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
}

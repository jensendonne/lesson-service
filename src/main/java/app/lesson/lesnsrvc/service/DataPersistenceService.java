/**
 * 
 */
package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;

/**
 * @author Rocketman
 *
 */
public interface DataPersistenceService {

	public void insertOrUpdateLoginedUser(LoginedUser user) throws Exception;
	
	public void registerAStudent(Student student, LoginedUser user) throws Exception;
	
	public void unregisterAStudent(Student student, LoginedUser user) throws Exception;
}

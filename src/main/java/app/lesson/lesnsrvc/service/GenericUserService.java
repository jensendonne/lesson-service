/**
 * 
 */
package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.model.LoginedUser;

/**
 * @author Rocketman
 *
 */
public interface GenericUserService {

	public String generateLoginState(String code);

	public LoginedUser getUserInfo(String loginState);
}

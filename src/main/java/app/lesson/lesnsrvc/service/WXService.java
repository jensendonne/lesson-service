/**
 * 
 */
package app.lesson.lesnsrvc.service;

import app.lesson.lesnsrvc.response.WXLoginResponse;

/**
 * 负责调用微信服务端API
 * @author Rocketman
 *
 */
public interface WXService {

	public WXLoginResponse loginCode2Session(String code) throws Exception;
}

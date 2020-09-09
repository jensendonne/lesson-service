/**
 * 
 */
package app.lesson.lesnsrvc.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import app.lesson.lesnsrvc.dao.LoginedUserMapper;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.response.WXLoginResponse;
import app.lesson.lesnsrvc.service.DataPersistenceService;
import app.lesson.lesnsrvc.service.GenericUserService;
import app.lesson.lesnsrvc.service.WXService;
import app.lesson.lesnsrvc.util.SecurityUtils;

/**
 * @author Rocketman
 *
 */
@Service
public class GenericUserServiceImpl implements GenericUserService {

	@Autowired
	private WXService wxService;
	@Autowired
	private DataPersistenceService dataPersistenceService;
	@Autowired
	private LoginedUserMapper loginedUserMapper;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Override
	public String generateLoginState(String code) {
		WXLoginResponse wxRes = null;
		try {
			wxRes = wxService.loginCode2Session(code);
		} catch (Exception e) {
			// TODO
			logger.error("调用微信登录接口异常：{}", e.getMessage());
			return null;
		}
		if (wxRes == null) {
			logger.error("微信登录接口返回异常。");
			return null;
		}
		LoginedUser record = new LoginedUser();
		String loginState = null;
		try {
			loginState = SecurityUtils.digestSHA256(wxRes.getSession_key());
		} catch (Exception e) {
			logger.error("生成登录态异常：", e);
			return null;
		}
		// TODO 敏感信息
		logger.info("生成登录态：{}", loginState);
		record.setOpenid(wxRes.getOpenid());
		record.setSessionKey(wxRes.getSession_key());
		record.setLoginState(loginState);
		try {
			dataPersistenceService.insertOrUpdateLoginedUser(record);
		} catch (Exception e) {
			logger.error("logined_user表更新异常：", e);
			return null;
		}
		return loginState;
	}
	
	@Override
	public LoginedUser getUserInfo(String loginState) {
		try {
			LoginedUser user = loginedUserMapper.selectByLoginState(loginState);
			return user;
		} catch (Exception e) {
			logger.error("数据库检索登录态异常：{}", e);
			return null;
		}
		
	}
}

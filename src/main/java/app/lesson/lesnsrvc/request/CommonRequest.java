package app.lesson.lesnsrvc.request;

import app.lesson.lesnsrvc.model.LoginedUser;

public class CommonRequest {

	private String loginState;
	
	private LoginedUser userInfo;

	public String getLoginState() {
		return loginState;
	}

	public void setLoginState(String loginState) {
		this.loginState = loginState;
	}

	public LoginedUser getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(LoginedUser userInfo) {
		this.userInfo = userInfo;
	}
}

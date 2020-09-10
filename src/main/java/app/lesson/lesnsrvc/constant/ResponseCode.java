package app.lesson.lesnsrvc.constant;

public enum ResponseCode {

	SUCCESS("0000", "成功"),
	LOGIN_EXPIRED("0001", "登录态失效"),
	REGISTERED("0002", "已注册过其他身份"),
	LESSON_REMAINED("0003", "当前剩余课时未结束，不能注销"),
	INVALID_AUTH_CODE("0004", "授权码无效，不能注册教师"),
	
	SERVER_ERROR("000X", "服务器故障");
	
	public String resCode;
	public String resMsg;
	
	private ResponseCode (String code, String msg) {
		this.resCode = code;
		this.resMsg = msg;
	}
}

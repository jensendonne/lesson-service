package app.lesson.lesnsrvc.constant;

public enum ResponseCode {

	/**
	 * 0000，成功
	 */
	SUCCESS("0000", "成功"),
	/**
	 * 0001，登录态失效
	 */
	LOGIN_EXPIRED("0001", "登录态失效"),
	/**
	 * 0002，已注册过其他身份
	 */
	REGISTERED("0002", "已注册过其他身份"),
	/**
	 * 0003，用户信息不存在
	 */
	USER_NOT_FOUND("0003", "用户信息不存在"),
	/**
	 * 0004，参数不合要求
	 */
	PARAM_ERROR("0004", "参数不合要求"),
	
	/**
	 * 0101，当前剩余课时未结束，不能注销
	 */
	LESSON_REMAINED("0101", "当前剩余课时未结束，不能注销"),
	/**
	 * 0102，可用课时不足
	 */
	LESSON_LACK("0102", "可用课时不足"),
	
	/**
	 * 0201，授权码无效，不能注册教师
	 */
	INVALID_AUTH_CODE("0201", "授权码无效，不能注册教师"),
	/**
	 * 0202，该日期课程已完成
	 */
	CLASS_COMPLETED("0202", "该日期课程已完成"),
	/**
	 * 0203，该日期学生已签到，无须再排课
	 */
	CLASS_SIGNED_IN("0203", "该日期学生已签到，无须再排课"),
	
	/**
	 * 1001，服务器故障
	 */
	SERVER_ERROR("1001", "服务器故障");
	
	public String resCode;
	public String resMsg;
	
	private ResponseCode (String code, String msg) {
		this.resCode = code;
		this.resMsg = msg;
	}
}

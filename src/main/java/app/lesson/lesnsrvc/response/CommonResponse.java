package app.lesson.lesnsrvc.response;

import app.lesson.lesnsrvc.constant.ResponseCode;

public class CommonResponse {

	private String resCode;

	private String resMsg;

	public String getResCode() {
		return resCode;
	}

	public void setResCode(String resCode) {
		this.resCode = resCode;
	}

	public String getResMsg() {
		return resMsg;
	}

	public void setResMsg(String resMsg) {
		this.resMsg = resMsg;
	}

	public void setResponse(ResponseCode res) {
		this.resCode = res.resCode;
		this.resMsg = res.resMsg;
	}
	
	public void setResponse(ResponseCode res, String customMsg) {
		this.resCode = res.resCode;
		this.resMsg = customMsg;
	}
}

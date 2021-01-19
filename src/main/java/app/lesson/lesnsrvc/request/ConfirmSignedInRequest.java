package app.lesson.lesnsrvc.request;

import java.util.List;

public class ConfirmSignedInRequest extends CommonRequest {

	private List<String> studentIdList;
	
	/**
	 * 签到日期
	 * 格式：yyyy-MM-dd
	 */
	private String signinDate;

	public List<String> getStudentIdList() {
		return studentIdList;
	}

	public void setStudentIdList(List<String> studentIdList) {
		this.studentIdList = studentIdList;
	}

	public String getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(String signinDate) {
		this.signinDate = signinDate;
	}
}

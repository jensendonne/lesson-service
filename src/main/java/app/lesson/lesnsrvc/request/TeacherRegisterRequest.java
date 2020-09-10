package app.lesson.lesnsrvc.request;

public class TeacherRegisterRequest extends CommonRequest {

	private String authCode;
	
	private String name;

	public String getAuthCode() {
		return authCode;
	}

	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

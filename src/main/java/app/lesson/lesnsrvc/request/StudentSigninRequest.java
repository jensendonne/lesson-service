package app.lesson.lesnsrvc.request;

public class StudentSigninRequest extends CommonRequest {

	/**
	 * 签到日期
	 * 格式：yyyy-MM-dd
	 */
	private String signinDate;
	
	/**
	 * 签到时间
	 * M-早上，A-下午，E-晚上
	 */
	private String signinTime;
	
	/**
	 * 消耗课时
	 */
	private int lessonUsed;

	public String getSigninDate() {
		return signinDate;
	}

	public void setSigninDate(String signinDate) {
		this.signinDate = signinDate;
	}

	public String getSigninTime() {
		return signinTime;
	}

	public void setSigninTime(String signinTime) {
		this.signinTime = signinTime;
	}

	public int getLessonUsed() {
		return lessonUsed;
	}

	public void setLessonUsed(int lessonUsed) {
		this.lessonUsed = lessonUsed;
	}
}

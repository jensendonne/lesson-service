package app.lesson.lesnsrvc.response;

/**
 * 学生首页的主要信息响应
 * @author Rocketman
 *
 */
public class StuMainInfoResponse extends CommonResponse {

	private String studentId;
	
	private String name;
	
	private int lessonLevel;
	
	private int availLessonAmount;
	
	private int frozenLessonAmount;
	
	private Integer[] signinedDatesThisMonth;
	
	private Integer[] toConfirmDatesThisMonth;
	
	private Integer[] toSigninDatesThisMonth;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getLessonLevel() {
		return lessonLevel;
	}

	public void setLessonLevel(int lessonLevel) {
		this.lessonLevel = lessonLevel;
	}

	public int getAvailLessonAmount() {
		return availLessonAmount;
	}

	public void setAvailLessonAmount(int availLessonAmount) {
		this.availLessonAmount = availLessonAmount;
	}

	public int getFrozenLessonAmount() {
		return frozenLessonAmount;
	}

	public void setFrozenLessonAmount(int frozenLessonAmount) {
		this.frozenLessonAmount = frozenLessonAmount;
	}

	public Integer[] getSigninedDatesThisMonth() {
		return signinedDatesThisMonth;
	}

	public void setSigninedDatesThisMonth(Integer[] signinedDatesThisMonth) {
		this.signinedDatesThisMonth = signinedDatesThisMonth;
	}

	public Integer[] getToConfirmDatesThisMonth() {
		return toConfirmDatesThisMonth;
	}

	public void setToConfirmDatesThisMonth(Integer[] toConfirmDatesThisMonth) {
		this.toConfirmDatesThisMonth = toConfirmDatesThisMonth;
	}

	public Integer[] getToSigninDatesThisMonth() {
		return toSigninDatesThisMonth;
	}

	public void setToSigninDatesThisMonth(Integer[] toSigninDatesThisMonth) {
		this.toSigninDatesThisMonth = toSigninDatesThisMonth;
	}
}

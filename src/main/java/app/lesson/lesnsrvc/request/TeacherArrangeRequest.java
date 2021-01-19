package app.lesson.lesnsrvc.request;

public class TeacherArrangeRequest extends CommonRequest {

	private String studentId;
	
	/**
	 * 计划上课日期
	 * 格式：yyyy-MM-dd
	 */
	private String arrangeDate;
	
	/**
	 * 计划上课时间
	 * M-早上，A-下午，E-晚上
	 */
	private String arrangeTime;
	
	/**
	 * 消耗课时
	 */
	private int lessonUsed;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getArrangeDate() {
		return arrangeDate;
	}

	public void setArrangeDate(String arrangeDate) {
		this.arrangeDate = arrangeDate;
	}

	public String getArrangeTime() {
		return arrangeTime;
	}

	public void setArrangeTime(String arrangeTime) {
		this.arrangeTime = arrangeTime;
	}

	public int getLessonUsed() {
		return lessonUsed;
	}

	public void setLessonUsed(int lessonUsed) {
		this.lessonUsed = lessonUsed;
	}
}

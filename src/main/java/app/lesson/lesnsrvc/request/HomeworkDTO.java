package app.lesson.lesnsrvc.request;

public class HomeworkDTO {

	private String studentId;
	
	/**
	 * 日期
	 * 格式：yyyyMMdd
	 */
	private String date;
	
	/**
	 * 作业要求
	 */
	private String homework;
	
	/**
	 * 作业评价
	 */
	private String comment;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHomework() {
		return homework;
	}

	public void setHomework(String homework) {
		this.homework = homework;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}

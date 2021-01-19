package app.lesson.lesnsrvc.model;

public class StudentAndLessonInfo {

	private String studentId;

	private String studentName;

	private int lessonLevel;

	private int availAmount;

	private int frozenAmount;

	private String date;

	private String time;

	private int lessonUsed;

	private String teacher;

	private String homework;

	private boolean hwCompleted;

	private String hwComment;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getLessonLevel() {
		return lessonLevel;
	}

	public void setLessonLevel(int lessonLevel) {
		this.lessonLevel = lessonLevel;
	}

	public int getAvailAmount() {
		return availAmount;
	}

	public void setAvailAmount(int availAmount) {
		this.availAmount = availAmount;
	}

	public int getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(int frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public int getLessonUsed() {
		return lessonUsed;
	}

	public void setLessonUsed(int lessonUsed) {
		this.lessonUsed = lessonUsed;
	}

	public String getTeacher() {
		return teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getHomework() {
		return homework;
	}

	public void setHomework(String homework) {
		this.homework = homework;
	}

	public boolean isHwCompleted() {
		return hwCompleted;
	}

	public void setHwCompleted(boolean hwCompleted) {
		this.hwCompleted = hwCompleted;
	}

	public String getHwComment() {
		return hwComment;
	}

	public void setHwComment(String hwComment) {
		this.hwComment = hwComment;
	}
}

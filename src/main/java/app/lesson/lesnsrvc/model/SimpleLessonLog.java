package app.lesson.lesnsrvc.model;

public class SimpleLessonLog {

	private String date;

	private String time;

	private int lessonUsed;

	private String teacher;

	private String homework;

	private boolean hwCompleted;

	private String hwComment;

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

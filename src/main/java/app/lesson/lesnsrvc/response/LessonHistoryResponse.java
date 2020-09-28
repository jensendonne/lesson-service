package app.lesson.lesnsrvc.response;

import java.util.List;

import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.model.LessonLog;

public class LessonHistoryResponse extends CommonResponse {

	private List<LessonRecord> history;
	
	private PageInfo<LessonLog> pageInfo;
	
	public List<LessonRecord> getHistory() {
		return history;
	}

	public void setHistory(List<LessonRecord> history) {
		this.history = history;
	}

	public PageInfo<LessonLog> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<LessonLog> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public static class LessonRecord {
		
		private String studentId;
		
		private String title;
		
		private String date;
		
		private String time;
		
		private int lessonUsed;
		
		private String student;
		
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

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
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

		public String getStudent() {
			return student;
		}

		public void setStudent(String student) {
			this.student = student;
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
}

package app.lesson.lesnsrvc.response;

import java.util.List;

import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.model.Student;

public class QueryStudentsResponse extends CommonResponse {

	private List<StudentInfo> studentList;
	
	private PageInfo<Student> pageInfo;
	
	/**
	 * 发送请求的老师的名字
	 */
	private String teacherName;
	
	public List<StudentInfo> getStudentList() {
		return studentList;
	}

	public void setStudentList(List<StudentInfo> studentList) {
		this.studentList = studentList;
	}

	public PageInfo<Student> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<Student> pageInfo) {
		this.pageInfo = pageInfo;
	}

	public String getTeacherName() {
		return teacherName;
	}

	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public static class StudentInfo {
		
		private String studentId;
		
		private String name;
		
		private int lessonLevel;
		
		private int availLessonAmount;

	    private int frozenLessonAmount;

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
	}
}

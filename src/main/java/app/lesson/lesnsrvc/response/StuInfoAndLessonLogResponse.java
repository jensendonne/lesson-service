package app.lesson.lesnsrvc.response;

import java.util.List;

import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.model.SimpleLessonLog;

public class StuInfoAndLessonLogResponse extends CommonResponse {

	private String studentId;

	private String studentName;

	private int lessonLevel;

	private int availAmount;

	private int frozenAmount;
	
	private List<SimpleLessonLog> list;
	
	private PageInfo<SimpleLessonLog> pageInfo;

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

	public List<SimpleLessonLog> getList() {
		return list;
	}

	public void setList(List<SimpleLessonLog> list) {
		this.list = list;
	}

	public PageInfo<SimpleLessonLog> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<SimpleLessonLog> pageInfo) {
		this.pageInfo = pageInfo;
	}
}

package app.lesson.lesnsrvc.response;

import java.util.List;

import com.github.pagehelper.PageInfo;

import app.lesson.lesnsrvc.model.StudentAndLessonInfo;


public class StuAndLessonInfoResponse extends CommonResponse {

	private List<StudentAndLessonInfo> list;
	
	private PageInfo<StudentAndLessonInfo> pageInfo;
	
	public List<StudentAndLessonInfo> getList() {
		return list;
	}

	public void setList(List<StudentAndLessonInfo> list) {
		this.list = list;
	}

	public PageInfo<StudentAndLessonInfo> getPageInfo() {
		return pageInfo;
	}

	public void setPageInfo(PageInfo<StudentAndLessonInfo> pageInfo) {
		this.pageInfo = pageInfo;
	}
}

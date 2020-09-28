package app.lesson.lesnsrvc.response;

import java.util.List;

import app.lesson.lesnsrvc.model.LessonProduct;

public class LessonProductResponse extends CommonResponse {

	private List<LessonProduct> list;

	public List<LessonProduct> getList() {
		return list;
	}

	public void setList(List<LessonProduct> list) {
		this.list = list;
	}
}

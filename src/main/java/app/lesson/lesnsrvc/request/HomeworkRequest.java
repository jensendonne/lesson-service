package app.lesson.lesnsrvc.request;

import java.util.List;

public class HomeworkRequest extends CommonRequest {

	private List<HomeworkDTO> homeworkList;

	public List<HomeworkDTO> getHomeworkList() {
		return homeworkList;
	}

	public void setHomeworkList(List<HomeworkDTO> homeworkList) {
		this.homeworkList = homeworkList;
	}
}

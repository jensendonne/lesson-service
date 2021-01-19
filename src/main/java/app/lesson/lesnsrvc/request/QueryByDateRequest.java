package app.lesson.lesnsrvc.request;

public class QueryByDateRequest extends ListQueryRequest {

	/**
	 * 签到日期
	 * 格式：yyyy-MM-dd
	 */
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}

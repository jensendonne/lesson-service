/**
 * 
 */
package app.lesson.lesnsrvc.request;

/**
 * 获取学生首页主要信息的请求
 * @author Rocketman
 *
 */
public class StuMainInfoRequest extends CommonRequest {

	/**
	 * 当前需要展示的月份，格式：yyyymm
	 */
	private String currentYearMonth;

	public String getCurrentYearMonth() {
		return currentYearMonth;
	}

	public void setCurrentYearMonth(String currentYearMonth) {
		this.currentYearMonth = currentYearMonth;
	}
}

package app.lesson.lesnsrvc.request;

public class ListQueryRequest extends CommonRequest {

	/**
	 * 页码
	 */
	private int pageNum;
	
	/**
	 * 每页显示数量
	 */
	private int pageSize;

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
}

package app.lesson.lesnsrvc.request;

import org.springframework.web.multipart.MultipartFile;

public class UploadVideoRequest extends CommonRequest {

	private String studentId;
	
	/**
	 * 作业的日期，格式yyyyMMdd
	 */
	private String date;
	
	/**
	 * 作业的视频文件
	 */
	private MultipartFile file;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}
	
	
}

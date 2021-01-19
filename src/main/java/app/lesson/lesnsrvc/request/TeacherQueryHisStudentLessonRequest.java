/**
 * 
 */
package app.lesson.lesnsrvc.request;

/**
 * 教师查询经由自己授完课的某学生的课程信息
 * @author Rocketman
 *
 */
public class TeacherQueryHisStudentLessonRequest extends ListQueryRequest {

	private String studentId;

	public String getStudentId() {
		return studentId;
	}

	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
}

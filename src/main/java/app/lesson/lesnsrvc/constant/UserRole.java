package app.lesson.lesnsrvc.constant;

public enum UserRole {

	Nobody(0), Student(1), Teacher(2);
	
	public int idCode;
	
	private UserRole(int code) {
		this.idCode = code;
	}
}

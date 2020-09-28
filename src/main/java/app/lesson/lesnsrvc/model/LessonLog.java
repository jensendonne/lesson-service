package app.lesson.lesnsrvc.model;

import java.util.Date;

public class LessonLog {
    private String studentId;

    private Date signinDate;

    private String signinTime;

    private Integer lessonUsed;

    private Boolean studentConfirm;

    private Boolean teacherConfirm;

    private String teacherOpenid;

    private String homework;

    private Boolean hwCompleted;

    private String hwComment;

    private Date createTime;

    private Date updateTime;
    
    private String sName;
    
    private String tName;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public Date getSigninDate() {
        return signinDate;
    }

    public void setSigninDate(Date signinDate) {
        this.signinDate = signinDate;
    }

    public String getSigninTime() {
        return signinTime;
    }

    public void setSigninTime(String signinTime) {
        this.signinTime = signinTime == null ? null : signinTime.trim();
    }

    public Integer getLessonUsed() {
        return lessonUsed;
    }

    public void setLessonUsed(Integer lessonUsed) {
        this.lessonUsed = lessonUsed;
    }

    public Boolean getStudentConfirm() {
        return studentConfirm;
    }

    public void setStudentConfirm(Boolean studentConfirm) {
        this.studentConfirm = studentConfirm;
    }

    public Boolean getTeacherConfirm() {
        return teacherConfirm;
    }

    public void setTeacherConfirm(Boolean teacherConfirm) {
        this.teacherConfirm = teacherConfirm;
    }

    public String getTeacherOpenid() {
        return teacherOpenid;
    }

    public void setTeacherOpenid(String teacherOpenid) {
        this.teacherOpenid = teacherOpenid == null ? null : teacherOpenid.trim();
    }

    public String getHomework() {
        return homework;
    }

    public void setHomework(String homework) {
        this.homework = homework == null ? null : homework.trim();
    }

    public Boolean getHwCompleted() {
        return hwCompleted;
    }

    public void setHwCompleted(Boolean hwCompleted) {
        this.hwCompleted = hwCompleted;
    }

    public String getHwComment() {
        return hwComment;
    }

    public void setHwComment(String hwComment) {
        this.hwComment = hwComment == null ? null : hwComment.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

	public String getsName() {
		return sName;
	}

	public void setsName(String sName) {
		this.sName = sName;
	}

	public String gettName() {
		return tName;
	}

	public void settName(String tName) {
		this.tName = tName;
	}
}
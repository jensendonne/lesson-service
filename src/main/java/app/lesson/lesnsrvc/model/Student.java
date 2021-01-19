package app.lesson.lesnsrvc.model;

import java.util.Date;

public class Student {
    private String openid;

    private String studentId;

    private String name;

    private Integer lessonLevel;

    private Integer availLessonAmount;

    private Integer frozenLessonAmount;

    private Date createTime;

    private Date updateTime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId == null ? null : studentId.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Integer getLessonLevel() {
        return lessonLevel;
    }

    public void setLessonLevel(Integer lessonLevel) {
        this.lessonLevel = lessonLevel;
    }

    public Integer getAvailLessonAmount() {
        return availLessonAmount;
    }

    public void setAvailLessonAmount(Integer availLessonAmount) {
        this.availLessonAmount = availLessonAmount;
    }

    public Integer getFrozenLessonAmount() {
        return frozenLessonAmount;
    }

    public void setFrozenLessonAmount(Integer frozenLessonAmount) {
        this.frozenLessonAmount = frozenLessonAmount;
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
}
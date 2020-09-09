package app.lesson.lesnsrvc.model;

import java.util.Date;

public class Student {
    private String openid;

    private Integer lessonAmount;

    private Date createTime;

    private Date updateTime;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid == null ? null : openid.trim();
    }

    public Integer getLessonAmount() {
        return lessonAmount;
    }

    public void setLessonAmount(Integer lessonAmount) {
        this.lessonAmount = lessonAmount;
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
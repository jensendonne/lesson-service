package app.lesson.lesnsrvc.dao;

import app.lesson.lesnsrvc.model.Teacher;

public interface TeacherMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Teacher record);

    int insertSelective(Teacher record);

    Teacher selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(Teacher record);

    int updateByPrimaryKey(Teacher record);
}
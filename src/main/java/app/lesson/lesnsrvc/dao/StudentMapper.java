package app.lesson.lesnsrvc.dao;

import app.lesson.lesnsrvc.model.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
}
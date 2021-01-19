package app.lesson.lesnsrvc.dao;

import java.util.List;

import app.lesson.lesnsrvc.model.Student;

public interface StudentMapper {
    int deleteByPrimaryKey(String openid);

    int insert(Student record);

    int insertSelective(Student record);

    Student selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(Student record);

    int updateByPrimaryKey(Student record);
    
    List<Student> selectAllStudents();
    
    Student selectByStudentId(String studentId);
}
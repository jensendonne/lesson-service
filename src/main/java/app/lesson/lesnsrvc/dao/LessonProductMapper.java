package app.lesson.lesnsrvc.dao;

import java.util.List;

import app.lesson.lesnsrvc.model.LessonProduct;

public interface LessonProductMapper {
    int deleteByPrimaryKey(Integer level);

    int insert(LessonProduct record);

    int insertSelective(LessonProduct record);

    LessonProduct selectByPrimaryKey(Integer level);

    int updateByPrimaryKeySelective(LessonProduct record);

    int updateByPrimaryKey(LessonProduct record);
    
    List<LessonProduct> selectAll();
}
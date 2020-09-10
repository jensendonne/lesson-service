package app.lesson.lesnsrvc.dao;

import app.lesson.lesnsrvc.model.TeachAuthCode;

public interface TeachAuthCodeMapper {
    int deleteByPrimaryKey(String authCode);

    int insert(TeachAuthCode record);

    int insertSelective(TeachAuthCode record);

    TeachAuthCode selectByPrimaryKey(String authCode);

    int updateByPrimaryKeySelective(TeachAuthCode record);

    int updateByPrimaryKey(TeachAuthCode record);

    TeachAuthCode selectValidAuthcode(String authCode);
}
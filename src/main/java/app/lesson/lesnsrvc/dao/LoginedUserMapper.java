package app.lesson.lesnsrvc.dao;

import app.lesson.lesnsrvc.model.LoginedUser;

public interface LoginedUserMapper {
    int deleteByPrimaryKey(String openid);

    int insert(LoginedUser record);

    int insertSelective(LoginedUser record);

    LoginedUser selectByPrimaryKey(String openid);

    int updateByPrimaryKeySelective(LoginedUser record);

    int updateByPrimaryKey(LoginedUser record);

    LoginedUser selectByLoginState(String loginState);
}
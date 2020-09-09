/**
 * 
 */
package app.lesson.lesnsrvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.lesson.lesnsrvc.dao.LoginedUserMapper;
import app.lesson.lesnsrvc.dao.StudentMapper;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.service.DataPersistenceService;

/**
 * @author Rocketman
 *
 */
@Service
@Transactional(rollbackFor={Exception.class})
public class DataPersistenceServiceImpl implements DataPersistenceService {

	@Autowired
	private LoginedUserMapper loginedUserMapper;
	@Autowired
	private StudentMapper studentMapper;
	
	@Override
	public void insertOrUpdateLoginedUser(LoginedUser user) throws Exception {
		LoginedUser existing = loginedUserMapper.selectByPrimaryKey(user.getOpenid());
		if (existing != null) {
			loginedUserMapper.updateByPrimaryKeySelective(user);
		} else {
			loginedUserMapper.insertSelective(user);
		}
	}
	
	@Override
	public void registerAStudent(Student student, LoginedUser user) throws Exception {
		user.setCreateTime(null);
		user.setUpdateTime(null);
		loginedUserMapper.updateByPrimaryKeySelective(user);
		studentMapper.insertSelective(student);
	}
	
	@Override
	public void unregisterAStudent(Student student, LoginedUser user) throws Exception {
		user.setCreateTime(null);
		user.setUpdateTime(null);
		loginedUserMapper.updateByPrimaryKeySelective(user);
		if (student != null) {
			studentMapper.deleteByPrimaryKey(student.getOpenid());
		}
	}
}
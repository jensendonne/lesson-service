/**
 * 
 */
package app.lesson.lesnsrvc.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import app.lesson.lesnsrvc.dao.LessonLogMapper;
import app.lesson.lesnsrvc.dao.LoginedUserMapper;
import app.lesson.lesnsrvc.dao.StudentMapper;
import app.lesson.lesnsrvc.dao.TeachAuthCodeMapper;
import app.lesson.lesnsrvc.dao.TeacherMapper;
import app.lesson.lesnsrvc.model.LessonLog;
import app.lesson.lesnsrvc.model.LoginedUser;
import app.lesson.lesnsrvc.model.Student;
import app.lesson.lesnsrvc.model.TeachAuthCode;
import app.lesson.lesnsrvc.model.Teacher;
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
	@Autowired
	private TeacherMapper teacherMapper;
	@Autowired
	private TeachAuthCodeMapper teachAuthCodeMapper;
	@Autowired
	private LessonLogMapper lessonLogMapper;
	
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
	
	@Override
	public void registerATeacher(Teacher teacher, TeachAuthCode authCode, LoginedUser user) throws Exception {
		teacherMapper.insertSelective(teacher);
		authCode.setCreateTime(null);
		authCode.setUpdateTime(null);
		teachAuthCodeMapper.updateByPrimaryKeySelective(authCode);
		user.setCreateTime(null);
		user.setUpdateTime(null);
		loginedUserMapper.updateByPrimaryKeySelective(user);
	}
	
	@Override
	public void unregisterATeacher(LoginedUser user) throws Exception {
		user.setCreateTime(null);
		user.setUpdateTime(null);
		loginedUserMapper.updateByPrimaryKeySelective(user);
		teacherMapper.deleteByPrimaryKey(user.getOpenid());
	}
	
	@Override
	public void studentSignin(LessonLog record, Student student) throws Exception {
		LessonLog existing = lessonLogMapper.selectByPrimaryKey(record.getStudentId(), record.getSigninDate());
		if (existing != null) {
			lessonLogMapper.updateByPrimaryKeySelective(record);
		} else {
			lessonLogMapper.insertSelective(record);
		}
		studentMapper.updateByPrimaryKeySelective(student);
	}
	
	@Override
	public void updateLessonLogByPrimaryKeySelective(LessonLog record) throws Exception {
		lessonLogMapper.updateByPrimaryKeySelective(record);
	}
}

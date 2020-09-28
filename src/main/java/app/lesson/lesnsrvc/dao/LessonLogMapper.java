package app.lesson.lesnsrvc.dao;

import app.lesson.lesnsrvc.model.LessonLog;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface LessonLogMapper {
    int deleteByPrimaryKey(@Param("studentId") String studentId, @Param("signinDate") Date signinDate);

    int insert(LessonLog record);

    int insertSelective(LessonLog record);

    LessonLog selectByPrimaryKey(@Param("studentId") String studentId, @Param("signinDate") Date signinDate);

    int updateByPrimaryKeySelective(LessonLog record);

    int updateByPrimaryKey(LessonLog record);
    
    List<Integer> selectSigninedDatesByYearMonth(@Param("studentId") String studentId,
			@Param("year") int year, @Param("month") int month);

	List<Integer> selectToConfirmDatesByYearMonth(@Param("studentId") String studentId,
			@Param("year") int year, @Param("month") int month);

	List<Integer> selectToSigninDatesByYearMonth(@Param("studentId") String studentId,
			@Param("year") int year, @Param("month") int month);
	
	List<LessonLog> selectLessonHistoryByStudentId(String studentId);
}
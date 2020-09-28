/**
 * 
 */
package app.lesson.lesnsrvc.constant;

import java.util.List;

import com.google.common.collect.ImmutableList;

/**
 * 数据库常量
 * @author Rocketman
 *
 */
public class DBConstant {

	public static final String TIME_MORNING = "M";
	
	public static final String TIME_AFTERNOON = "A";
	
	public static final String TIME_EVENING = "E";
	
	public static final List<String> TIME_CONSTANT_LIST = ImmutableList.of("M", "A", "E");
}

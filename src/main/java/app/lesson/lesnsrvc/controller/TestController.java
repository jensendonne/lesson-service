/**
 * 
 */
package app.lesson.lesnsrvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.lesson.lesnsrvc.dao.TestMapper;
import app.lesson.lesnsrvc.model.Test;

/**
 * @author Rocketman
 *
 */
@RestController
@RequestMapping("/test")
public class TestController {

	@Value("${test.str}")
	private String testStr;
	@Autowired
	private TestMapper testMapper;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@GetMapping("/web")
	public String testWeb() {
		logger.debug("调用testWeb()方法");
		logger.info("调用testWeb()方法");
		return testStr == null ? "Hello, world!" : testStr;
	}
	
	@GetMapping("/db")
	public String testDb() {
		logger.debug("调用testDb()方法");
		logger.info("调用testDb()方法");
		Test result = testMapper.selectByPrimaryKey(1);
		if (result != null) {
			return result.getName();
		} else {
			return "测试表中无记录。";
		}
	}

}

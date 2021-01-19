/**
 * 
 */
package app.lesson.lesnsrvc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import app.lesson.lesnsrvc.response.LessonProductResponse;
import app.lesson.lesnsrvc.service.GenericUserService;

/**
 * @author Rocketman
 *
 */
@RestController
@RequestMapping("/generic")
public class GenericUserController {

	@Autowired
	private GenericUserService genericUserService;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@PostMapping("/loginstate")
	public String getLoginState(@RequestBody String reqData) {
		logger.info(reqData);
		JSONObject reqJson = JSON.parseObject(reqData);
		String loginState = genericUserService.generateLoginState(reqJson.getString("code"));
		// TODO 敏感信息
		logger.info("响应登录态代码：{}", loginState);
		return loginState;
	}
	
	@PostMapping("/product")
	@ResponseBody
	public LessonProductResponse getLessonProduct() {
		LessonProductResponse res = genericUserService.getLessonProduct();
		logger.info("/generic/product 响应：resCode={}, resMsg={}", res.getResCode(), res.getResMsg());
		return res;
	}
}

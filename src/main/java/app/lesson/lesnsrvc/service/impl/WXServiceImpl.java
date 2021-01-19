/**
 * 
 */
package app.lesson.lesnsrvc.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.alibaba.fastjson.JSON;

import app.lesson.lesnsrvc.constant.WXConstant;
import app.lesson.lesnsrvc.response.WXLoginResponse;
import app.lesson.lesnsrvc.service.WXService;

/**
 * @author Rocketman
 *
 */
@Service
public class WXServiceImpl implements WXService {

	@Resource
	private RestTemplate restTemplate;
	@Value("${wx.url.login}")
	private String loginUrl;
	@Value("${wx.app.id}")
	private String appId;
	@Value("${wx.app.secret}")
	private String secret;
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Override
	public WXLoginResponse loginCode2Session(String code) throws Exception {
		Map<String, String> uriVariables = new HashMap<>();
		uriVariables.put("appid", appId);
		uriVariables.put("secret", secret);
		uriVariables.put("js_code", code);
		uriVariables.put("grant_type", WXConstant.GRANT_TYPE);
		logger.info(uriVariables.toString());
		WXLoginResponse res = restTemplate.getForObject(loginUrl, WXLoginResponse.class, uriVariables);
		if (res != null) {
			// TODO 敏感信息
			logger.info(JSON.toJSONString(res));
		}
		return res;
	}
}

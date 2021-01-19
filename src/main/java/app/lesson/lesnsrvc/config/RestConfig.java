/**
 * 
 */
package app.lesson.lesnsrvc.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * @author Rocketman
 *
 */
@Configuration
public class RestConfig {

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate rt = new RestTemplate();
		rt.getMessageConverters().add(new WXHttpMessageConverter());
		return rt;
	}
	
	class WXHttpMessageConverter extends MappingJackson2HttpMessageConverter {
		public WXHttpMessageConverter() {
			super();
			List<MediaType> mediaTypeList = new ArrayList<>();
			mediaTypeList.add(MediaType.TEXT_PLAIN);
			setSupportedMediaTypes(mediaTypeList);
		}
	}
}

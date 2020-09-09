/**
 * 
 */
package app.lesson.lesnsrvc.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author Rocketman
 *
 */
//@Configuration
public class WebConfig extends WebMvcConfigurationSupport {
	
	@Value("${path.image}")
	private String imagePath;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/image/**").addResourceLocations("file:///" + imagePath);
	}
}

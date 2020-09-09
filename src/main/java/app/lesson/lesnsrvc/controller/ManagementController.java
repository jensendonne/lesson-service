package app.lesson.lesnsrvc.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/manage")
public class ManagementController {

	@Value("${path.image}")
	private String imagePath;
	@Value("${icon.name}")
	private String iconName;
}

package app.lesson.lesnsrvc;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 应用启动
 *
 */
@SpringBootApplication
@MapperScan("app.lesson.lesnsrvc.dao")
public class Application {
	public static void main(String[] args) {
		System.out.println("启动应用！！！");
		SpringApplication.run(Application.class, args);
	}
}

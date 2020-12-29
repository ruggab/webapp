package it.com.gab.webapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

import it.com.gab.webapp.utils.FileUploadProperties;



@EnableConfigurationProperties({
    FileUploadProperties.class
})

@SpringBootApplication
public class WebappApplication extends SpringBootServletInitializer {
	
	public static void main(String[] args) {
		SpringApplication.run(WebappApplication.class, args);
	}

}

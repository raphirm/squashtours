package squash;

import java.util.ArrayList;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import squash.model.Address;
import squash.model.Court;
import squash.model.Group;
import squash.model.User;
import squash.service.AddressService;
import squash.service.CourtService;
import squash.service.UserService;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class Application extends SpringBootServletInitializer {
	private static Class<Application> applicationClass = Application.class;
	@Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(applicationClass);
    }
	
    public static void main(String[] args) {
    	ConfigurableApplicationContext context = SpringApplication.run(applicationClass, args);
    	
    	//Initial Address 1
    	
    }
}

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
    	AddressService addressService = context.getBean(AddressService.class);
    	Address address = new Address();
    	address.setStreet1("Brandstrasse 49");
    	address.setStreet2("49.2.2");
    	address.setCity("Schlieren");
    	address.setZip("8952");    	
    	addressService.save(address);
    	
    	//Initial Address 2
    	Address address2 = new Address();
    	address2.setStreet1("Flurstrasse 65");
    	address2.setStreet2("4. Stock");
    	address2.setCity("Zürich");
    	address2.setZip("8048");
    	addressService.save(address2);
    	
    	//Court bundled with Address
    	CourtService courtService = context.getBean(CourtService.class);
    	Court court = new Court();
    	court.setCourtName("Vitis");
    	court.setTelephoneNumber("0791234567");
    	court.setAddress(addressService.findByaddressID(1l));
    	courtService.save(court);
    	
    	//User for authentication
    	UserService userservice = context.getBean(UserService.class);
    	User user = new User();
    	ArrayList<Group> groups = new ArrayList<Group>();
    	Group group = new Group();
    	group.setName("admin");
    	groups.add(group);
    	user.seteMail("raphael@marques.com");
    	user.setUsername("admin");
    	user.setPassword("admin");
    	user.setGroups(groups);
    	user.setAddressID(address2);
        userservice.save(user);
        
    }
}

package squash.task;

import java.util.ArrayList;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import squash.model.Address;
import squash.model.Court;
import squash.model.Group;
import squash.model.User;
import squash.service.AddressService;
import squash.service.CourtService;
import squash.service.UserService;

@Component
public class OnStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private AddressService addressService;
	
	@Resource
	private CourtService courtService;
	
	@Resource
	private UserService userservice;
	
	private final Logger log = Logger.getLogger(this.getClass().getName());

	@Override
	public void onApplicationEvent(ContextRefreshedEvent arg0) {
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
    	Court court = new Court();
    	court.setCourtName("Vitis");
    	court.setTelephoneNumber("0791234567");
    	court.setAddress(addressService.findByaddressID(1l));
    	courtService.save(court);
    	
    	//User for authentication
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
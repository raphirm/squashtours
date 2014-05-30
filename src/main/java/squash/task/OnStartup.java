package squash.task;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.Resource;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.deser.DataFormatReaders.Match;

import squash.model.Address;
import squash.model.Court;
import squash.model.Dates;
import squash.model.Group;
import squash.model.Satz;
import squash.model.Spiel;
import squash.model.User;
import squash.service.AddressService;
import squash.service.CourtService;
import squash.service.SpielService;
import squash.service.UserService;
import squash.util.DateStatus;
import squash.util.MatchStatus;

@Component
public class OnStartup implements ApplicationListener<ContextRefreshedEvent> {
	@Resource
	private AddressService addressService;
	
	@Resource
	private CourtService courtService;
	
	@Resource
	private UserService userservice;
	@Resource
	private SpielService spielService;
	
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
    	court.setAddress(addressService.findOne(1l));
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
        
        User user1 = new User();
    	ArrayList<Group> groups1 = new ArrayList<Group>();
    	Group group1 = new Group();
    	group1.setName("user");
    	groups1.add(group1);
    	user1.seteMail("raph2ael@marques.com");
    	user1.setUsername("user");
    	user1.setPassword("user");
    	user1.setGroups(groups1);
    	user1.setAddressID(address);
        userservice.save(user1);
        
        
        Spiel match = new Spiel();
        match.setPlayer1(user);
        match.setPlayer2(user1);
        List<Dates> dates = new ArrayList<Dates>();
        Dates date = new Dates();
        date.setDate(new Date());
        date.setOrigin(user);
        date.setSpiel(match);
        date.setStatus(DateStatus.NEW);
        dates.add(date);
        match.setDate(dates);
        List<Satz> sets = new ArrayList<Satz>();
        Satz set = new Satz();
        set.setMatch(match);
        set.setPlayer1Points(10);
        set.setPlayer2Points(0);
        sets.add(set);
        match.setSets(sets);
        match.setPlayer1Status(MatchStatus.win);
        match.setPlayer2Status(MatchStatus.loss);
        spielService.save(match);
        
		
	}
	
}
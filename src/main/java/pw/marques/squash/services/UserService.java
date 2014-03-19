package pw.marques.squash.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.security.authentication.encoding.PasswordEncoder;
import org.springframework.security.authentication.encoding.ShaPasswordEncoder;




import pw.marques.squash.dao.DaoFactory;
import pw.marques.squash.domain.Group;
import pw.marques.squash.domain.User;

@Component("userService")
public class UserService  {

	private PasswordEncoder encoder = new ShaPasswordEncoder();
	
	@Autowired
	private DaoFactory daoFactory;
	
	
	public User findByUsername(String username) {
		User user = daoFactory.getDao(User.class).findOneByCriteria(Restrictions.eq("username",  username));
		return user;
	}
	public List<User> getAllUsers(){
		List<User> users = daoFactory.getDao(User.class).findAll();
		return users;
		
	}
	
	@Transactional
	public void installNewUser(User user) {
		user.setSalt( String.valueOf(System.currentTimeMillis()) );
		user.setPassword(encoder.encodePassword(user.getPassword(), user.getSalt()));
		daoFactory.getDao(User.class).save(user);
	}

	@Transactional
	public void installNewGroup(Group group) {
		daoFactory.getDao(Group.class).save(group);
	}
	@Transactional
	public Group findByGroupname(String groupname){
		Group group = daoFactory.getDao(Group.class).findOneByCriteria(Restrictions.eq("name", groupname));
		return group;
		
	}


}

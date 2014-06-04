package squash.service;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import squash.controller.HomeController;
@Component("userDetailsService")
public class UserDetailServiceImpl implements UserDetailsService{
	static final Logger logger = Logger.getLogger(HomeController.class);  
	@Resource
    private UserService userservice;	
	@Override
	public UserDetails loadUserByUsername(String email)
			throws UsernameNotFoundException {
		
			squash.model.User result = userservice.findByEMail(email);

			return result;
	}

}

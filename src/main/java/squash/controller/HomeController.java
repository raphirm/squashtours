package squash.controller;


import java.io.Console;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.Logger;  
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import squash.model.User;
import squash.service.UserService;


@Controller
@RequestMapping("/")
public class HomeController {
	static final Logger logger = Logger.getLogger(HomeController.class);  
	@Resource
	private UserService userservice;
	

	@RequestMapping(value={"/", "/index.html"})
	public String index() {
		return "home/index";
	}
	@RequestMapping(value={"/about.html"})
	public String about() {
		return "home/about";
	}
	@RequestMapping(value={"/private/{id}"})
	public String privates(Model model , @PathVariable("id") long id){
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		String username;
		if (principal instanceof User) {
		   username = ((User)principal).getUsername();
		} else {
		  username = principal.toString();
		}
		User iduser = userservice.findOne(id);
		model.addAttribute("user", iduser);
		if (iduser!=null){
			if(iduser.getUsername().equals(username)){
				return "home/private";
				
			}
			else{
				return "redirect:/index.html?accessdenied";
			}
		}else{
			return "redirect:/index.html?accessdenied";
		}
		
		
	}
	




}
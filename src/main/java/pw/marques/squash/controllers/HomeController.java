package pw.marques.squash.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pw.marques.squash.domain.Address;
import pw.marques.squash.domain.User;
import pw.marques.squash.services.UserService;


@Controller
public class HomeController extends AbstractController {
	@Autowired
	private UserService userService;
	@RequestMapping(value={"/", "/index.html"})
	public String index() {
		log.info("/index.htm ");
		return "home/index";
	}
	@RequestMapping("/user")
	public ModelAndView listUser() {
		
		List<User> users = userService.getAllUsers();
		log.info("/index.htm ");
		return new ModelAndView("user/index", "user", users);
	}
	@RequestMapping("/protected.html")
	public String protectedPage() {
		log.info("/protected.htm ");
		return "home/protected";
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.GET)
	public String registerPage(){
		log.info("/register.html ");
		return "home/register";
		
	}
	
	@RequestMapping(value = "/register.html", method = RequestMethod.POST)
	public String register(@RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value="Street", defaultValue="No Street") String street){
		log.info("/register.html ");
		return "redirect:index.html";
		 
	}
	@RequestMapping(value = "/accessdenied.html")
	public String accessdenied(){
		log.info("/register.html ");
		return "redirect:index.html?accessdenied";
		 
	}
}

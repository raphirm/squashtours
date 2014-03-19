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
	@RequestMapping(value={"/about.html"})
	public String about() {
		log.info("/about.htm ");
		return "home/about";
	}
	
	@RequestMapping("/protected.html")
	public String protectedPage() {
		log.info("/protected.htm ");
		return "home/protected";
	}
	

	

	
	
}

package squash.controller;


import java.io.Console;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.annotation.Resource;

import org.apache.log4j.BasicConfigurator;  
import org.apache.log4j.Logger;  
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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




}
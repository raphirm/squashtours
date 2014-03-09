package pw.marques.squash.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import pw.marques.squash.domain.Address;


@Controller
public class HomeController extends AbstractController {
	
	@RequestMapping(value={"/", "/index.html"})
	public String index() {

		log.info("/index.htm ");
		return "home/index";
	}
	@RequestMapping("/protected.html")
	public String protectedPage() {
		log.info("/protected.htm ");
		return "home/protected";
	}
	@RequestMapping("/api/address")
	public ModelAndView apiAdress() {
		Address address = new Address();
		address.setStreet1("BLABLA");
		return new ModelAndView("api/api", "api", address);
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
}

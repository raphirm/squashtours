package pw.marques.squash.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
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
}

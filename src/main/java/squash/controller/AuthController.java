package squash.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthController {

	@RequestMapping(method=RequestMethod.GET, value="register.html")
	private String register() {
		return "home/register";
	}
	@RequestMapping(method=RequestMethod.POST, value="register.html")
	private String registerProc(Model model) {
		
		return "home/register";
	}
}

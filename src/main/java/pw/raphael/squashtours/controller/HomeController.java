package pw.raphael.squashtours.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.*;
import pw.raphael.squashtours.models.LoginModel;

@Controller
public class HomeController {

	@RequestMapping(value="/")
	public ModelAndView test(HttpServletResponse response) throws IOException{
		return new ModelAndView("home");
	}
        @RequestMapping(value="login")
        public ModelAndView login(HttpServletResponse response) throws IOException{
            LoginModel bla = new LoginModel();
            bla.setBla(1);
            return new ModelAndView("login", "login", bla);
            
        }
}

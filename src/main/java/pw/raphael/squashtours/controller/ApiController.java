package pw.raphael.squashtours.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.*;
import pw.raphael.squashtours.models.LoginModel;
import pw.raphael.squashtours.models.RootApiModel;

@Controller
public class ApiController {

	@RequestMapping(value="api")
	public ModelAndView test(HttpServletResponse response, Model model) throws IOException{
                RootApiModel types = new RootApiModel();
                types.addType("Test1");
                types.addType("Test2");
                return new ModelAndView("api", "api", types);
	}
        @RequestMapping(value="api/login")
        public ModelAndView login(HttpServletResponse response) throws IOException{
            LoginModel bla = new LoginModel();
            bla.setBla(1);
            return new ModelAndView("api", "api", bla);
            
        }
}

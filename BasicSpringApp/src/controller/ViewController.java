package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ViewController {
	
	@RequestMapping("/hello")
	public ModelAndView getHelloView(ModelAndView mav) {
		mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello World!");
		return mav;
	}

	@RequestMapping("/contact_manager")
	public ModelAndView getContactView(ModelAndView mav) {
		mav = new ModelAndView("contact_manager");
		return mav;
	}
}

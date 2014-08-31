package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import domain.User;

/*
 * ViewController
 * Controller for resolving views
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
@Controller
public class ViewController {
	
	/*
	 * URL mapping for /hello
	 * 
	 * @param mav	Empty ModelAndView object
	 * @return		Hello view
	 */
	@RequestMapping("/hello")
	public ModelAndView getHelloView(ModelAndView mav) {
		mav = new ModelAndView("hello");
		mav.addObject("msg", "Hello World!");
		return mav;
	}
	
	/*
	 * URL mapping for /user_registration
	 * 
	 * @param mav	Empty ModelAndView object
	 * @return		User registration view
	 */
	@RequestMapping("/user_registration")
	public ModelAndView getUserRegistrationView(ModelAndView mav,
			User user)
	{
		mav = new ModelAndView("user_registration");
		user = new User();
		mav.addObject("User",user);
		return mav;
	}
	
	/*
	 * URL mapping for /contact_manager
	 * 
	 * @param mav	Empty ModelAndView object
	 * @return		Contact manager view
	 */
	@RequestMapping("/contact_manager")
	public ModelAndView getContactView(ModelAndView mav) {
		mav = new ModelAndView("contact_manager");
		return mav;
	}
}

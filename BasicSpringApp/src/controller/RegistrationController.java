package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dao.DatabaseService;
import domain.User;

/*
 * ReqistrationController
 * Controller for handling the registration forms Ajax requests
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
@RestController
@RequestMapping("/registration")
public class RegistrationController {
	
	/*
	 * URL mapping for /registration/add_user
	 * 
	 * @param user	User object request parameter
	 * @return		Registration result
	 */
	@RequestMapping(value = "/add_user", method = RequestMethod.POST)
	public String addUser(User user) {
		DatabaseService dbs = new DatabaseService();
		if(dbs.addUser(user)) {
			return "Registration Successfull!";
		} else {
			return "Registration Failed.";
		}
	}
}

package controller;

import java.util.ArrayList;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import util.NameSplitter;
import validation.Validator;
import dao.DatabaseService;
import domain.Contact;
import domain.Query;

/*
 * ContactController
 * Controller for handling the contact managers Ajax requests
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
@RestController
@RequestMapping("/contact")
public class ContactController {
	
	/*
	 * URL mapping for /contact/search
	 * 
	 * @param query	Query object request parameter
	 * @return		Validation result
	 */
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Query query) {
		if (Validator.isValid(query.getQueryString())) {
			return "success";
		} else {
			return "failure";
		}
	}
	/*
	 * URL mapping for /contact/add
	 * 
	 * @param contact	Contact object request parameter
	 * @return			Result of insert
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(Contact contact) {
		ArrayList<String> contactFields = new ArrayList<>();
		contactFields.add(contact.getFirstName());
		contactFields.add(contact.getLastName());
		contactFields.add(contact.getPhoneNumber());
		contactFields.add(contact.getEmailAddr());
		
		if (Validator.isObjectValid(contactFields)) {
			DatabaseService dbs = new DatabaseService();
			dbs.createContact(contact);
			return "success";
		} else {
			return "failure";
		}
	}
	/*
	 * URL mapping for /contact/show_all
	 * 
	 * @return	List of contact names
	 */
	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public ArrayList<String> getAllContacts() {
		DatabaseService dbs = new DatabaseService();
		ArrayList<String> allContactNames = dbs.requestContactNames();
		
		return allContactNames;
	}
	
	/*
	 * URL mapping for /contact/display_contact
	 * 
	 * @param contactName	Contact name request parameter
	 * @return				List of ordered information on contact
	 */
	@RequestMapping(value = "/display_contact", method = RequestMethod.GET)
	public ArrayList<String> displayContact(String contactName) {
		DatabaseService dbs = new DatabaseService();
		String firstName = NameSplitter.firstName(contactName);
		String lastName = NameSplitter.lastName(contactName);
		ArrayList<String> contactInfo = dbs.requestContactInfo(firstName, lastName);
		return contactInfo;
	}
	/*
	 * URL mapping for contact/find_contacts
	 * 
	 * @param firstName	Contact name request parameter
	 * @return			List of matching contact names
	 */
	@RequestMapping(value = "/find_contacts", method = RequestMethod.GET)
	public ArrayList<String> findContacts(String firstName) {
		DatabaseService dbs = new DatabaseService();
		ArrayList<String> foundContacts = dbs
				.requestCertainContactNames(firstName);

		return foundContacts;
	}
	
	/*
	 * URL mapping for contact/delete_contact
	 * 
	 * @param contactEmail	Contact email address request parameter
	 * @return				Deletion result
	 */
	@RequestMapping(value = "/delete_contact", method = RequestMethod.POST)
	public String deleteContact(String contactEmail) {
		DatabaseService dbs = new DatabaseService();
		dbs.removeContact(contactEmail);
		
		return "contact deleted.";
	}

}

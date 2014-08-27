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

@RestController
@RequestMapping("/contact")
public class ContactController 
{	
	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String search(Query query)
	{
		if(Validator.isValid(query.getQueryString()))
		{
			return "success";	
		}
		else
		{
			return "failure";
		}
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addContact(Contact contact)
	{
		ArrayList<String> contactFields = new ArrayList<>();
		contactFields.add(contact.getFirstName());
		contactFields.add(contact.getLastName());
		contactFields.add(contact.getPhoneNumber());
		contactFields.add(contact.getEmailAddr());
		
		if(Validator.isObjectValid(contactFields))
		{
			DatabaseService dbs = new DatabaseService();
			dbs.createContact(contact);
			return "success";
		}
		else
		{
			return "failure";
		}
	}
	
	@RequestMapping(value = "/show_all", method = RequestMethod.GET)
	public ArrayList<String> getAllContacts()
	{
		DatabaseService dbs = new DatabaseService();
		ArrayList<String> allContactNames = dbs.requestContactNames();
		return allContactNames;
	}
	
	@RequestMapping(value = "/display_contact", method = RequestMethod.GET)
	public ArrayList<String> displayContact(String contactName)
	{
		DatabaseService dbs = new DatabaseService();
		String firstName = NameSplitter.firstName(contactName);
		ArrayList<String> contactInfo = dbs.requestContactInfo(firstName);

		return contactInfo;
	}
	
	@RequestMapping(value = "/find_contacts", method = RequestMethod.GET)
	public ArrayList<String> findContacts(String firstName)
	{
		DatabaseService dbs = new DatabaseService();
		ArrayList<String> foundContacts = dbs.requestCertainContactNames(firstName);
		
		return foundContacts;
	}
	
	@RequestMapping(value = "/delete_contact", method = RequestMethod.POST)
	public String deleteContact(String contactEmail)
	{
		DatabaseService dbs = new DatabaseService();
		dbs.removeContact(contactEmail);
		return "contact deleted.";
	}
	
}

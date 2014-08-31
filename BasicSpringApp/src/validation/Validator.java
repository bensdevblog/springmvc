package validation;

import java.util.ArrayList;

/*
 * Validator
 * Server side form validation class
 * 
 * @author Ben Hansen
 * http://www.bensdevblog.com
 */
public class Validator {
	
	/*
	 * Checks a string for validity
	 * 
	 * @param toValidate	String to validate
	 * @return				True if valid, false if otherwise
	 */
	public static boolean isValid(String toValidate) {
		if (!toValidate.isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	/*
	 * Checks a model object for validation
	 * 
	 * @param toValidate	ArrayList of objects attribute values
	 * @return				True if valid, false if otherwise
	 */
	public static boolean isObjectValid(ArrayList<String> toValidate) {
		for (int i = 0; i < toValidate.size(); i++) {
			if (toValidate.get(i).isEmpty()) {
				return false;
			}
		}
		return true;
	}
}

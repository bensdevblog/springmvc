package util;

/*
 * NameSplitter
 * Utility class for splitting contact names.
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
public class NameSplitter {
	
	/*
	 * @param fullName	Full name of contact
	 * @return	First name of contact
	 */
	public static String firstName(String fullName) {
		String[] splitName = splitName(fullName);
		return splitName[0];
	}
	
	/*
	 * @param fullName	Full name of contact
	 * @return Last name of contact
	 */
	public static String lastName(String fullName) {
		String[] splitName = splitName(fullName);
		return splitName[1];
	}
	
	/*
	 * @param fullName	Full name of contact
	 * @return	Array of words in contacts full name
	 */
	public static String[] splitName(String fullName) {
		return fullName.split(" ");
	}
}

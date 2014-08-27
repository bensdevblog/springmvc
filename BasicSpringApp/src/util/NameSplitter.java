package util;

public class NameSplitter 
{
	/*
	 * @param	Full name of contact
	 * @return	First name of contact
	 */
	public static String firstName(String fullName)
	{
		String[] splitName = fullName.split(" ");
		return splitName[0];
	}
}

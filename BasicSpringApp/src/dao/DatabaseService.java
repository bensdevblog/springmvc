package dao;

import java.util.ArrayList;
import java.sql.*;

import sec.Cryptography;
import domain.Contact;
import domain.User;
/*
 * DatabaseService
 * Performs database operations
 * 
 * @author	Ben Hansen
 * http://www.bensdevblog.com
 */
public class DatabaseService {
	
	//Connection info
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.1.101:3306/springmvc";
	static final String USER = "springmvc";
	static final String PASS = "password";
	Connection conn = null;

	//Open DB connection
	public DatabaseService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/*
	 * Adds a new user to the DB
	 * 
	 * @param user	User object to add
	 * @return 		True if successful, otherwise false
	 */
	public boolean addUser(User user) {
		Cryptography crypto = new Cryptography();
		try {
			byte[] salt = crypto.generateSalt();
			byte[] encrypted_pass = crypto.getEncryptedPassword(user.getPassword(), salt);
			PreparedStatement pst = conn
					.prepareStatement("INSERT INTO users (user_name, email_addr,"
							+ "phone_number, password, salt) VALUES (?,?,?,?,?)");

			pst.setString(1, user.getUserName());
			pst.setString(2, user.getEmail());
			pst.setString(3, user.getPhone());
			pst.setString(4, crypto.toHexString(encrypted_pass));
			pst.setString(5, crypto.toHexString(salt));
			pst.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		}
		return true;
	}
	/*
	 * Adds a new contact to the DB
	 * 
	 * @param contact	Contact object to add
	 */
	public void createContact(Contact contact) {
		try {
			PreparedStatement pst = conn
					.prepareStatement("INSERT INTO contacts (first_name, last_name,"
							+ "phone_number,email_addr) VALUES (?,?,?,?)");

			pst.setString(1, contact.getFirstName());
			pst.setString(2, contact.getLastName());
			pst.setString(3, contact.getPhoneNumber());
			pst.setString(4, contact.getEmailAddr());
			pst.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	/*
	 * Removes a contact from the DB
	 * 
	 * @param contactEmail	Contacts email address (unique identifier)
	 */
	public void removeContact(String contactEmail) {
		try {
			PreparedStatement pst = conn
					.prepareStatement("DELETE FROM contacts WHERE email_addr = ?");

			pst.setString(1, contactEmail);
			pst.executeUpdate();
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * Request a certain contacts information from DB
	 * 
	 * @param firstName	The first name of contact
	 * @param lastName	The last name of contact
	 * @return	The requested contacts information
	 */
	public ArrayList<String> requestContactInfo(String firstName, String lastName) {
		ArrayList<String> requestedContact = new ArrayList<>();

		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT first_name, last_name,"
							+ "phone_number,email_addr FROM contacts WHERE first_name = ?"
							+ " AND last_name = ?");

			pst.setString(1, firstName);
			pst.setString(2, lastName);
			
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				requestedContact.add(rs.getString(1));
				requestedContact.add(rs.getString(2));
				requestedContact.add(rs.getString(3));
				requestedContact.add(rs.getString(4));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return requestedContact;
	}
	/*
	 * Request all contact names from DB
	 * 
	 * @return	List of contacts first and last names
	 */
	public ArrayList<String> requestContactNames() {
		ArrayList<String> contactNames = new ArrayList<>();
		try {
			Statement st = conn.createStatement();
			ResultSet rs = st
					.executeQuery("SELECT first_name, last_name FROM contacts");
			while (rs.next()) {
				contactNames.add(rs.getString("first_name") + " "
						+ rs.getString("last_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contactNames;
	}

	/*
	 * Request certain contacts from DB
	 * 
	 * @param firstName	the first name of a contact
	 * @return	list of contacts matching param argument
	 */
	public ArrayList<String> requestCertainContactNames(String firstName) {
		ArrayList<String> contactNames = new ArrayList<>();
		try {
			PreparedStatement pst = conn
					.prepareStatement("SELECT first_name, last_name"
							+ " FROM contacts WHERE first_name = ?");
			pst.setString(1, firstName);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				contactNames.add(rs.getString("first_name") + " "
						+ rs.getString("last_name"));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return contactNames;
	}
}

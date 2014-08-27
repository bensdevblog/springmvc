package dao;

import java.util.ArrayList;
import java.sql.*;

import domain.Contact;

public class DatabaseService {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://192.168.1.101:3306/springmvc";

	static final String USER = "springmvc";
	static final String PASS = "password";

	Connection conn = null;

	public DatabaseService()
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
		    conn = DriverManager.getConnection(DB_URL,USER,PASS);
		} catch (Exception ex)
		{
			ex.printStackTrace();
		} 
	}
	
	public void createContact(Contact contact)
	{
		try
		{
			PreparedStatement pst = conn.prepareStatement("INSERT INTO contacts (first_name, last_name,"
					+ "phone_number,email_addr) VALUES (?,?,?,?)");
			
			pst.setString(1, contact.getFirstName());
			pst.setString(2, contact.getLastName());
			pst.setString(3, contact.getPhoneNumber());
			pst.setString(4, contact.getEmailAddr());
			pst.executeUpdate();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	
	public void removeContact(String contactEmail)
	{
		try
		{
			PreparedStatement pst = conn.prepareStatement("DELETE FROM contacts WHERE email_addr = ?");
			
			pst.setString(1,contactEmail);
			pst.executeUpdate();
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
	}
	
	public ArrayList<String> requestContactInfo(String contactName)
	{
		ArrayList<String> requestedContact = new ArrayList<>();
		
		try
		{
			PreparedStatement pst = conn.prepareStatement("SELECT first_name, last_name,"
					+ "phone_number,email_addr FROM contacts WHERE first_name = ?");
			
			pst.setString(1, contactName);
			ResultSet rs = pst.executeQuery();
			
			while(rs.next())
			{
				requestedContact.add(rs.getString(1));
				requestedContact.add(rs.getString(2));
				requestedContact.add(rs.getString(3));
				requestedContact.add(rs.getString(4));
			}
			
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		
		return requestedContact;
	}

	public ArrayList<String> requestContactNames() 
	{
		ArrayList<String> contactNames = new ArrayList<>();
		try
		{
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT first_name, last_name FROM contacts");
			while(rs.next())
			{
				contactNames.add(rs.getString("first_name") + " " + rs.getString("last_name"));
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return contactNames;
	}
	
	public ArrayList<String> requestCertainContactNames(String firstName) 
	{
		ArrayList<String> contactNames = new ArrayList<>();
		try
		{
			PreparedStatement pst = conn.prepareStatement("SELECT first_name, last_name"
					+ " FROM contacts WHERE first_name = ?");	//Don't forget whitespace before 'FROM'
			pst.setString(1, firstName);
			ResultSet rs = pst.executeQuery();
			while(rs.next())
			{
				contactNames.add(rs.getString("first_name") + " " + rs.getString("last_name"));
			}
		}catch (Exception ex)
		{
			ex.printStackTrace();
		}
		finally
		{
			try { if (conn != null) conn.close(); } catch (SQLException e) { e.printStackTrace(); }
		}
		return contactNames;
	}
}

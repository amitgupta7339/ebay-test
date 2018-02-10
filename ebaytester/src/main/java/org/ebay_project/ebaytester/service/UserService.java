package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.ebay_project.ebaytester.model.User;

public class UserService {
	
		Connection conn;

	public UserService() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
		 conn=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/ebaytest","root","root");  
			
			
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			try {
				conn.close();
			} 
			catch (Exception ee) {
				System.out.println("Connection close error");
			}
		}
	}

	public User userLogin(String user_email, String user_password) {
		User user = null;
		PreparedStatement preparedstmnt;
		try {
			preparedstmnt = conn
					.prepareStatement("select user_email from user_info where user_email = ? and user_password = ?");
			preparedstmnt.setString(1, user_email);
			preparedstmnt.setString(2, user_password);
			ResultSet rs = preparedstmnt.executeQuery();
			if (rs.next())
				user = getUserDetail(rs.getString("user_email"));

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}
	
	public String userRegister(User user)
	{
		
		PreparedStatement preparedstmnt;
		if(ValidateEmailId(user.getUser_email())==0)
		{
			try {
			 preparedstmnt = conn.prepareStatement(
					"INSERT INTO `user_info`(`first_name`,`last_name`,`user_email`,`user_password`) VALUES (?,?,?,?)");
			preparedstmnt.setString(1,user.getFirst_name());
			preparedstmnt.setString(2,user.getLast_name());
			preparedstmnt.setString(3,user.getUser_email());
			preparedstmnt.setString(4,user.getUser_password());
			int success = preparedstmnt.executeUpdate();
			if (success == 1)
				return "Registration Successful";

		} catch (Exception e) {
			System.out.println("Exception raised!!" + e);
		}

		

		}
		return null;
	}
	
	public int ValidateEmailId(String user_email) {
		try {
			
			PreparedStatement stmt = conn.prepareStatement("Select user_email from user_info where user_email LIKE ?");
			stmt.setString(1, user_email);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	
	public User getUserDetail(String user_email) {

		User user = null;
		String sql = "select * from user_info where user_email = ?";
		PreparedStatement preparedstatement;
		
		try {
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setString(1, user_email);
			ResultSet rs = preparedstatement.executeQuery();

			if (rs.next()) {
				user = new User();
				user.setFirst_name(rs.getString(1));
				user.setLast_name(rs.getString(2));
				user.setUser_email(rs.getString(3));
				user.setUser_password(rs.getString(4));
				
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		System.out.println("GetUserProfile !");
		return user;
	}
}
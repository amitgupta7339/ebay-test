package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.ebay_project.ebaytester.model.Payment;

public class WalletPaymentService {
	Connection con;
	Statement stmt;
	ResultSet rs;
	Payment pay;
	double walletAmount;
	public String result;

	public WalletPaymentService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			stmt = con.createStatement();
			if (con.isClosed() == false)
				System.out.println("Database connection successful");
			else
				System.out.println("Database connection Failed");

		} catch (SQLException e) {
			System.out.println("Error");
			e.printStackTrace();
		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

	}

	// Validation Logic for Card details checking while adding money to wallet
	public String Validation(double walletAmount, String card_number, String cvv, String ex_month, String ex_year, int user_id)
	{
		try {
			//System.out.println("Inside Validation");

			this.walletAmount=walletAmount;
			String ex_date=ex_month+"/"+ex_year;
			pay=cardDetailsValidation(card_number,cvv,ex_date);
			if(pay==null)
			{
				result="Invalid card details.";
				System.out.println("Invalid card details");
				return result;
			}
			else
			{
//				String query = "SELECT * FROM cardDetails"
				if(pay.getBalance() >= walletAmount)
				{
					String query = "SELECT wallet_balance FROM user WHERE user_id = ?";
					PreparedStatement pstmt = con.prepareStatement(query);
					pstmt.setInt(1, user_id);
					rs = pstmt.executeQuery();

					if(rs.next())
					{
						double updateCardBalance = pay.getBalance() - walletAmount;
						walletAmount += rs.getDouble("wallet_balance") ;
						query = "UPDATE user SET wallet_balance = ? WHERE user_id = ?";
						pstmt = con.prepareStatement(query);
						pstmt.setDouble(1, walletAmount);
						pstmt.setInt(2, user_id);

						int result1 = pstmt.executeUpdate();

						if(result1>0)
						{
							query = "UPDATE cardDetails SET balance = ? WHERE card_number=?";
							pstmt = con.prepareStatement(query);
							pstmt.setDouble(1, updateCardBalance);
							pstmt.setString(2, card_number);
							result1 = pstmt.executeUpdate();

							if(result1 > 0)
							{
								result = "Successfully added balance to your wallet";
								return result;
							}

						}

					}
				}
			}
	}
		catch (SQLException e) 
		{		 
			e.printStackTrace();
			result = "Inside line 99 Wallet Payment service";
			return result;
	 	}
	 result = "Failed to add balance to your wallet";
	 return result;

}

	public Payment cardDetailsValidation(String card_number1, String cvv1, String ex_date1) {
		try {
			// System.out.println("inside cardDetailsValidation");
			String card_number, cvv, ex_date;
			String query = "select * from cardDetails where card_number=? and cvv=? and ex_date=?; ";
			PreparedStatement pstmt = con.prepareStatement(query);
			pstmt.setString(1, card_number1);
			pstmt.setString(2, cvv1);
			pstmt.setString(3, ex_date1);
			rs = pstmt.executeQuery();
			pay = null;
			if (rs.next()) {
				System.out.println("user card details: ");
				System.out.println(rs.getString("card_number") + " " + rs.getString("cvv") + " "
						+ rs.getString("ex_date") + " " + rs.getInt("balance"));

				pay = new Payment(rs.getString("card_number"), rs.getString("cvv"), rs.getString("ex_date"),
						rs.getInt("balance"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
}
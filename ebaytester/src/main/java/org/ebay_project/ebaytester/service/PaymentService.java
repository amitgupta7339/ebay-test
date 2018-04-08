package org.ebay_project.ebaytester.service;

import java.sql.*;

import org.ebay_project.ebaytester.model.Payment;

public class PaymentService {
	Connection con;
	Statement stmt;
	ResultSet rs;
	Payment pay;
	int product_id;
	int buyquantity;
	public String result;
	String deal;
	TransactionService t = new TransactionService();

	public PaymentService() {
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

	public boolean Validation(int user_id, int product_id, int buy_quantity, String card_number, String cvv,
			String ex_month, String ex_year) {
		try {
			// System.out.println("Inside Validation");
			buyquantity = buy_quantity;
			this.product_id = product_id;
			String ex_date = ex_month + "/" + ex_year;
			pay = cardDetailsValidation(card_number, cvv, ex_date);
			if (pay == null) {
				result = "Invalid card details.";
				System.out.println("Invalid card details");
				return false;
			} else {
				String query = "select * from product where product_id='" + product_id + "';";
				rs = stmt.executeQuery(query);
				float price = 0;
				int discount = 0;
				while (rs.next()) {
					price = rs.getInt("product_price");
					discount = rs.getInt("product_discount");
					price = price - (price * discount) / 100;
					price = price * buy_quantity;

					break;
				}
				if (updateBalance(pay, (int) price)) {
					result = t.enterTransaction(user_id, product_id, buy_quantity);
					return true;
				}

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
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

	public boolean updateBalance(Payment pay, int price) {
		// System.out.println("Inside updateBalance");
		if (quantitycheck(pay, price)) {
			if (!cardWithdraw(pay, price)) {
				result = "Insufficient balance in account.";
				System.out.println("product price is " + price);
				System.out.println("user balance is " + pay.getBalance());
				System.out.println("Insufficient balance");
				return false;
			} else
				return true;
		} else
			return false;
	}

	public boolean quantitycheck(Payment pay, int price) {
		try {
			String query = "select * from product where product_id='" + product_id + "';";

			rs = stmt.executeQuery(query);

			int quantity = 0;
			if (rs.next()) {
				quantity = rs.getInt("product_available_quantity");
				//deal = rs.getString("deal");
			}
			/*
			 * if(deal.equals("b1g1")) buyquantity=2*buyquantity; if(deal.equals("b2g1"))
			 * buyquantity=buyquantity+(buyquantity/2); if(deal.equals("b3g1"))
			 * buyquantity=buyquantity+(buyquantity/3);
			 */
			if (quantity >= buyquantity) {
				return true;
				/*
				 * query="update product set product_available_quantity="+(quantity-buyquantity)
				 * +" where product_id='"+product_id+"';"; stmt.execute(query);
				 * query="select product_sold_quantity from product where product_id='"
				 * +product_id+"';"; rs=stmt.executeQuery(query); if(rs.next())
				 * quantity=rs.getInt("product_sold_quantity");
				 * query="update product set product_sold_quantity="+(quantity+buyquantity)
				 * +" where product_id='"+product_id+"';"; stmt.execute(query);
				 */

			} else {
				result = "Seller doesnot have the desired quantity";
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

	}

	public boolean cardWithdraw(Payment pay, int price) {
		// System.out.println("Inside cardWithdraw");
		// System.out.println(pay.getCard_number());
		if (pay.getBalance() >= price) {
			try {
				System.out.println("Users current balance before buying product is " + pay.getBalance());
				System.out.println("Product price is " + price);
				int balance = pay.getBalance() - price;
				String query = "update cardDetails set balance= ? where card_number=?;";

				PreparedStatement pstmt = con.prepareStatement(query);

				pstmt.setInt(1, balance);
				pstmt.setString(2, pay.getCard_number());

				pstmt.execute();
				System.out.println("Remaining balance after buying product is  " + balance);

				query = "select balance from cardDetails where card_number=000000000000000;";
				rs = stmt.executeQuery(query);
				if (rs.next())
					balance = rs.getInt("balance");
				query = "update cardDetails set balance=" + (balance + price) + " where card_number=000000000000000;";
				stmt.execute(query);

				int quantity = 0;
				query = "select * from product where product_id='" + product_id + "';";
				rs = stmt.executeQuery(query);
				if (rs.next())
					quantity = rs.getInt("product_available_quantity");
				if (quantity >= buyquantity) {
					query = "update product set product_available_quantity=" + (quantity - buyquantity)
							+ " where product_id='" + product_id + "';";
					stmt.execute(query);
					query = "select product_sold_quantity from product where product_id='" + product_id + "';";
					rs = stmt.executeQuery(query);
					if (rs.next())
						quantity = rs.getInt("product_sold_quantity");
					query = "update product set product_sold_quantity=" + (quantity + buyquantity)
							+ " where product_id='" + product_id + "';";
					stmt.execute(query);
				}
				return true;
			} catch (SQLException e) {
				e.printStackTrace();
				return false;
			}
		} else
			return false;
	}

	public String buyWallet(int user_id, int product_id, int buy_quantity) {
		int buyquantity = buy_quantity;
		int wallet_balance = 0;
		int balance = 0;
		try {
			String query = "select wallet_balance from user where user_id=" + user_id + ";";
			stmt = con.createStatement();
			rs = stmt.executeQuery(query);

			if (rs.next())
				wallet_balance = rs.getInt("wallet_balance");// got user wallet balance
			query = "select balance from cardDetails where card_number=000000000000000;";
			rs = stmt.executeQuery(query);
			if (rs.next())
				balance = rs.getInt("balance"); // received ebay balance

			// calculate total price

			query = "select * from product where product_id='" + product_id + "';";
			rs = stmt.executeQuery(query);
			float price = 0;
			int discount = 0;
			if (rs.next()) {
				price = rs.getInt("product_price");
				discount = rs.getInt("product_discount");
				price = price - (price * discount) / 100;
				price = price * buy_quantity;
				price = (int) price;
			}
			// recieved total price;
			// quantity check
			// update wallet and ebay
			query = "select * from product where product_id='" + product_id + "';";

			rs = stmt.executeQuery(query);

			int quantity = 0;
			if (rs.next()) {
				quantity = rs.getInt("product_available_quantity");
				deal = rs.getString("deal");
			}
			/*
			 * if(deal.equals("b1g1")) buyquantity=2*buyquantity; if(deal.equals("b2g1"))
			 * buyquantity=buyquantity+(buyquantity/2); if(deal.equals("b3g1"))
			 * buyquantity=buyquantity+(buyquantity/3);
			 */
			if (quantity >= buyquantity && wallet_balance >= price) {
				query = "update product set product_available_quantity=" + (quantity - buyquantity)
						+ " where product_id='" + product_id + "';";
				stmt.execute(query);
				query = "select product_sold_quantity from product where product_id='" + product_id + "';";
				rs = stmt.executeQuery(query);
				if (rs.next())
					quantity = rs.getInt("product_sold_quantity");
				query = "update product set product_sold_quantity=" + (quantity + buyquantity) + " where product_id='"
						+ product_id + "';";
				stmt.execute(query);

				query = "update cardDetails set balance=" + (balance + price) + " where card_number=000000000000000;";
				stmt.execute(query);
				query = "update user set wallet_balance=" + (wallet_balance - price) + "where user_Id=" + user_id + ";";
				stmt.execute(query);
				String transaction = t.enterTransaction(user_id, product_id, buy_quantity);
				return "true" + "TXN000" + transaction;
				// update quantity
			} else {
				if (!(quantity >= buyquantity))
					return "Seller does not have desired quantity";
				else
					return "eBay wallet balance insufficient. Add money to eBay wallet or use different payment method.";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return "Transaction failure";
		}
	}
}
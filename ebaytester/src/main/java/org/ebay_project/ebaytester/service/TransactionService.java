package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ebay_project.ebaytester.model.Transaction;

public class TransactionService {
	Connection conn;
	Connection con;

	public TransactionService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			try {
				conn.close();
			} catch (Exception ee) {
				System.out.println("Connection close error");
			}
		}

	}
//==========================================get max transaction id and set=======================================//
	public Transaction generateId() {
		Transaction txn = new Transaction();

		PreparedStatement preparedstmnt;
		try {
			String Query = "SELECT txn_id FROM ebaytest.transaction where txn_id = (select max(txn_id)\n"
					+ "	  from ebaytest.transaction) group by txn_id;";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			ResultSet rs = preparedstmnt.executeQuery();
			if (rs.next()) {
				txn.setTxn_id(rs.getInt("txn_id") + 1);
			}

		} catch (Exception e) {
			System.out.println(e);
		}

		return txn;
	}
//=======================================end of code get max transaction id======================================//
	public List<Transaction> getAllTransactions(int user_id) {
		Transaction txn = new Transaction();
		List<Transaction> list = new ArrayList<Transaction>();

		PreparedStatement preparedstmnt;
		try {
			String query = "select * from transaction where user_id = ?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setInt(1, user_id);
			ResultSet rs = preparedstmnt.executeQuery();

			while (rs.next()) {

				txn.setTxn_surr_id(rs.getInt("Txn_surr_id"));
				txn.setTxn_id(rs.getInt("txn_id"));
				txn.setUser_id(rs.getInt("user_id"));
				txn.setSeller_id(rs.getInt("seller_id"));
				txn.setAmount(rs.getInt("amount"));
				txn.setProduct_id(rs.getInt("product_id"));
				txn.setQuantity(rs.getInt("quantity"));
				txn.setProduct_status(rs.getString("product_status"));
				txn.setOrder_date(rs.getDate("order_date"));
				txn.setShip_date(rs.getDate("ship_date"));
				txn.setReceive_date(rs.getDate("receive_date"));
				txn.setProduct_discount(rs.getInt("product_discount"));
				txn.setProduct_deal(rs.getString("product_deal"));
				txn.setUser_address(rs.getString("user_address"));
				txn.setSeller_address(rs.getString("seller_address"));

				list.add(txn);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return list;
	}

	public Transaction getATransaction(int user_id, int txn_id) {
		Transaction txn = new Transaction();

		PreparedStatement preparedstmnt;
		try {
			String query = "select * from transaction where user_id = ? and txn_id = ?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setInt(1, user_id);
			preparedstmnt.setInt(2, txn_id);

			ResultSet rs = preparedstmnt.executeQuery();

			if (rs.next()) {

				txn.setTxn_surr_id(rs.getInt("Txn_surr_id"));
				txn.setTxn_id(rs.getInt("txn_id"));
				txn.setUser_id(rs.getInt("user_id"));
				txn.setSeller_id(rs.getInt("seller_id"));
				txn.setAmount(rs.getInt("amount"));
				txn.setProduct_id(rs.getInt("product_id"));
				txn.setQuantity(rs.getInt("quantity"));
				txn.setProduct_status(rs.getString("product_status"));
				txn.setOrder_date(rs.getDate("order_date"));
				txn.setShip_date(rs.getDate("ship_date"));
				txn.setReceive_date(rs.getDate("receive_date"));
				txn.setProduct_discount(rs.getInt("product_discount"));
				txn.setProduct_deal(rs.getString("product_deal"));
				txn.setUser_address(rs.getString("user_address"));
				txn.setSeller_address(rs.getString("seller_address"));

			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return txn;
	}

	public String addTransactionDetail(int user_id, int txn_id, int product_id) {
		String opn = "";

		int seller_id = 0;

		PreparedStatement preparedstmnt;
		try {
			String Query = "SELECT user_id FROM ebaytest.product where product_id = ?;";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, product_id);
			ResultSet rs = preparedstmnt.executeQuery();
			if (rs.next()) {
				seller_id = rs.getInt("user_id");
			} else {
				System.console().writer().println("nahi aaya");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		String user_address = "";

		try {
			String Query = "SELECT * FROM user where user_id = ?;";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, user_id);
			ResultSet rs = preparedstmnt.executeQuery();
			if (rs.next()) {
				// user_address = rs.getString(columnIndex)
			} else {
				System.console().writer().println("nahi aaya");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		ResultSet rs;
		try {
			String query = "";

			query = "insert into product(sub_category_id,category_id,user_id,product_name,product_price,product_discount,product_condition,product_shipping, product_sold_quantity, product_img_url, product_available_quantity, product_description,product_rating, deal, brand, color, screen_size,processor, storage, warranty, operating_system, product_year, gender, applicable, material, clothing_size, style, warranty_type, card_class) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);

			preparedstmnt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return opn;
	}

	public String updateSellerTransaction(int user_id, int txn_id, int product_id, String product_status) {

		PreparedStatement preparedstmnt;
		int rs = 0;
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			String query = "UPDATE product set " + "product_status = ?" + "ship_date = ?"
					+ " where seller_id = ? and txn_id = ? and product_id = ?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setString(1, product_status);
			preparedstmnt.setDate(2, sqlDate);
			preparedstmnt.setInt(3, user_id);
			preparedstmnt.setInt(4, txn_id);
			preparedstmnt.setInt(5, product_id);

			rs = preparedstmnt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

		if (rs > 0) {
			return "Succesfull";
		}
		return "unsuccessfull";
	}

	public String updateUserTransaction(int user_id, int txn_id, int product_id, String product_status) {

		PreparedStatement preparedstmnt;
		int rs = 0;
		java.util.Date date = new java.util.Date();
		java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		try {
			String query = "UPDATE product set " + "product_status = ?" + "receive_date = ?"
					+ " where user_id = ? and txn_id = ? and product_id = ?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setString(1, product_status);
			preparedstmnt.setDate(2, sqlDate);
			preparedstmnt.setInt(3, user_id);
			preparedstmnt.setInt(4, txn_id);
			preparedstmnt.setInt(5, product_id);

			rs = preparedstmnt.executeUpdate();

		} catch (Exception e) {
			System.out.println(e);
		}

		if (rs > 0) {
			return "Succesfull";
		}
		return "unsuccessfull";
	}

	public String enterTransaction(int user_id, int product_id, int quantity) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			int txn_id = 0;
			int seller_id = 0;
			int amount = 0;
			int discount = 0;
			String product_status = "Order_placed";
			String product_deal = "";
			int deal_id = 0;
			String user_address = "";

			java.util.Date date = new java.util.Date();
			java.sql.Date order_date = new java.sql.Date(date.getTime());
			String query = "select max(txn_id) from transaction;";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next())
				txn_id = rs.getInt("max(txn_id)");
			txn_id = txn_id + 1;
			query = "select * from product where product_id=" + product_id + ";";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				seller_id = rs.getInt("user_id");
				amount = rs.getInt("product_price");
				discount = rs.getInt("product_discount");
			}
			query = "select * from user where user_id=" + user_id + ";";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				user_address = rs.getString("user_address") + " " + rs.getString("user_city") + " "
						+ rs.getString("user_state") + " " + rs.getInt("user_pincode");
			}

			PreparedStatement preparedstmnt;
			query = "insert into transaction(txn_id,user_id,seller_id,amount,product_id,quantity,product_status,order_date,product_discount,product_deal,user_address,deal_id) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setInt(1, txn_id);
			preparedstmnt.setInt(2, user_id);
			preparedstmnt.setInt(3, seller_id);
			preparedstmnt.setInt(4, amount);
			preparedstmnt.setInt(5, product_id);
			preparedstmnt.setInt(6, quantity);
			preparedstmnt.setString(7, product_status);
			preparedstmnt.setDate(8, order_date);
			preparedstmnt.setInt(9, discount);
			preparedstmnt.setString(10, product_deal);
			preparedstmnt.setString(11, user_address);
			preparedstmnt.setInt(12, deal_id);
			preparedstmnt.execute();
			conn.close();
			return txn_id + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "transaction entry failed";
		}
	}

	public String enterOneCartTransaction(int txn_id, int user_id, int product_id, int quantity) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");

			int seller_id = 0;
			int amount = 0;
			int discount = 0;
			String product_status = "Order_placed";
			String product_deal = "";
			int deal_id = 0;
			String user_address = "";

			java.util.Date date = new java.util.Date();
			java.sql.Date order_date = new java.sql.Date(date.getTime());

			String query = "select * from product where product_id=" + product_id + ";";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next()) {
				seller_id = rs.getInt("user_id");
				amount = rs.getInt("product_price");
				discount = rs.getInt("product_discount");
			}
			query = "select * from user where user_id=" + user_id + ";";
			rs = stmt.executeQuery(query);
			if (rs.next()) {
				user_address = rs.getString("user_address") + " " + rs.getString("user_city") + " "
						+ rs.getString("user_state") + " " + rs.getInt("user_pincode");
			}

			PreparedStatement preparedstmnt;
			query = "insert into transaction(txn_id,user_id,seller_id,amount,product_id,quantity,product_status,order_date,product_discount,product_deal,user_address,deal_id) values(?,?,?,?,?,?,?,?,?,?,?,?);";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(query);
			preparedstmnt.setInt(1, txn_id);
			preparedstmnt.setInt(2, user_id);
			preparedstmnt.setInt(3, seller_id);
			preparedstmnt.setInt(4, amount);
			preparedstmnt.setInt(5, product_id);
			preparedstmnt.setInt(6, quantity);
			preparedstmnt.setString(7, product_status);
			preparedstmnt.setDate(8, order_date);
			preparedstmnt.setInt(9, discount);
			preparedstmnt.setString(10, product_deal);
			preparedstmnt.setString(11, user_address);
			preparedstmnt.setInt(12, deal_id);
			preparedstmnt.execute();
			conn.close();
			return txn_id + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "transaction entry failed";
		}
	}

	public String enterCartTransaction(int user_id) {
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			String query = "select max(txn_id) from transaction;";
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			int txn_id = 0;
			if (rs.next())
				txn_id = rs.getInt("max(txn_id)");
			txn_id = txn_id + 1;
			query = "select user_id,product_id,quantity from cart where user_id=" + user_id + " and checked=1;";
			rs = stmt.executeQuery(query);

			while (rs.next()) {
				int userid = rs.getInt("user_id");
				int product_id = rs.getInt("product_id");
				int quantity = rs.getInt("quantity");
				enterOneCartTransaction(txn_id, userid, product_id, quantity);
			}
			con.close();
			return txn_id + "";
		} catch (Exception e) {
			e.printStackTrace();
			return "tranaction entry failed";
		}

	}

}

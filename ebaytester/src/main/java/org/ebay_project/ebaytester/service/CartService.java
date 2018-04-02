package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.ebay_project.ebaytester.model.Cart;

public class CartService {
	Connection conn;

	public CartService() {

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

	String Result = null;

	public List<Cart> getCartProductList(int user_id) {
		Cart C1;
		List<Cart> list = new ArrayList<>();
		PreparedStatement preparedstmnt;
		PreparedStatement preparedstmnt1;
		try {
			String Query="UPDATE cart SET checked=1 WHERE user_id=?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, user_id);
			preparedstmnt.executeUpdate();
			Query = "SELECT P.product_id ,P.product_price,P.product_available_quantity,P.product_discount,P.user_id,P.product_name,P.product_img_url,P.product_shipping,C.quantity FROM product AS P,cart AS C WHERE P.product_id=C.product_id AND C.user_id=?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, user_id);
			ResultSet rs = preparedstmnt.executeQuery();
			while (rs.next()) {
				C1 = new Cart();
				String query = "SELECT * FROM user WHERE user_id=? ";
				preparedstmnt1 = (PreparedStatement) conn.prepareStatement(query);
				preparedstmnt1.setInt(1, rs.getInt("user_id"));
				ResultSet rs1 = preparedstmnt1.executeQuery();
				if (rs1.next()) {
					C1.setUser_fname(rs1.getString("user_fname"));
					C1.setUser_lname(rs1.getString("user_lname"));
				}
				float price = rs.getInt("product_price");
				price = price - (price * rs.getInt("product_discount")) / 100;
				price = price * rs.getInt("quantity");
				C1.setProduct_id(rs.getInt("product_id"));
				C1.setProduct_price(price);
				C1.setProduct_available_quantity(rs.getInt("product_available_quantity"));
				C1.setProduct_name(rs.getString("product_name"));
				C1.setProduct_img_url(rs.getString("product_img_url"));
				C1.setProduct_shipping(rs.getString("product_shipping"));
				C1.setProduct_user_quantity(rs.getInt("quantity"));
				list.add(C1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}

	public String deleteproduct(int product_id, int user_id) {
		PreparedStatement preparedstmnt;
		try {
			String Query = "DELETE FROM cart WHERE product_id=? AND user_id=?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, product_id);
			preparedstmnt.setInt(2, user_id);
			int rs = preparedstmnt.executeUpdate();
			if (rs > 0) {
				Result = "Success";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Result;
	}

	public String updateProductQuantity(int product_id, int Quantity, int user_id) {
		PreparedStatement preparedstmnt;
		try {
			String Query = "UPDATE cart SET quantity=? WHERE product_id=? AND user_id=?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, Quantity);
			preparedstmnt.setInt(2, product_id);
			preparedstmnt.setInt(3, user_id);
			int rs = preparedstmnt.executeUpdate();
			if (rs > 0) {
				Result = "Success";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Result;
	}

	public String checkboxUpdate(int product_id, int user_id, int check) {
		PreparedStatement preparedstmnt;
		try {
			String Query = "UPDATE cart SET checked=? WHERE product_id=? AND user_id=?";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, check);
			preparedstmnt.setInt(2, product_id);
			preparedstmnt.setInt(3, user_id);
			int rs = preparedstmnt.executeUpdate();
			if (rs > 0) {
				Result = "Success";
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return Result;
	}

	public String addToCart(int product_id, int user_id, int quantity) {
		String str = "failure";
		PreparedStatement preparedstmnt;
		try {
			String Query = "INSERT into cart(user_id,product_id,quantity) values(?,?,?)";
			preparedstmnt = (PreparedStatement) conn.prepareStatement(Query);
			preparedstmnt.setInt(1, user_id);
			preparedstmnt.setInt(2, product_id);
			preparedstmnt.setInt(3, quantity);
			int rs = preparedstmnt.executeUpdate();
			if (rs > 0) {
				str = "Success";
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return str;
	}
}

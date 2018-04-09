package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ebay_project.ebaytester.model.Cart;
import org.ebay_project.ebaytester.model.Payment;
import org.ebay_project.ebaytester.service.PaymentService;

public class CartService {
	Connection conn;
	Payment pay = new Payment();
	PaymentService payser = new PaymentService();
	TransactionService t = new TransactionService();

	public CartService() {

		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);

		}
	}

	String Result = null;
//========================================GET CART PRODUCT LIST==================================================//
	public List<Cart> getCartProductList(int user_id) {
		Cart C1;
		List<Cart> list = new ArrayList<>();
		PreparedStatement preparedstmnt;
		PreparedStatement preparedstmnt1;
		try {
			String Query = "SELECT P.product_id ,P.product_price,P.product_available_quantity,P.product_discount,P.user_id,P.product_name,P.product_img_url,P.product_shipping,P.item_id,C.quantity FROM product AS P,cart AS C WHERE P.product_id=C.product_id AND C.user_id=?";
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
				C1.setItem_id(rs.getString("item_id"));
				list.add(C1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
//=======================================GET CART BUY PRODUCT LIST===============================================//
	public List<Cart> getCartBuyProductList(int user_id) {
		Cart C1;
		List<Cart> list = new ArrayList<>();
		PreparedStatement preparedstmnt;
		PreparedStatement preparedstmnt1;
		try {
			String Query = "SELECT P.product_id ,P.product_price,P.product_available_quantity,P.product_discount,P.user_id,P.product_name,P.product_img_url,P.product_shipping,C.quantity FROM product AS P,cart AS C WHERE P.product_id=C.product_id AND C.user_id=? AND C.checked=1";
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
//===============================================DELETE PRODUCT==================================================//
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
//===========================================UPDATE PRODUCT QUANTITY=============================================//
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
//===========================================CHECKBOX UPDATE=====================================================//
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
// ============================================ADD TO CART=======================================================//
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
//================================================BUY CART ITEMS=================================================//
	public String buyCartItems(int user_id, String card_number, String cvv, String ex_month, String ex_year) {
		try {
			String ex_date = ex_month + "/" + ex_year;
			pay = payser.cardDetailsValidation(card_number, cvv, ex_date);
			if (pay != null) {
				String query = "select P.product_price,C.quantity,P.product_discount from cart as C,product as P where C.user_id='"
						+ user_id + "' and C.product_id=P.product_id and C.checked=1;";
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(query);
				int total = 0;

				while (rs.next()) {
					int price = rs.getInt("product_price");
					int discount = rs.getInt("product_discount");
					price = price - (price * discount) / 100;
					total = total + (price * rs.getInt("quantity"));
				}
				if (total > pay.getBalance()) {
					return "insufficient balance";
				} else {
					System.out.println("Users current balance before buying product is " + pay.getBalance());
					System.out.println("Total buy price is " + total);
					int balance = pay.getBalance() - total;
					query = "update cardDetails set balance= ? where card_number=?;";
					PreparedStatement pstmt = conn.prepareStatement(query);
					pstmt.setInt(1, balance);
					pstmt.setString(2, pay.getCard_number());
					pstmt.execute();
					System.out.println("Remaining balance after buying product is  " + balance);
					query = "select balance from cardDetails where card_number=000000000000000;";
					rs = stmt.executeQuery(query);
					if (rs.next())
						balance = rs.getInt("balance");
					query = "update cardDetails set balance=" + (balance + total)
							+ " where card_number=000000000000000;";
					stmt.execute(query);
					String transaction = t.enterCartTransaction(user_id);
					query = "select product_id,quantity from cart where user_id='" + user_id + "' and checked=1;";
					rs = stmt.executeQuery(query);
					while (rs.next()) {
						Statement st = conn.createStatement();
						String update = "select * from product where product_id='" + rs.getInt("product_id") + "';";
						ResultSet t = st.executeQuery(update);
						if (t.next()) {
							Statement ss = conn.createStatement();
							int updatequantity = t.getInt("product_available_quantity");
							String u = "update product set product_available_quantity="
									+ (updatequantity - rs.getInt("quantity")) + " where product_id='"
									+ rs.getInt("product_id") + "';";
							ss.executeUpdate(u);

							int soldquantity = t.getInt("product_sold_quantity");
							u = "update product set product_sold_quantity=" + (soldquantity + rs.getInt("quantity"))
									+ " where product_id='" + rs.getInt("product_id") + "';";
							ss.executeUpdate(u);

						}
						String delete = "delete from cart where product_id='" + rs.getInt("product_id")
								+ "' and user_id='" + user_id + "' and checked=1;";
						Statement rr = conn.createStatement();
						rr.execute(delete);
					}
					return "true" + "TXN000" + transaction;
				}

			} else
				return "invalid card details";
		} catch (Exception e) {
			e.printStackTrace();
			return "transaction failure";
		}
	}
// ===============================================BUY CART WALLET================================================//
	public String buyCartWallet(int user_id) {
		int wallet_balance = 0;
		try {
			String query = "select wallet_balance from user where user_id=" + user_id + ";";
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(query);
			if (rs.next())
				wallet_balance = rs.getInt("wallet_balance");

			query = "select P.product_price,C.quantity,P.product_discount from cart as C,product as P where C.user_id='"
					+ user_id + "' and C.product_id=P.product_id and C.checked=1;";
			rs = stmt.executeQuery(query);
			int total = 0;
			while (rs.next()) {
				int price = rs.getInt("product_price");
				int discount = rs.getInt("product_discount");
				price = price - (price * discount) / 100;
				total = total + (price * rs.getInt("quantity"));
			}
			if (total > wallet_balance)
				return "Insufficient wallet balance";
			else {
				query = "update user set wallet_balance=" + (wallet_balance - total) + " where user_id=" + user_id
						+ ";";
				stmt.executeUpdate(query);

				int balance = 0;
				query = "select balance from cardDetails where card_number=000000000000000;";
				rs = stmt.executeQuery(query);
				if (rs.next())
					balance = rs.getInt("balance");
				query = "update cardDetails set balance=" + (balance + total) + " where card_number=000000000000000;";
				stmt.execute(query);

				String transaction = t.enterCartTransaction(user_id);
				query = "select product_id,quantity from cart where user_id='" + user_id + "' and checked=1;";
				rs = stmt.executeQuery(query);
				while (rs.next()) {
					Statement st = conn.createStatement();
					String update = "select * from product where product_id='" + rs.getInt("product_id") + "';";
					ResultSet t = st.executeQuery(update);
					if (t.next()) {
						Statement ss = conn.createStatement();
						int updatequantity = t.getInt("product_available_quantity");
						String u = "update product set product_available_quantity="
								+ (updatequantity - rs.getInt("quantity")) + " where product_id='"
								+ rs.getInt("product_id") + "';";
						ss.executeUpdate(u);

						int soldquantity = t.getInt("product_sold_quantity");
						u = "update product set product_sold_quantity=" + (soldquantity + rs.getInt("quantity"))
								+ " where product_id='" + rs.getInt("product_id") + "';";
						ss.executeUpdate(u);

					}
					String delete = "delete from cart where product_id='" + rs.getInt("product_id") + "' and user_id='"
							+ user_id + "' and checked=1;";
					Statement rr = conn.createStatement();
					rr.execute(delete);
				}
				System.out.println("success");
				return "true" + "TXN000" + transaction;
			}

		} catch (Exception e) {
			e.printStackTrace();
			return "Transaction failure";
		}
	}
}
//=====================================================END OF CODE===============================================//
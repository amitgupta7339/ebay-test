package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.ebay_project.ebaytester.model.Product;
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
	

	//list of all transactions id are returned by this method
	
		public List<Transaction> getAllTransactions(int user_id){
			Transaction txn = null ;
			List<Transaction> list = new ArrayList<Transaction>();
			
			PreparedStatement preparedstmnt;
			PreparedStatement preparedstmnt1;
			
			try {
				String query="select txn_id from transaction where user_id = ? group by txn_id";
				
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
				preparedstmnt.setInt(1, user_id);
				System.out.println(preparedstmnt);
				ResultSet rs = preparedstmnt.executeQuery();

				while (rs.next()) {
					txn = new Transaction();
					//txn.setTxn_surr_id(rs.getInt("Txn_surr_id"));
					txn.setTxn_id(rs.getInt("txn_id"));
					String str = "TXN0000"+rs.getInt("txn_id");
					txn.setString_txn_id(str);
					list.add(txn);
				}
				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			try {
				//select txn_id from transaction where user_id = 1 group by txn_id;
				String query1="select txn_id from transaction where seller_id = ? group by txn_id";
				
				preparedstmnt1=	(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt1.setInt(1, user_id);
				System.out.println(preparedstmnt1);
				ResultSet rs1 = preparedstmnt1.executeQuery();

				while (rs1.next()) {
					txn = new Transaction();
					//txn.setTxn_surr_id(rs.getInt("Txn_surr_id"));
					txn.setTxn_id(rs1.getInt("txn_id"));
					String str = "TXN0000"+rs1.getInt("txn_id");
					txn.setString_txn_id(str);
					list.add(txn);	

				}

				
				
			} catch (Exception e) {
				System.out.println(e);
			}
			
			return list;
		}
		
		
		//this returns all products associated with a transaction , buyer and seller are identified
		
		public List<Product> getATransaction(int user_id,int txn_id) {
			
			Product p1= null;
			List<Product> list = new ArrayList<>();
			PreparedStatement preparedstmnt;
			PreparedStatement preparedstmnt1;
			
			
			try {
				String query="select * from transaction where user_id = ? and txn_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);
				ResultSet rs1 = preparedstmnt.executeQuery();
				System.out.println(rs1);
				if(rs1.next()) {
					int status = 1;
					String query10="select * from product where product_id in (select product_id from transaction where user_id = ? and txn_id = ?)";
					preparedstmnt=	(PreparedStatement) conn.prepareStatement(query10);
					preparedstmnt.setInt(1, user_id);
					preparedstmnt.setInt(2, txn_id);
					
					ResultSet rs = preparedstmnt.executeQuery();
					while (rs.next()) {
						p1 = new Product();
						p1.setProduct_id(rs.getInt(1));
						
						String query11="select product_status from transaction where user_id = ? and txn_id = ? and product_id = ?";
						preparedstmnt=	(PreparedStatement) conn.prepareStatement(query11);
						preparedstmnt.setInt(1, user_id);
						preparedstmnt.setInt(2, txn_id);
						preparedstmnt.setInt(3, rs.getInt(1));
						ResultSet rs10 = preparedstmnt.executeQuery();
						String statusofproduct="";
						if(rs10.next()) {
							statusofproduct = rs10.getString("product_status");
						}
						
						p1.setSub_category_id(rs.getInt(2));
						p1.setCategory_id(rs.getInt(3));
						p1.setUser_id(rs.getInt(4));
						p1.setProduct_name(rs.getString(5));
						p1.setProduct_price(rs.getInt(6));
						p1.setProduct_discount(rs.getInt(7));
						p1.setProduct_condition(rs.getString(8));
						p1.setProduct_shipping(rs.getString(9));
						p1.setProduct_sold_quantity(rs.getInt(10));
						p1.setProduct_img_url(rs.getString(11));
						p1.setProduct_available_quantity(rs.getInt(12));
						p1.setProduct_description(rs.getString(13));
						p1.setProduct_rating(rs.getInt(14));
						p1.setItem_id(rs.getString(15));
						p1.setBrand(rs.getString(16));
						p1.setColor(rs.getString(17));
						p1.setScreen_size(rs.getString(18));
						p1.setProcessor(rs.getString(19));
						p1.setStorage(rs.getString(20));
						p1.setWarranty(rs.getString(21));
						p1.setOperating_system(rs.getString(22));
						p1.setProduct_year(rs.getInt(23));
						p1.setGender(rs.getString(24));
						p1.setApplicable(rs.getString(25));
						p1.setMaterial(rs.getString(26));
						p1.setClothing_size(rs.getString(27));
						p1.setStyle(rs.getString(28));
						p1.setWarranty_type(rs.getString(29));
						p1.setCard_class(rs.getString(30));
						// differentiation for seller or user
						p1.setA1(status);
						p1.setProduct_status(statusofproduct);

						list.add(p1);
					}
					
				}else {
					int status1 = 2;
					String query2="select * from product where product_id in (select product_id from transaction where seller_id = ? and txn_id = ?)";
					preparedstmnt1=	(PreparedStatement) conn.prepareStatement(query2);
					preparedstmnt1.setInt(1, user_id);
					preparedstmnt1.setInt(2, txn_id);
					//System.out.println("udhar");
					ResultSet rs4 = preparedstmnt1.executeQuery();

					while (rs4.next()) {
						p1 = new Product();
						p1.setProduct_id(rs4.getInt(1));
						
						String query15="select product_status from transaction where user_id = ? and txn_id = ? and product_id = ?";
						preparedstmnt=	(PreparedStatement) conn.prepareStatement(query15);
						preparedstmnt.setInt(1, user_id);
						preparedstmnt.setInt(2, txn_id);
						preparedstmnt.setInt(3, rs4.getInt(1));
						ResultSet rs15 = preparedstmnt.executeQuery();
						String statusofproduct="";
						if(rs15.next()) {
							statusofproduct = rs15.getString("product_status");
						}
						
						
						p1.setSub_category_id(rs4.getInt(2));
						p1.setCategory_id(rs4.getInt(3));
						p1.setUser_id(rs4.getInt(4));
						p1.setProduct_name(rs4.getString(5));
						p1.setProduct_price(rs4.getInt(6));
						p1.setProduct_discount(rs4.getInt(7));
						p1.setProduct_condition(rs4.getString(8));
						p1.setProduct_shipping(rs4.getString(9));
						p1.setProduct_sold_quantity(rs4.getInt(10));
						p1.setProduct_img_url(rs4.getString(11));
						p1.setProduct_available_quantity(rs4.getInt(12));
						p1.setProduct_description(rs4.getString(13));
						p1.setProduct_rating(rs4.getInt(14));
						p1.setItem_id(rs4.getString(15));
						p1.setBrand(rs4.getString(16));
						p1.setColor(rs4.getString(17));
						p1.setScreen_size(rs4.getString(18));
						p1.setProcessor(rs4.getString(19));
						p1.setStorage(rs4.getString(20));
						p1.setWarranty(rs4.getString(21));
						p1.setOperating_system(rs4.getString(22));
						p1.setProduct_year(rs4.getInt(23));
						p1.setGender(rs4.getString(24));
						p1.setApplicable(rs4.getString(25));
						p1.setMaterial(rs4.getString(26));
						p1.setClothing_size(rs4.getString(27));
						p1.setStyle(rs4.getString(28));
						p1.setWarranty_type(rs4.getString(29));
						p1.setCard_class(rs4.getString(30));
						// differentiation for seller or user
						p1.setA1(status1);
						p1.setProduct_status(statusofproduct);
						
						list.add(p1);
					}
				}
						
			
			} catch (Exception e) {
				System.out.println(e);
			}
			
			
			return list;
		}
		
		
		public String updateSellerTransaction(int user_id,int txn_id,int product_id) {

			PreparedStatement preparedstmnt;
			int rs=0;
			//java.util.Date date = new java.util.Date();
			//java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			String str = null;
			String product_status = "";
			
			try {
				String query1="select * from transaction"
						+ " where seller_id = ? and txn_id = ? and product_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt=(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);	
				preparedstmnt.setInt(3, product_id);

				//System.out.println("yaha aaya");
				//System.out.println(preparedstmnt);
				
				ResultSet rs1= preparedstmnt.executeQuery();
				if(rs1.next()) {
					//System.out.println("yaha aaya1");
					product_status = rs1.getString("product_status");
				}
			
			} catch (Exception e) {
			System.out.println(e);
			}
			
			//System.out.println(product_status);
			
			if(product_status.equals("goods_shipped")) {
				return "Order had been shipped already";
			}else if(product_status.equals("goods_received")){
				return "Goods had been delivered already";
			}else {
				
				try {
					String query="UPDATE transaction set "
							+ "product_status = ?,"
							+ "ship_date = CURDATE()"
							+ " where seller_id = ? and txn_id = ? and product_id = ?";
					preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
					preparedstmnt=(PreparedStatement) conn.prepareStatement(query);
					preparedstmnt.setString(1, "goods_shipped");
					//preparedstmnt.setDate(2, sqlDate);
					preparedstmnt.setInt(2, user_id);
					preparedstmnt.setInt(3, txn_id);	
					preparedstmnt.setInt(4, product_id);
					
					System.out.println(preparedstmnt);
					
					rs= preparedstmnt.executeUpdate();
				
					
				} catch (Exception e) {
				System.out.println(e);
				}
				
			}
			
			return "Product status changed to goods_shipped";
		}
		
		public String updateUserTransaction(int user_id,int txn_id,int product_id) {
			
			PreparedStatement preparedstmnt;
			int rs=0;
			java.util.Date date = new java.util.Date();
			java.sql.Date sqlDate=new java.sql.Date(date.getTime());
			String product_status = "";
			
			String deal = "";
			int deal_percentage = 100;
			
			try {
				String query1="select product_status from transaction"
						+ " where user_id = ? and txn_id = ? and product_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt=(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);	
				preparedstmnt.setInt(3, product_id);

				//System.out.println(preparedstmnt);
				
				ResultSet rs1= preparedstmnt.executeQuery();
				if(rs1.next()) {
					product_status = rs1.getString("product_status");
				}
			
			} catch (Exception e) {
			System.out.println(e);
			}
			
			if(product_status.equals("order_placed")) {
				return "Order yet to be shipped";
			}else if(product_status.equals("goods_received")){
				return "Goods had been delivered ";
			}else {
				try {
					
					String query="UPDATE transaction set "
							+ "product_status = ?,"
							+ "receive_date = CURDATE()"
							+ " where user_id = ? and txn_id = ? and product_id = ?";
					preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
					preparedstmnt=(PreparedStatement) conn.prepareStatement(query);
					preparedstmnt.setString(1, "goods_received");
					//preparedstmnt.setDate(2, sqlDate);
					preparedstmnt.setInt(2, user_id);
					preparedstmnt.setInt(3, txn_id);	
					preparedstmnt.setInt(4, product_id);

					rs= preparedstmnt.executeUpdate();
					
					
					int amount = 0, quantity = 0;

					String query5 = "select amount,quantity,product_deal from transaction"
							+ " where user_id = ? and txn_id = ? and product_id = ?";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query5);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query5);
					preparedstmnt.setInt(1, user_id);
					preparedstmnt.setInt(2, txn_id);
					preparedstmnt.setInt(3, product_id);

					ResultSet rs1 = preparedstmnt.executeQuery();
					if (rs1.next()) {
						amount = rs1.getInt("amount");
						quantity = rs1.getInt("quantity");
						deal = rs1.getString("product_deal");
					}
					
					//finding the percentage of deal if it is a part of discount deal
					String query12 = "select * from admin_deal where deal_name like ?";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query12);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query12);
					preparedstmnt.setString(1, deal);
					//System.out.println(preparedstmnt);
					ResultSet rs15 = preparedstmnt.executeQuery();
					if (rs15.next()) {
						//System.out.println("yaha");
						if(rs15.getInt("discount") != 0)
							//System.out.println("aaya");
							deal_percentage = rs15.getInt("discount");
					}
					
					
					String card_number = "";

					String query6 = "select card_number from user where user_id = (select user_id from product"
							+ " where product_id = ?)";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query6);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query6);
					preparedstmnt.setInt(1, product_id);

					ResultSet rs2 = preparedstmnt.executeQuery();
					if (rs2.next()) {
						card_number = rs2.getString("card_number");
					}
					
					System.out.println(amount+" "+quantity+" "+card_number+" "+deal_percentage);
					
					int transfer = 0;

					String query7 = "select balance from cardDetails where card_number like '0000000000000000'";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query7);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query7);
					ResultSet rs3 = preparedstmnt.executeQuery();
					if (rs3.next()) {
						transfer = rs3.getInt("balance");
					}

					transfer = transfer - (quantity * amount -( quantity * amount * deal_percentage)/100 ) ;

					String query8 = "UPDATE cardDetails set " + "balance = ?"
							+ " where card_number like '0000000000000000'";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query8);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query8);
					preparedstmnt.setInt(1, transfer);
					//System.out.println(preparedstmnt);
					int rs4 = preparedstmnt.executeUpdate();

					
					String query9 = "select balance from cardDetails where card_number like ?";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query9);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query9);
					preparedstmnt.setString(1, card_number);
					//System.out.println(preparedstmnt);
					ResultSet rs5 = preparedstmnt.executeQuery();
					if (rs5.next()) {
						//System.out.println("card ke andar");
						transfer = rs5.getInt("balance");
					}
									
					transfer = transfer + (quantity * amount -( quantity * amount * deal_percentage)/100 ) ;

					String query10 = "UPDATE cardDetails set balance = ? where card_number like ?";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query10);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query10);
					preparedstmnt.setInt(1, transfer);
					preparedstmnt.setString(2, card_number);
					//System.out.println(preparedstmnt);
					int rs6 = preparedstmnt.executeUpdate();

				} catch (Exception e) {
					System.out.println(e);
				}
			}
			return "Status updated to goods_received";
		}
		
		public String rateProduct(int user_id,int txn_id,int product_id,int stat, int rate) {
			
			int rating = 100;
			int rs = 0;
			PreparedStatement preparedstmnt;
			
			String product_status = "";
			
			try {
				String query1="select product_status from transaction"
						+ " where user_id = ? and txn_id = ? and product_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt=(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);	
				preparedstmnt.setInt(3, product_id);

				//System.out.println(preparedstmnt);
				
				ResultSet rs1= preparedstmnt.executeQuery();
				if(rs1.next()) {
					product_status = rs1.getString("product_status");
				}
			
			} catch (Exception e) {
			System.out.println(e);
			}
			
			if(product_status.equals("order_placed")) {
				
				return "Product order is placed but it is yet to be delivered";
				
			}else if(product_status.equals("goods_shipped")) {
				
				return "Goods shipped yet to be delivered";
				
			}else if(stat == 2) {
				
				return "Seller cannot rate the product";
				
			}else {
			
				try {	
					String query5 = "select * from transaction"
							+ " where user_id = ? and txn_id = ? and product_id = ?";
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query5);
					preparedstmnt = (PreparedStatement) conn.prepareStatement(query5);
					preparedstmnt.setInt(1, user_id);
					preparedstmnt.setInt(2, txn_id);
					preparedstmnt.setInt(3, product_id);
					
					//System.out.println(preparedstmnt);
					
					ResultSet rs1 = preparedstmnt.executeQuery();
					if (rs1.next()) {
						rating = rs1.getInt("product_rating");
					}
					System.out.println(rating);
				} catch (Exception e) {
					System.out.println(e);
				}
				
				if(rating != 0 ) {
					return "You already rated the product";
				}else {
					
					try {	
						String query="UPDATE transaction set "
								+ "product_rating = ?"
								+ " where user_id = ? and txn_id = ? and product_id = ?";
						preparedstmnt=	(PreparedStatement) conn.prepareStatement(query);
						preparedstmnt=(PreparedStatement) conn.prepareStatement(query);
						preparedstmnt.setInt(1, rate);
						preparedstmnt.setInt(2, user_id);
						preparedstmnt.setInt(3, txn_id);	
						preparedstmnt.setInt(4, product_id);
						
						//System.out.println(preparedstmnt);
			
						rs= preparedstmnt.executeUpdate();
						
						
						int raters = 0,pro_rate =0;
						
						String query6 = "select * from product"
								+ " where product_id = ?";
						preparedstmnt = (PreparedStatement) conn.prepareStatement(query6);
						preparedstmnt = (PreparedStatement) conn.prepareStatement(query6);
						preparedstmnt.setInt(1, product_id);
				
						//System.out.println(preparedstmnt);
						
						ResultSet rs1 = preparedstmnt.executeQuery();
						if (rs1.next()) {
							pro_rate = rs1.getInt("product_rating");
							raters = rs1.getInt("product_raters");
						}
						int new_raters = raters +1;
						
						int rate_update = 0;
						if(raters == 0) {
							rate_update = rate;
						}else {
							rate_update = (raters*pro_rate + rate)/ new_raters ;
						}
						
						String query7="UPDATE product set "
								+ "product_rating = ?,"
								+ "product_raters = ?"
								+ " where product_id = ?";
						preparedstmnt=	(PreparedStatement) conn.prepareStatement(query7);
						preparedstmnt=(PreparedStatement) conn.prepareStatement(query7);
						preparedstmnt.setInt(1, rate_update);
						preparedstmnt.setInt(2, new_raters);	
						preparedstmnt.setInt(3, product_id);
						
						//System.out.println(preparedstmnt);
			
						rs= preparedstmnt.executeUpdate();
						
					} catch (Exception e) {
						System.out.println(e);
					}
					
				}
			}
			return "Successfully rated the product";
		}
		
		public String checkTxn(int user_id, int txn_id) {
			PreparedStatement preparedstmnt;
			ArrayList<String> list = new ArrayList<>(); 
			ArrayList<Integer> rating = new ArrayList<>(); 
			
			try {
				String query1="select product_status,product_rating from transaction"
						+ " where user_id = ? and txn_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt=(PreparedStatement) conn.prepareStatement(query1);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);	


				//System.out.println(preparedstmnt);
				
				ResultSet rs1= preparedstmnt.executeQuery();
				while(rs1.next()) {
					list.add(rs1.getString("product_status"));
					rating.add(rs1.getInt("product_rating"));
				}
				
				String query2="select product_status from transaction"
						+ " where seller_id = ? and txn_id = ?";
				preparedstmnt=	(PreparedStatement) conn.prepareStatement(query2);
				preparedstmnt=(PreparedStatement) conn.prepareStatement(query2);
				preparedstmnt.setInt(1, user_id);
				preparedstmnt.setInt(2, txn_id);	


				//System.out.println(preparedstmnt);
				
				ResultSet rs2= preparedstmnt.executeQuery();
				while(rs2.next()) {
					list.add(rs2.getString("product_status"));
					rating.add(rs2.getInt("product_rating"));
				}
				
				if(list.isEmpty()) {
					return "Enter correct transaction id or this transaction id doesnot belong to you";
				}else {
					int count=0;
					for(int i=0;i<list.size();i++) {
						if(list.get(i).equals("goods_received") && rating.get(i)!=0) {
							count++;
						}
					}
					if(count == list.size()) {
						return "Goods recevied and had been rated";
					}
				}
				
			
			} catch (Exception e) {
			System.out.println(e);
			}
			return "Success";
			
		}
		
		

	public String enterTransaction(int user_id, int product_id, int quantity) {
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			int txn_id = 0;
			int seller_id = 0;
			int amount = 0;
			int discount = 0;
			String product_status = "order_placed";
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
			String product_status = "order_placed";
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

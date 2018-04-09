package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import org.ebay_project.ebaytester.model.MyOrder;

public class MyOrderService {
	Connection connection = null;

	public MyOrderService() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest", "root", "root");
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			try {
				connection.close();
			} catch (Exception ee) {
				System.out.println("Connection close error");
			}
		}
	}
// ============================================MY ORDER LIST=====================================================//
	public ArrayList<MyOrder> MyOrderList(int user_id) {
		MyOrder oo;
		ArrayList<MyOrder> list = new ArrayList<>();
		PreparedStatement preparedstmnt = null;
		try {
// =========================================QUERY FOR DEALS ON PRODUCT===========================================//		
			String query = "SELECT t.* , p.product_img_url, p.product_name,p.item_id, d.free_check,  u.user_fname, u.user_lname, u.user_email FROM transaction AS t, product as p, user as u, seller_deal AS d WHERE u.user_id=t.seller_id AND t.product_id=p.product_id AND d.deal_id=t.deal_id AND t.user_id=? ORDER BY t.txn_id DESC";
			// String query = "select * from transaction where user_id=?";
			preparedstmnt = connection.prepareStatement(query);
			preparedstmnt.setInt(1, user_id);
			ResultSet rs = preparedstmnt.executeQuery();
			while (rs.next()) {
				if(rs.getInt("deal_id")!=0)
				{
				oo = new MyOrder();
				oo.setTxn_surr_id(rs.getInt("Txn_surr_id"));
				String txnid = "TXN000" + rs.getInt("Txn_id");
				oo.setTxn_id(txnid);
				oo.setDeal_id(rs.getInt("deal_id"));
				oo.setFree_check(0);
				oo.setUser_id(rs.getInt("user_id"));
				oo.setSeller_id(rs.getInt("seller_id"));
				oo.setAmount(rs.getInt("amount"));
				oo.setProduct_id(rs.getInt("product_id"));
				oo.setQuantity(rs.getInt("quantity"));
				oo.setStatus(rs.getString("product_status"));
				oo.setOrder_date(rs.getDate("order_date"));
				oo.setShip_date(rs.getDate("ship_date"));
				oo.setReceive_date(rs.getDate("receive_date"));
				oo.setProduct_discount(rs.getInt("product_discount"));
				oo.setUser_address(rs.getString("user_address"));
				oo.setProduct_deal(rs.getString("product_deal"));
				oo.setProduct_img_url(rs.getString("product_img_url"));
				oo.setItem_id(rs.getString("item_id"));
				oo.setProduct_name(rs.getString("product_name"));
				oo.setUser_fname(rs.getString("user_fname"));
				oo.setUser_lname(rs.getString("user_lname"));
				oo.setUser_email(rs.getString("user_email"));
				list.add(oo);
				}
			}
// =========================================QUERY FOR DEAL ID=0 OR NO DEAL ON PRODUCT============================//
			query = "SELECT t.* , p.product_img_url, p.product_name,p.item_id,u.user_fname, u.user_lname, u.user_email FROM transaction AS t, product as p, user as u WHERE u.user_id=t.seller_id AND t.product_id=p.product_id AND t.user_id=? AND t.deal_id=? ORDER BY t.txn_id DESC";
			// String query = "select * from transaction where user_id=?";
			preparedstmnt = connection.prepareStatement(query);
			preparedstmnt.setInt(1, user_id);
			preparedstmnt.setInt(2,0);
		    rs = preparedstmnt.executeQuery();
			while (rs.next()) {
				oo = new MyOrder();
				oo.setTxn_surr_id(rs.getInt("Txn_surr_id"));
				String txnid = "TXN000" + rs.getInt("Txn_id");
				oo.setTxn_id(txnid);
				oo.setDeal_id(rs.getInt("deal_id"));
				//oo.setFree_check(rs.getInt("free_check"));
				oo.setUser_id(rs.getInt("user_id"));
				oo.setSeller_id(rs.getInt("seller_id"));
				oo.setAmount(rs.getInt("amount"));
				oo.setProduct_id(rs.getInt("product_id"));
				oo.setQuantity(rs.getInt("quantity"));
				oo.setStatus(rs.getString("product_status"));
				oo.setOrder_date(rs.getDate("order_date"));
				oo.setShip_date(rs.getDate("ship_date"));
				oo.setReceive_date(rs.getDate("receive_date"));
				oo.setProduct_discount(rs.getInt("product_discount"));
				oo.setUser_address(rs.getString("user_address"));
				oo.setProduct_deal(rs.getString("product_deal"));
				oo.setProduct_img_url(rs.getString("product_img_url"));
				oo.setItem_id(rs.getString("item_id"));
				oo.setProduct_name(rs.getString("product_name"));
				oo.setUser_fname(rs.getString("user_fname"));
				oo.setUser_lname(rs.getString("user_lname"));
				oo.setUser_email(rs.getString("user_email"));
				list.add(oo);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return list;
	}
}
//=======================================================END OF CODE=============================================//
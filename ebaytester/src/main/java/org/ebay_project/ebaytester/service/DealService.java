package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import org.ebay_project.ebaytester.model.DealSet;
import org.ebay_project.ebaytester.model.Deals;

public class DealService {

	Connection conn;

	public DealService() {
		
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
	public String uploadNewDeal(String deal_name,int free_products,int paid_products,int overall_discount)
	{
		ResultSet rs;
		//Deals deal = new Deal(deal_name,free_products,paid_products,overall_discount);
		
		String query = "insert into admin_deal(deal_name,buy_number,get_number,discount) values(?,?,?,?)";
		try {
		PreparedStatement stmt = conn.prepareStatement("select *from admin_deal where deal_name =?");
		stmt.setString(1,deal_name);
		rs = stmt.executeQuery();
		if(!rs.next()) {
		stmt = conn.prepareStatement(query);
		stmt.setString(1,deal_name);
		stmt.setInt(2,paid_products );
		stmt.setInt(3, free_products);
		stmt.setInt(4,overall_discount);
		stmt.execute();
		return "Succussfully Added";
		}
		else {
			return "Already Exist";
		}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		return "Failed To Add";
		}
		
	}
	
	public ArrayList<Deals> getAllDealsName()
	{
		String query = "select * from admin_deal";
		ArrayList<Deals> deals = new ArrayList<Deals>();
		try {
			PreparedStatement stmt = conn.prepareStatement(query);
			ResultSet rs = stmt.executeQuery();
			while(rs.next())
			{
				Deals deal = new Deals(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getInt(3),rs.getInt(5));
				deals.add(deal);
			}
			System.out.println("All deals retrieved");
			return deals;
			
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		//return deals;
	}
	
	public Deals getDealInfo(String deal_name)
	{
		try {
			PreparedStatement stmt = conn.prepareStatement("select * from admin_deal where deal_name = ?");
			stmt.setString(1,deal_name);
			ResultSet rs = stmt.executeQuery();
			if(rs.next())
			{
				Deals deal = new Deals(rs.getInt(1),rs.getString(2),rs.getInt(4),rs.getInt(3),rs.getInt(5));
				return deal;
			}
			return null;
		}catch(Exception e)
		{
			e.printStackTrace();
			return null;
		}
		
	}
	
	public String addProductsToDeal(DealSet deal)
	{
		try {
		PreparedStatement stmt = conn.prepareStatement("select MAX(deal_id) from seller_deal");
		ResultSet rs = stmt.executeQuery();
		int txn_id = 0;
		if(rs.next()) {
			txn_id = 1+rs.getInt(1);
			System.out.println(txn_id);		
		}else {
			txn_id = 1;
		}
		System.out.println(deal.getFree_products());
		String[] free_products = (deal.getFree_products()).split("\\|");
		String[] paid_products = (deal.getPaid_products()).split("\\|");
		System.out.println(deal.getFree_products());
		if(!(deal.getFree_products()=="")) {
		for(int i=0;i<free_products.length;i++)
		{
			System.out.println("Inside First Loop");
			stmt = conn.prepareStatement("insert into seller_deal(deal_id,seller_id,product_id,deal_name,start_date,end_date,free_check) values(?,?,?,?,?,?,?)");
			stmt.setInt(1,txn_id);
			stmt.setInt(2, deal.getSeller_id());
			System.out.println(free_products[i]);
			stmt.setInt(3, Integer.parseInt(free_products[i]));
			stmt.setString(4, deal.getDeal_name());
			stmt.setString(5, deal.getStart_date());
			stmt.setString(6, deal.getEnd_date());
			stmt.setInt(7,1);
			stmt.execute();
		}
		}
		if(!(deal.getPaid_products()=="")) {
		for(int i=0;i<paid_products.length;i++)
		{
			System.out.println("Inside Second Loop");
			System.out.println(paid_products[i]);
			stmt = conn.prepareStatement("insert into seller_deal(deal_id,seller_id,product_id,deal_name,start_date,end_date,free_check) values(?,?,?,?,?,?,?)");
			stmt.setInt(1,txn_id);
			stmt.setInt(2, deal.getSeller_id());
			stmt.setInt(3, Integer.parseInt(paid_products[i]));
			stmt.setString(4, deal.getDeal_name());
			stmt.setString(5, deal.getStart_date());
			stmt.setString(6, deal.getEnd_date());
			stmt.setInt(7,0);
			stmt.execute();
		}}
		return "SUCCESS";
	
		}catch(Exception e)
		{
			e.printStackTrace();
		return null;
		}
		//return "SUCCESS";
	}

}
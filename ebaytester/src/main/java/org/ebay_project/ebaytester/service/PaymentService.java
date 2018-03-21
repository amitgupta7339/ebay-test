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
	String deal="";
	int total_quantity=0;
	public  String result;
	public PaymentService() 
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");  
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/ebaytest","root","root");
			stmt=con.createStatement();  
			if(con.isClosed()==false)
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
	
	public boolean Validation(int  product_id,int buy_quantity,String card_number, String cvv, String ex_month, String ex_year) {
		try {
		//System.out.println("Inside Validation");
		buyquantity=buy_quantity;
		this.product_id=product_id;
		String ex_date=ex_month+"/"+ex_year;
		pay=cardDetailsValidation(card_number,cvv,ex_date);
		if(pay==null)
			{
			result="Invalid card details.";
			System.out.println("Invalid card details");
			return false;
			}
		else
		{
			String query="select * from product where product_id='"+product_id+"';";
			rs=stmt.executeQuery(query);
			float price=0,discount=0;
			
			while(rs.next())
			{
				price=rs.getInt("product_price");
				discount=rs.getInt("product_discount");
				deal=rs.getString("deal");
				price=price-(price*discount)/100;
				price=price*buy_quantity;
				checkdeal(deal);
				break;
			}
			if(updateBalance(pay,(int)price))
				return true;
			
		}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public void checkdeal(String deal_type)
	{
		switch(deal)
		{
		case "BUY 1 GET 1":
			total_quantity=buyquantity*2;
			break;
		default:
			total_quantity=buyquantity;
		}
	}
	
	public Payment cardDetailsValidation( String card_number1, String cvv1, String ex_date1)
	{
		try {
		//System.out.println("inside cardDetailsValidation");
		String card_number,cvv,ex_date;
		String query="select * from cardDetails where card_number=? and cvv=? and ex_date=?; ";
		PreparedStatement pstmt= con.prepareStatement(query);
		pstmt.setString(1, card_number1);
		pstmt.setString(2, cvv1);
		pstmt.setString(3, ex_date1);
		rs=pstmt.executeQuery();
		pay=null;
		if(rs.next())
		{
			System.out.println("user card details: ");
			System.out.println(rs.getString("card_number")+" "+rs.getString("cvv")+" "+rs.getString("ex_date")+" "+
					rs.getInt("balance"));
			
			pay=new Payment(rs.getString("card_number"),rs.getString("cvv"),rs.getString("ex_date"),
					rs.getInt("balance"));
		}
		
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pay;
	}
	
	public boolean updateBalance(Payment pay, int price)
	{
		//System.out.println("Inside updateBalance");
		if(quantitycheck(pay,price)) {
		if(!cardWithdraw(pay, price)) 
			{	
			result="Insufficient balance in account.";
			System.out.println("product price is "+price);
			System.out.println("user balance is "+pay.getBalance());
			System.out.println("Insufficient balance");
			return false;
			}
		else
			return true;
		}
		else
			return false;	
		}
	public boolean quantitycheck(Payment pay, int price)
	{	
		try {
		String query="select product_available_quantity from product where product_id='"+product_id+"';";
	
			rs=stmt.executeQuery(query);
		
		int quantity=0;
		if(rs.next())
			quantity=rs.getInt("product_available_quantity");
		if(quantity>=total_quantity)
		{	return true;
			/*query="update product set product_available_quantity="+(quantity-buyquantity)+" where product_id='"+product_id+"';";
		stmt.execute(query);
			query="select product_sold_quantity from product where product_id='"+product_id+"';";
			rs=stmt.executeQuery(query);
			if(rs.next())
				quantity=rs.getInt("product_sold_quantity");
		query="update product set product_sold_quantity="+(quantity+buyquantity)+" where product_id='"+product_id+"';";
		stmt.execute(query);*/
		
		}
		else {
			result="Seller doesnot have the desired quantity";
			return false;
		}
		}
		catch(SQLException e)
		{	
			e.printStackTrace();
			return false;
		}
		
	}
	public boolean cardWithdraw(Payment pay, int price)
	{
		//System.out.println("Inside cardWithdraw");
		//System.out.println(pay.getCard_number());
		if(pay.getBalance()>= price)
		{	
			try {
				System.out.println("Users current balance before buying product is "+pay.getBalance());
				System.out.println("Product price is "+price);
			int balance=pay.getBalance()-price;
			String query="update cardDetails set balance= ? where card_number=?;";
			
			PreparedStatement pstmt = con.prepareStatement(query);
			
			pstmt.setInt(1,balance);
			pstmt.setString(2, pay.getCard_number());
			
			pstmt.execute();
			System.out.println("Remaining balance after buying product is  "+balance);
			
			
			query="select balance from cardDetails where card_number=000000000000000;";
			rs=stmt.executeQuery(query);
			if(rs.next())
			balance=rs.getInt("balance");
			query="update cardDetails set balance="+(balance+price)+" where card_number=000000000000000;";
			stmt.execute(query);
			
			int quantity=0;
			query="select * from product where product_id='"+product_id+"';";
			rs=stmt.executeQuery(query);
			if(rs.next())
				quantity=rs.getInt("product_available_quantity");
			if(quantity>=total_quantity)
			{	
				query="update product set product_available_quantity="+(quantity-total_quantity)+" where product_id='"+product_id+"';";
			stmt.execute(query);
				query="select product_sold_quantity from product where product_id='"+product_id+"';";
				rs=stmt.executeQuery(query);
				if(rs.next())
					quantity=rs.getInt("product_sold_quantity");
			query="update product set product_sold_quantity="+(quantity+total_quantity)+" where product_id='"+product_id+"';";
			stmt.execute(query);
			}
			return true;
			}
		 catch (SQLException e) 
			{		 
			e.printStackTrace();
			return false;
		 	}
		}
		else
		return false;
	}
	

}

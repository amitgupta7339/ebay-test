package org.ebay_project.ebaytester.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.ebay_project.ebaytester.model.Product;

public class CategoryService {
	
	Connection connection;
		public CategoryService() {
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver");  
		 connection=DriverManager.getConnection(  "jdbc:mysql://localhost:3306/ebaytest","root","root");  
			
			
			System.out.println("connection !");
		} catch (Exception e) {
			System.out.println("Exception found" + e);
			try {
				connection.close();
			} 
			catch (Exception ee) {
				System.out.println("Connection close error");
			}
		}
	}
		
		public ArrayList<Product> getProducts(String category_name) {

			Product product;
			ArrayList<Product> productList = new ArrayList<Product>();
			String q1="select category_id from category where category_name=?";
			int category_id = 0;
			String q2 = "select * from product where category_id = ?";
			PreparedStatement preparedStatementq2;
			PreparedStatement preparedStatementq1;
			try {
				 preparedStatementq1 = connection.prepareStatement(q1);
				preparedStatementq1.setString(1,category_name);
				ResultSet rsq1 = preparedStatementq1.executeQuery();
				if(rsq1.next())
				{
					category_id = rsq1.getInt(1);
				}
				preparedStatementq2 = connection.prepareStatement(q2);
				preparedStatementq2.setInt(1,category_id);
			
				
				 ResultSet rsq2 =  preparedStatementq2.executeQuery();

				while(rsq2.next()) {
					product = new Product();
					product.setProduct_id(rsq2.getInt(1));
					product.setCategory_id(rsq2.getInt(2));
					product.setSub_category_id(rsq2.getInt(3));
					product.setProduct_name(rsq2.getString(4));
					product.setProduct_price(rsq2.getInt(5));
					product.setProduct_discount(rsq2.getInt(6));
					product.setProduct_condition(rsq2.getString(7));
					product.setProduct_shipping(rsq2.getString(8));
					product.setProduct_sold_quantity(rsq2.getInt(9));
					product.setProduct_quantity(rsq2.getInt(10));
					product.setProduct_available_quantity(rsq2.getInt(11));
					product.setProduct_img_url(rsq2.getString(12));
					product.setProduct_description(rsq2.getString(13));
					productList.add(product);
				}
				System.out.println(productList.size());

				for (Product l : productList) {
					System.out.println(l.getProduct_id() + "  " + l.getProduct_description());
				}

			} catch (SQLException e) {
				e.printStackTrace();
			}
			return productList;
		
		}
		
		

}

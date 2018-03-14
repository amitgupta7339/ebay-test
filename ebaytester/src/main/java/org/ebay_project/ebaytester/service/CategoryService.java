package org.ebay_project.ebaytester.service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.ebay_project.ebaytester.model.Category;
import org.ebay_project.ebaytester.model.Product;
import org.ebay_project.ebaytester.model.Subcategory;

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
		
		public ArrayList<Category> getAllCategory() {
			Category category;
			ArrayList<Category> categoryList = new ArrayList<Category>();
			String c1 = "select * from category";
			PreparedStatement preparedStatementc1;
			try {
				 preparedStatementc1 = connection.prepareStatement(c1);
				ResultSet rsq1 = preparedStatementc1.executeQuery();
				while(rsq1.next()) {
					category=new Category();
					category.setCategory_id(rsq1.getInt(1));
					category.setCategory_name(rsq1.getString(2));
					categoryList.add(category);
				}
				for (Category l : categoryList) {
					System.out.println(l.getCategory_name() );
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return categoryList;
		}
		
		public ArrayList<Subcategory> getAllSubCategory(String category_name) {
			int category_id=0;
			Subcategory subcategory;
			ArrayList<Subcategory> subcategoryList = new ArrayList<Subcategory>();
			String c1 = "select category_id from category where category_name=?";
			String c2= "select * from sub_category where category_id=?";
			PreparedStatement preparedStatementc1;
			PreparedStatement preparedStatementc2;
			try {
				 preparedStatementc1 = connection.prepareStatement(c1);
				 preparedStatementc1.setString(1,category_name);
				 ResultSet rsc1 = preparedStatementc1.executeQuery();
				if(rsc1.next())
				{
					category_id=rsc1.getInt(1);
				}
				preparedStatementc2 = connection.prepareStatement(c2);
				preparedStatementc2.setInt(1,category_id);
				ResultSet rsc2 = preparedStatementc2.executeQuery();
				while(rsc2.next())
				{
					subcategory= new Subcategory();
					subcategory.setSub_category_id(rsc2.getInt(1));
					subcategory.setCategory_id(rsc2.getInt(2));
					subcategory.setSub_category_name(rsc2.getString(3));
					subcategoryList.add(subcategory);
				}
				for (Subcategory l : subcategoryList) {
					System.out.println(l.getSub_category_name() );
				}
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
			return subcategoryList;
		}
		
		public String categoryAdded(String old_category_name,String new_category_name,String subcategory_name) {
			String categoryresult=null;
			try {
			if(!old_category_name.equals(""))
			{   int category_id = 0;
				PreparedStatement preparedStatementq1;
				PreparedStatement preparedStatementq2;
				PreparedStatement preparedStatementq3;
				preparedStatementq1 = connection.prepareStatement("select category_id from category where category_name=?");
				preparedStatementq1.setString(1,old_category_name);
				ResultSet rsq1 = preparedStatementq1.executeQuery();
				if(rsq1.next())
				{
					category_id = rsq1.getInt(1);
				}
				if(!subcategory_name.equals(""))
				{   preparedStatementq3 = connection.prepareStatement("select * from sub_category where sub_category_name=?");
				    preparedStatementq3.setString(1,subcategory_name);
				    ResultSet rsq3 = preparedStatementq3.executeQuery();
				    if(rsq3.next())
					{   categoryresult="Already_exist_sub_category";
						return categoryresult;
					}
				    else {
					preparedStatementq2 = connection.prepareStatement(
							 "INSERT INTO `sub_category`(`category_id`,`sub_category_name`) VALUES (?,?)");
					preparedStatementq2.setInt(1,category_id);
					preparedStatementq2.setString(2,subcategory_name);
					int result=preparedStatementq2.executeUpdate();
					if(result==1)
					{   categoryresult="Successfully";
						return categoryresult;
					}
				    }
				}
				else
				{
					categoryresult="Already_exist_category";
					return categoryresult;
				}
			}
			else
			{   int category_id = 0;
				PreparedStatement preparedStatementq1;
				PreparedStatement preparedStatementq2;
				PreparedStatement preparedStatementq3;
				PreparedStatement preparedStatementq4;
				preparedStatementq1 = connection.prepareStatement("select * from category where category_name=?");
			    preparedStatementq1.setString(1,new_category_name);
			    ResultSet rsq1 = preparedStatementq1.executeQuery();
			    if(rsq1.next())
				{   categoryresult="Already_exist_category";
					return categoryresult;
				}
			    else 
			    {   preparedStatementq2 = connection.prepareStatement(
							 "INSERT INTO `category`(`category_name`) VALUES (?)");
					preparedStatementq2.setString(1,new_category_name);
					int result=preparedStatementq2.executeUpdate();
					preparedStatementq4 = connection.prepareStatement("select * from category where category_name=?");
				    preparedStatementq4.setString(1,new_category_name);
				    ResultSet rsq4 = preparedStatementq4.executeQuery();
				    if(rsq4.next())
					{
				    	category_id = rsq4.getInt(1);
					}
					if(!subcategory_name.equals("")) {
						preparedStatementq3 = connection.prepareStatement(
								 "INSERT INTO `sub_category`(`category_id`,`sub_category_name`) VALUES (?,?)");
						preparedStatementq3.setInt(1,category_id);
						preparedStatementq3.setString(2,subcategory_name);
						int result_1=preparedStatementq3.executeUpdate();
						if(result==1 && result_1==1)
						{    categoryresult="Successfully";
							return categoryresult;
						}
					}
					else
					{
						if(result==1)
						{   categoryresult="Successfully";
							return categoryresult;
						}
					}
			    }
			}
			}catch (SQLException e) {
				e.printStackTrace();
			}
			return categoryresult;
			}
		
		
//		public ArrayList<Product> getProducts(String category_name) {
//
//			Product product;
//			ArrayList<Product> productList = new ArrayList<Product>();
//			String q1="select category_id from category where category_name=?";
//			int category_id = 0;
//			String q2 = "select * from product where category_id = ?";
//			PreparedStatement preparedStatementq2;
//			PreparedStatement preparedStatementq1;
//			try {
//				 preparedStatementq1 = connection.prepareStatement(q1);
//				preparedStatementq1.setString(1,category_name);
//				ResultSet rsq1 = preparedStatementq1.executeQuery();
//				if(rsq1.next())
//				{
//					category_id = rsq1.getInt(1);
//				}
//				preparedStatementq2 = connection.prepareStatement(q2);
//				preparedStatementq2.setInt(1,category_id);
//			
//				
//				 ResultSet rsq2 =  preparedStatementq2.executeQuery();
//
//				while(rsq2.next()) {
//					product = new Product();
//					product.setProduct_id(rsq2.getInt(1));
//					product.setCategory_id(rsq2.getInt(2));
//					product.setSub_category_id(rsq2.getInt(3));
//					product.setProduct_name(rsq2.getString(4));
//					product.setProduct_price(rsq2.getInt(5));
//					product.setProduct_discount(rsq2.getInt(6));
//					product.setProduct_condition(rsq2.getString(7));
//					product.setProduct_shipping(rsq2.getString(8));
//					product.setProduct_sold_quantity(rsq2.getInt(9));
//					product.setProduct_quantity(rsq2.getInt(10));
//					product.setProduct_available_quantity(rsq2.getInt(11));
//					product.setProduct_img_url(rsq2.getString(12));
//					product.setProduct_description(rsq2.getString(13));
//					productList.add(product);
//				}
//				System.out.println(productList.size());
//
//				for (Product l : productList) {
//					System.out.println(l.getProduct_id() + "  " + l.getProduct_description());
//				}
//
//			} catch (SQLException e) {
//				e.printStackTrace();
//			}
//			return productList;
//		
//		}
//		
		

}

package org.ebay_project.ebaytester.service;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import javax.validation.ReportAsSingleViolation;

import org.ebay_project.ebaytester.model.Product;

import com.mysql.jdbc.Statement;

public class ProductService {

	Connection connection;

	public ProductService() {
		
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
	
//	public ArrayList<Product> getAllProducts() {
//
//		Product product;
//		ArrayList<Product> productList = new ArrayList<Product>();
//		String sql = "select * from product";
//		PreparedStatement preparedStatement;
//		try {
//			preparedStatement = connection.prepareStatement(sql);
//			
//		
//			
//			 ResultSet rs =  preparedStatement.executeQuery();
//
//			while(rs.next()) {
//				product = new Product();
//				product.setProduct_id(rs.getInt(1));
//				product.setCategory_id(rs.getInt(2));
//				product.setSub_category_id(rs.getInt(3));
//				product.setProduct_name(rs.getString(4));
//				product.setProduct_price(rs.getInt(5));
//				product.setProduct_discount(rs.getInt(6));
//				product.setProduct_condition(rs.getString(7));
//				product.setProduct_shipping(rs.getString(8));
//				product.setProduct_sold_quantity(rs.getInt(9));
//				product.setProduct_quantity(rs.getInt(10));
//				product.setProduct_available_quantity(rs.getInt(11));
//				product.setProduct_img_url(rs.getString(12));
//				product.setProduct_description(rs.getString(13));
//				productList.add(product);
//			}
//			System.out.println(productList.size());
//
//			for (Product l : productList) {
//				System.out.println(l.getProduct_id() + "  " + l.getProduct_description());
//			}
//
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return productList;
//	
//	}
	
	/*String product_name, String category, String subcategory,int seller_id, int product_price,
			int product_discount, int product_sold_quantity, int product_available_quantity, int product_rating,
			int product_year, String product_condition, String product_shipping, String product_img_url,
			String product_description, String deal, String brand, String color, String screen_size, String processor,
			String storage, String warranty, String operating_system, String gender, String applicable, String material,
			String clothing_size, String style, String warranty_type, String card_class)*/
	public Product setProductInfo(String product_name,
			String category,
			String subcategory,
			int seller_id,
			int product_price,
			int quantity,
			String product_condition,
			String product_shipping,
			String product_description,
			int product_discount,
			String deal,
			String brand,
			String color,
			String screen_size,
			String processor,
			String storage,
			String warranty,
			String operating_system,
			int product_year,
			String gender,
			String warranty_type,
			String applicable,
			String material,
			String clothing_size,
			String style,
			String card_class)
	{
		int product_sold_quantity=0,product_available_quantity=quantity,product_rating=0;
		String product_img_url="";
		PreparedStatement preparedstmnt;
		ResultSet rs;
			try {
			String query="select category_id from category where category_name=?";
			preparedstmnt=	(PreparedStatement) connection.prepareStatement(query);
			preparedstmnt.setString(1, category);
			rs= preparedstmnt.executeQuery();
			int category_id=0;
			if(rs.next())
				category_id=rs.getInt("category_id");
			
			query="select sub_category_id from sub_category where sub_category_name=?";
			preparedstmnt=	(PreparedStatement) connection.prepareStatement(query);
			preparedstmnt.setString(1, subcategory);
			rs= preparedstmnt.executeQuery();
			int sub_category_id =0;
			if(rs.next())
				sub_category_id=rs.getInt("sub_category_id");
			
//			query="select product_available_quantity from product where product_name=? AND user_id=seller_id";
//			preparedstmnt=	(PreparedStatement) connection.prepareStatement(query);
//			preparedstmnt.setString(1,product_name );
//			preparedstmnt.setInt(2,seller_id );
//			rs= preparedstmnt.executeQuery();
//			int product_available_quantity =0;
//			if(rs.next())
//				product_available_quantity=rs.getInt("product_available_quantity");
			
			query="insert into product(sub_category_id,category_id,user_id,product_name,product_price,product_discount,product_condition,product_shipping, product_sold_quantity, product_img_url, product_available_quantity, product_description,product_rating, deal, brand, color, screen_size,processor, storage, warranty, operating_system, product_year, gender, applicable, material, clothing_size, style, warranty_type, card_class) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);";
			preparedstmnt=(PreparedStatement) connection.prepareStatement(query);
			preparedstmnt.setInt(1, sub_category_id);
			preparedstmnt.setInt(2, category_id);
			preparedstmnt.setInt(3, seller_id);
			preparedstmnt.setString(4, product_name);
			preparedstmnt.setInt(5, product_price);
			preparedstmnt.setInt(6, product_discount);
			preparedstmnt.setString(7, product_condition);
			preparedstmnt.setString(8, product_shipping);
			preparedstmnt.setInt(9, product_sold_quantity); //product_sold_quantity
			preparedstmnt.setString(10,product_img_url);
			preparedstmnt.setInt(11, quantity);
			preparedstmnt.setString(12, product_description);
			preparedstmnt.setInt(13, product_rating);
			preparedstmnt.setString(14, deal);
			preparedstmnt.setString(15, brand);
			preparedstmnt.setString(16, color);
			preparedstmnt.setString(17, screen_size);
			preparedstmnt.setString(18, processor);
			preparedstmnt.setString(19, storage);
			preparedstmnt.setString(20, warranty);
			preparedstmnt.setString(21, operating_system);
			preparedstmnt.setInt(22, product_year);
			preparedstmnt.setString(23, gender);
			preparedstmnt.setString(24, applicable);
			preparedstmnt.setString(25, material);
			preparedstmnt.setString(26, clothing_size);
			preparedstmnt.setString(27, style);
			preparedstmnt.setString(28, warranty_type);
			preparedstmnt.setString(29, card_class);
			
			preparedstmnt.execute();
			
			
			query="select * from product_info where product_name =? AND user_id=? ";
			preparedstmnt=(PreparedStatement) connection.prepareStatement(query);
			preparedstmnt.setString(1, product_name);
			preparedstmnt.setInt(2, seller_id);
			rs=preparedstmnt.executeQuery();
			int product_id=0;
			if(rs.next())
				product_id=rs.getInt("product_id");
			
			Product product=new Product(product_id, sub_category_id, sub_category_id,seller_id, product_price, product_discount, product_sold_quantity,
					product_available_quantity, product_rating, product_year, product_name, product_condition, product_shipping, product_img_url, 
					product_description, deal, brand, color, screen_size, processor, storage, warranty, operating_system, gender, applicable, material, 
					clothing_size, style, warranty_type, card_class);
			System.out.println(product_id);
			new File(PathSetup.imagePath + "products/" + product_id+ "/images").mkdirs();
			//new ProductService().Upload()
			
			InputStream fileInputStream = null;
			OutputStream outputStream = null;
			String path = PathSetup.imagePath + "products/" + product_id+ "/";

			try {
				fileInputStream = new FileInputStream(PathSetup.imagePath + "defaultProductPic.jpg");

				outputStream = new FileOutputStream(new File(path + "productPic.jpg"));
				int read = 0;
				byte[] bytes = new byte[1024];
				while ((read = fileInputStream.read(bytes)) != -1) {
					outputStream.write(bytes, 0, read);

				}
				product.setProduct_img_url("productPic.jpg");
				outputStream.close();
				fileInputStream.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		
		
//	} catch (Exception e) {
//		System.out.println("Exception raised!!" + e);
//	}
return product;	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
	
//	public void DeleteProduct(String product_name, int seller_id)
//	{PreparedStatement preparedstmnt;
//		try {
//		String query= "delete from product_info where product_name=? and seller_id=?;";
//		preparedstmnt=(PreparedStatement) connection.prepareStatement(query);
//		preparedstmnt.setString(1, product_name);
//		preparedstmnt.setInt(2, seller_id);
//		preparedstmnt.execute();
//		
//		}
//		catch(Exception e)
//		{
//		 e.printStackTrace();
//		}
//		
//	}
	
//	public Product setProductInfo(String Product_Name,String Category,String Subcategory,int Price,int Quantity,String Condition,String Shipping,String Description,int Discount) {
//
//		PreparedStatement preparedstmnt;
//		PreparedStatement preparedstmnt1;
//		PreparedStatement preparedstmnt11;
//		PreparedStatement preparedstmnt12;
//		Product response = null;
//		int category_id=0;
//		int sub_category_id=0;
//		int pid=0;
//			try {
//				
//				preparedstmnt11=connection.prepareStatement("select category_id from category where category_name = ?");
//				preparedstmnt11.setString(1,Category);
//				ResultSet rs11 = preparedstmnt11.executeQuery();
//				if(rs11.next())
//				{
//					category_id=rs11.getInt(1);
//				}
//				preparedstmnt12=connection.prepareStatement("select sub_category_id from sub_category where sub_category_name = ?");
//				preparedstmnt12.setString(1,Subcategory);
//				ResultSet rs12 = preparedstmnt11.executeQuery();
//				if(rs12.next())
//				{
//					sub_category_id=rs12.getInt(1);
//				}
//				 preparedstmnt = connection.prepareStatement(
//						 "INSERT INTO `product`(`category_id`,`sub_category_id`,`product_name`,`product_price`,`product_discount`,`product_condition`,`product_shipping`,`product_sold_quantity`,`product_quantity`,`product_available_quantity`,`product_img_url`,`product_description`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)");
//				preparedstmnt.setInt(1,category_id);
//				preparedstmnt.setInt(2,sub_category_id);
//				preparedstmnt.setString(3,Product_Name);
//				preparedstmnt.setInt(4,Price);
//				preparedstmnt.setInt(5,Discount);
//				preparedstmnt.setString(6,Condition);
//				preparedstmnt.setString(7,Shipping);
//				preparedstmnt.setInt(8,0);
//				preparedstmnt.setInt(9,Quantity);
//				preparedstmnt.setInt(10,Quantity);
//				preparedstmnt.setString(11,"");
//				preparedstmnt.setString(12,Description);
//				System.out.println(category_id +" "+sub_category_id );
//				System.out.println("product upload successful" + preparedstmnt.executeUpdate());
//				preparedstmnt1 = connection
//						.prepareStatement("select product_id from product where product_name = ? and sub_category_id = ?");
//				preparedstmnt1.setString(1,Product_Name);
//				preparedstmnt1.setInt(2,sub_category_id);
//				ResultSet rs = preparedstmnt1.executeQuery();
//				 
//				
//				if (rs.next())
//					{
//					pid = rs.getInt(1);
//				
//					}
//				
//				response = new Product();
//				response.setProduct_id(pid);
//				response.setCategory_id(category_id);
//				response.setSub_category_id(sub_category_id);
//				response.setProduct_name(Product_Name);
//				response.setProduct_price(Price);
//				response.setProduct_discount(Discount);
//				response.setProduct_condition(Condition);
//				response.setProduct_shipping(Shipping);
//				response.setProduct_sold_quantity(0);
//				response.setProduct_quantity(Quantity);
//				response.setProduct_available_quantity(Quantity);
//				response.setProduct_img_url("");
//				response.setProduct_description(Description);
//				
//				
//				
//				
//				
//					System.out.println(response.getProduct_id());
//					new File(PathSetup.imagePath + "products/" + response.getProduct_id()+ "/images").mkdirs();
//					//new ProductService().Upload()
//					
//					InputStream fileInputStream = null;
//					OutputStream outputStream = null;
//					String path = PathSetup.imagePath + "products/" + response.getProduct_id()+ "/";
//
//					try {
//						fileInputStream = new FileInputStream(PathSetup.imagePath + "defaultProductPic.jpg");
//
//						outputStream = new FileOutputStream(new File(path + "productPic.jpg"));
//						int read = 0;
//						byte[] bytes = new byte[1024];
//						while ((read = fileInputStream.read(bytes)) != -1) {
//							outputStream.write(bytes, 0, read);
//
//						}
//						response.setProduct_img_url("productPic.jpg");
//						outputStream.close();
//						fileInputStream.close();
//					} catch (Exception e) {
//						e.printStackTrace();
//					}
//				
//			} catch (Exception e) {
//				System.out.println("Exception raised!!" + e);
//			}
//			return response;
//		}
	@SuppressWarnings("finally")
	public String uploadProductPic(InputStream fileInputStream,String fileName, String id) {	
		System.out.println("now uploading the product");
		System.out.println(id);
		int product_id = Integer.parseInt(id);
		PreparedStatement preparedStatement3;
		File folder = new File(PathSetup.imagePath+"products/"+product_id);
	    File[] listOfFiles = folder.listFiles();
	    listOfFiles[1].delete();
	    //System.out.println(userName+" "+listOfFiles[1].getName()+" profilepic to delete");
	   
	    //System.out.println(f.delete());
		OutputStream outputStream=null;
		OutputStream outputStream1=null;
		fileName=""+Calendar.getInstance().getTimeInMillis()+fileName;
		String path=PathSetup.imagePath+"products/"+product_id+"/images/";
		String productPicPath=PathSetup.imagePath+"products/"+product_id+"/";
		Product product = new Product();
		try{
			outputStream=new FileOutputStream(new File(path+fileName));
			outputStream1=new FileOutputStream(new File(productPicPath+"p"+fileName));
			String query= "update product set product_img_url = ? where product_id = ?";
			preparedStatement3 = connection.prepareStatement(query);
			preparedStatement3.setString(1,productPicPath+"p"+fileName);
			preparedStatement3.setInt(2,product_id);
			preparedStatement3.executeUpdate();
			int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = fileInputStream.read(bytes)) != -1) { 
            	outputStream.write(bytes, 0, read);
            	outputStream1.write(bytes, 0, read);               
            }
            outputStream.close();
            outputStream1.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		finally{	  
			if(outputStream!=null)
			{
			product.setProduct_img_url(productPicPath+"p"+fileName);
			System.out.println(product.getProduct_img_url()+"........................................says hello");
				return product.getProduct_img_url();
			}
			return null;
		}			
	}
	
	public int ValidateProductId(int product_id) {
		try {
			
			PreparedStatement stmt = connection.prepareStatement("Select product_id from product_info where product_id = ?");
			stmt.setInt(1, product_id);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}
	

}
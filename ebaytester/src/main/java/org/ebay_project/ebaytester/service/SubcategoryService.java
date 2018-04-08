package org.ebay_project.ebaytester.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.ebay_project.ebaytester.model.Subcategory;

public class SubcategoryService {

	Connection connection;

	public SubcategoryService() {

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
// ========================================GET SUBCATEGORY NAME===================================================//
	public Subcategory getSubcategoryName(int sub_category_id) {
		Subcategory sc = new Subcategory();
		PreparedStatement preparedStatement;
		try {
			String query = "select * from sub_category where sub_category_id = ?";
			preparedStatement = (PreparedStatement) connection.prepareStatement(query);
			preparedStatement.setInt(1, sub_category_id);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {

				sc.setSub_category_id(rs.getInt(1));
				sc.setCategory_id(rs.getInt(2));
				sc.setSub_category_name(rs.getString(3));
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return sc;
	}
// ============================================GET ALL SUBCATEGORY NAME===========================================//
	public List<Subcategory> getAllSubcategoryName() {
		Subcategory s1;
		List<Subcategory> list = new ArrayList<>();
		PreparedStatement preparedstmnt;
		try {
			String query = "select * from sub_category";
			preparedstmnt = (PreparedStatement) connection.prepareStatement(query);
			ResultSet rs = preparedstmnt.executeQuery();
			while (rs.next()) {
				s1 = new Subcategory();
				s1.setSub_category_id(rs.getInt(1));
				s1.setCategory_id(rs.getInt(2));
				s1.setSub_category_name(rs.getString(3));
				list.add(s1);
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return list;
	}
}
// ==================================================END OF CODE==================================================//
package com.training.demo.product;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {

	public int add(Product product) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd");
			String sql = "insert into product(name, price, quantity) values(?,?,?)";
			PreparedStatement st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			st.setString(1, product.getName());
			st.setDouble(2, product.getPrice());
			st.setInt(3, product.getQuantity());
			st.executeUpdate();			
			
			ResultSet rs = st.getGeneratedKeys();
			rs.next();
			int pk = rs.getInt(1);
			return pk;
		}
		catch(ClassNotFoundException | SQLException e) {
			throw new ProductException("Error while adding product", e);
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}


	public boolean doesProductExist(String name) {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd");
			String sql = "select count(*) from product where name = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, name);
			ResultSet rs = st.executeQuery();
			
			if(rs.next()) {
				int count = rs.getInt(1);
				if(count > 0)
					return true;
			}
			return false;
		}
		catch(ClassNotFoundException | SQLException e) {
			throw new ProductException("Error while checking product by name", e);
		}
		finally {
			try { conn.close(); } catch(Exception e) { }
		}
	}


}

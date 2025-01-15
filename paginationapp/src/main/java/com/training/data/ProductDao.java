package com.training.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.exception.ProductAccessException;

public class ProductDao {

	static {
		try { Class.forName("com.mysql.cj.jdbc.Driver");  } catch(Exception e) { }
	}
	
	public List<Product> fetchProducts(int offset, int rows) {
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd")) {
			String sql = "select * from product limit ?,?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, offset);
			st.setInt(2, rows);
			ResultSet rs = st.executeQuery();
			List<Product> list = new ArrayList<Product>();
			while(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				list.add(p);
			}
			return list;
		}
		catch(SQLException e) {
			throw new ProductAccessException("Something went wrong", e);
		}
	}
	
	public int count() {
		try(Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/training", "root", "passw0rd")) {
			String sql = "select count(*) from product";
			PreparedStatement st = conn.prepareStatement(sql);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				return rs.getInt(1);
			}
			throw new ProductAccessException("Something went wrong while counting products");
		}
		catch(SQLException e) {
			throw new ProductAccessException("Something went wrong", e);
		}
	}

}

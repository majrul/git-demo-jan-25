package com.training.data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.training.exception.ProductAccessException;

//TODO: 
	/*
	 create table product(id int primary key auto_increment, name varchar(25), price double, quantity int);
	 insert into product(name, price, quantity) values('iPhone 14', 49000, 99);
	insert into product(name, price, quantity) values('iPhone 15', 59000, 99);
	insert into product(name, price, quantity) values('iPhone 16', 69000, 99);
	 */

public class ProductDao {

	static {
		try { Class.forName("org.h2.Driver");  } catch(Exception e) { }
	}
	
	public void add(Product product) {
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/rest-api-training;AUTO_SERVER=true", "sa", "")) {
			String sql = "insert into product(name, price, quantity) values(?, ?, ?)";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, product.getName());
			st.setDouble(2, product.getPrice());
			st.setInt(3, product.getQuantity());
			
			st.executeUpdate();
		}
		catch(SQLException e) {
			throw new ProductAccessException("Something went wrong", e);
		}
		
	}

	public Product fetchOne(int id) {
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/rest-api-training;AUTO_SERVER=true", "sa", "")) {
			String sql = "select * from product where id = ?";
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			ResultSet rs = st.executeQuery();
			if(rs.next()) {
				Product p = new Product();
				p.setId(rs.getInt("id"));
				p.setName(rs.getString("name"));
				p.setPrice(rs.getDouble("price"));
				p.setQuantity(rs.getInt("quantity"));
				return p;
			}
			throw new ProductAccessException("Something went wrong");
		}
		catch(SQLException e) {
			throw new ProductAccessException("Something went wrong", e);
		}
		
	}

	public List<Product> fetchAll() {
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/rest-api-training;AUTO_SERVER=true", "sa", "")) {
			String sql = "select * from product";
			PreparedStatement st = conn.prepareStatement(sql);
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
	
	public List<Product> fetchProducts(int offset, int rows) {
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/rest-api-training;AUTO_SERVER=true", "sa", "")) {
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
		try(Connection conn = DriverManager.getConnection("jdbc:h2:~/rest-api-training;AUTO_SERVER=true", "sa", "")) {
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

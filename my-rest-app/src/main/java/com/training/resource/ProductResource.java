package com.training.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.training.data.Product;
import com.training.data.ProductDao;

@Path("/product")
public class ProductResource {

	// http://localhost:8080/my-rest-app/api/product/all
	@GET
	@Path("/all")
	public List<Product> getAll() {
		ProductDao dao = new ProductDao();
		return dao.fetchAll();
	}

	// http://localhost:8080/my-rest-app/api/product/1
	@GET
	@Path("/{id}")
	public Product get(@PathParam("id") int id) {
		ProductDao dao = new ProductDao();
		return dao.fetchOne(id);
	}

	// http://localhost:8080/my-rest-app/api/product/1
	// In the body, send the product data as a json from POSTman or any other tool
	/*
	 	{
    		"name": "Samsung S23",
    		"price": 39000,
    		"quantity": 99
		}
	 */
	@POST
	@Path("/add")
	public String add(Product product) {
		ProductDao dao = new ProductDao();
		dao.add(product);
		return "Product added successfully!";
	}
	
}

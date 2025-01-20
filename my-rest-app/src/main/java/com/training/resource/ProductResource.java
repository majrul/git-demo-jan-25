package com.training.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

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
}

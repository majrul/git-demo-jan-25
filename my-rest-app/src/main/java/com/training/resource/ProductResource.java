package com.training.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.training.data.Product;
import com.training.data.ProductDao;
import com.training.model.ProductDetails;

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

	// http://localhost:8080/my-rest-app/api/product/add
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
	
	// http://localhost:8080/my-rest-app/api/product/update
	// In the body, send the product data as a json from POSTman or any other tool
	/*
		{
		    "id": 1,
		    "name": "iPhone 14",
		    "price": 48000,
		    "quantity": 89
		}	 
		
		The issue in this code is that if we pass, partial data in json, for ex:
		 	{
		    	"id": 1,
		    	"quantity": 79
			}
		The remaining fields in the db will get null values
	 */
	@PUT
	@Path("/update")
	public String update(Product product) {
		ProductDao dao = new ProductDao();
		dao.update(product);
		return "Product updated successfully!";
	}
	
	
	// http://localhost:8080/my-rest-app/api/product/update/partial
	// In the body, send the product data as a json from POSTman or any other tool
	/*
	 	{
		    "id": 2,
		    "propertyName" : "quantity",
		    "propertyValue": 79
		}
	*/
	@PATCH
	@Path("/update/partial")
	public String updatePartial(ProductDetails productDetails) {
		ProductDao dao = new ProductDao();
		Product existingProduct = dao.fetchOne(productDetails.getId());
		
		//figure out how can we remove the below if conditions
		if(productDetails.getPropertyName().equals("name"))
			existingProduct.setName(productDetails.getPropertyValue());
		else if(productDetails.getPropertyName().equals("price"))
			existingProduct.setPrice(Double.parseDouble(productDetails.getPropertyValue()));
		else if((productDetails.getPropertyName().equals("quantity")))
			existingProduct.setQuantity(Integer.parseInt(productDetails.getPropertyValue()));
		
		dao.update(existingProduct);
		return "Product updated successfully!";
	}
	
	
}

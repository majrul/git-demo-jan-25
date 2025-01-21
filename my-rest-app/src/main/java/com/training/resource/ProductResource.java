package com.training.resource;

import java.net.URI;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Link;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.training.data.Product;
import com.training.data.ProductDao;
import com.training.exception.ProductAccessException;
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
	public Product getProduct(@PathParam("id") int id) {
		ProductDao dao = new ProductDao();
		return dao.fetchOne(id);
	}

	
	// http://localhost:8080/my-rest-app/api/product/get/1
	@GET
	@Path("/get/{id}")
	public Response getv2(@PathParam("id") int id) {
		try {
			ProductDao dao = new ProductDao();
			return  Response
					.ok()
					.entity(dao.fetchOne(id))
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
		catch (ProductAccessException e) {
			return  Response
					.status(400)
					.entity(new Status(false, e.getMessage()))
					.type(MediaType.APPLICATION_JSON)
					.build();
		}
	}

	public static class Status {
		private boolean status;
		private String messageIfAny;
		
		public Status() {
		}
		public Status(boolean status, String messageIfAny) {
			super();
			this.status = status;
			this.messageIfAny = messageIfAny;
		}
		public boolean isStatus() {
			return status;
		}
		public void setStatus(boolean status) {
			this.status = status;
		}
		public String getMessageIfAny() {
			return messageIfAny;
		}
		public void setMessageIfAny(String messageIfAny) {
			this.messageIfAny = messageIfAny;
		}
		
		
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
	
	//trying to implement HATEOAS
	@POST
	@Path("/add/hateoas/single")
	public Response addv2(Product product, @Context UriInfo uriInfo) {
		ProductDao dao = new ProductDao();
		int id = dao.add(product);
		
		URI uri = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/my-rest-app/api
					.path(ProductResource.class) // /product
					.path(ProductResource.class, "getProduct") // /{id}
					.build(id); //substituting {id} with actual value
		
		return Response
				.created(uri) //201 HTTP Status Code
				.entity("Product added successfully!")
				.type(MediaType.TEXT_PLAIN)
				.build();
	}
	
	@POST
	@Path("/add/hateoas/multiple")
	public Response addv3(Product product, @Context UriInfo uriInfo) {
		ProductDao dao = new ProductDao();
		int id = dao.add(product);
		
		URI uri1 = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/my-rest-app/api
					.path(ProductResource.class) // /product
					.path(ProductResource.class, "getProduct") // /{id}
					.build(id); //substituting {id} with actual value
		
		Link link1 = Link
						.fromUri(uri1)
						.rel("Get Product By ID")
						.build();
		
		URI uri2 = uriInfo
					.getBaseUriBuilder() //http://localhost:8080/my-rest-app/api
					.path(ProductResource.class) // /product
					.path(ProductResource.class, "getAll") // /all
					.build();
	
		Link link2 = Link
					.fromUri(uri2)
					.rel("Get All Products")
					.build();
		
		
		return Response
				.ok()
				.links(link1, link2)
				.entity("Product added successfully!")
				.type(MediaType.TEXT_PLAIN)
				.build();
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
	
	// http://localhost:8080/my-rest-app/api/product/delete/1
	@DELETE
	@Path("/delete/{id}")
	public String delete(@PathParam("id")int id) {
		ProductDao dao = new ProductDao();
		dao.delete(id);
		
		return "Product deleted successfully!";
	}
	
}

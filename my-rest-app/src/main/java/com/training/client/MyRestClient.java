package com.training.client;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

import com.training.data.Product;

public class MyRestClient {

	public static void main(String[] args) {
		//Using JAX-RS Client API
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080/my-rest-app/api/product/{id}");
		
		//testing GET method
		String response = target
							.resolveTemplate("id", 10)
							.request()
							.get(String.class);
		System.out.println(response);
		
		Product product = target
							.resolveTemplate("id", 10)
							.request()
							.get(Product.class);
		System.out.println(product);
		
		
		//testing POST method
		Product p = new Product();
		p.setName("Redmi 14 Pro");
		p.setPrice(29000);
		p.setQuantity(40);
		
		target = client.target("http://localhost:8080/my-rest-app/api/product/add");

		response = target
					.request()
					.post(Entity.entity(p, MediaType.APPLICATION_JSON), String.class);
		System.out.println(response);
		
		
		client.close(); //should be in finally block
	}
}

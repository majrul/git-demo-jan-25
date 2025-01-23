package com.training.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.training.demo.product.Product;
import com.training.demo.product.ProductDao;
import com.training.demo.product.ProductService;

class PoductServiceTest {

	@Test
	void addProduct_ShouldBeSuccessful() {
		ProductDao productDao = new ProductDao();
		ProductService productService = new ProductService(productDao);
		
		Product product = new Product();
		product.setName("Samsung S23");
		product.setPrice(59000);
		product.setQuantity(50);
		
		int id = productService.add(product);
		assertTrue(id > 0);
	}

}

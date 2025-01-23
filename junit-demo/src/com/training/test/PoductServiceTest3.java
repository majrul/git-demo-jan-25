package com.training.test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import com.training.demo.product.Product;
import com.training.demo.product.ProductDao;
import com.training.demo.product.ProductService;

@ExtendWith(MockitoExtension.class)
class PoductServiceTest3 {

	@Spy
	private ProductDao productDao = new ProductDao();
	
	@InjectMocks
	private ProductService productService;
	
	@Test
	void addProduct_ShouldBeSuccessful() {
		when(productDao.doesProductExist(anyString())).thenReturn(false);
		
		Product product = new Product();
		product.setName("Samsung S23");
		product.setPrice(59000);
		product.setQuantity(50);
		
		int id = productService.add(product);
		assertTrue(id > 0);
	}

}

package com.training.demo.product;

public class ProductService {

	private ProductDao productDao;

	public ProductService(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	public int add(Product product) {
		boolean exists = productDao.doesProductExist(product.getName());
		if(!exists) {
			int id = productDao.add(product);
			return id;
		}
		else
			throw new ProductException("Cannot add, product already exists!");
	}
}

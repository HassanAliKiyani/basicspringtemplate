package com.example.demo.dao;

import java.util.List;

import com.example.demo.model.Product;

public interface ProductDao {
	
//	public abstract long createUser();
	 List<Product> findAll();
	 Product findById(Long id);
	 Product save(Product product);
	 void deleteById(Long id);

}

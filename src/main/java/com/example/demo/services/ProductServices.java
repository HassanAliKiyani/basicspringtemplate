package com.example.demo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.ProductDao;
import com.example.demo.model.Product;

@Service
public class ProductServices {
	
	@Autowired
	private ProductDao productDao;
	
	 public List<Product> getAllProducts() {
	        return productDao.findAll();
	    }

	    public Product getProductById(Long id) {
	        return productDao.findById(id);
	    }

	    public Product createProduct(Product product) {
	        return productDao.save(product);
	    }

	    public Product updateProduct(Long id, Product productDetails) {
	        Product existingProduct = productDao.findById(id);
	        if (existingProduct != null) {
	            existingProduct.setName(productDetails.getName());
	            existingProduct.setDescription(productDetails.getDescription());
	            existingProduct.setPrice(productDetails.getPrice());
	            return productDao.save(existingProduct);
	        }
	        return null;
	    }
	    
	    

	    public void deleteProduct(Long id) {
	        productDao.deleteById(id);
	    }

}

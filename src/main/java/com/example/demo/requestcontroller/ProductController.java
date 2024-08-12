package com.example.demo.requestcontroller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.mappers.ResponseControllerMapper;
import com.example.demo.model.Product;
import com.example.demo.services.ProductServices;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	 @Autowired
	    private ProductServices productService;

	    @GetMapping("/getall")
	    public ResponseEntity<Object> getAllProducts() {
	    	try {
	    		Map<String,Object> res = ResponseControllerMapper.successResponseMap();
		        res.put("Message", "Total Products");
		    	List<Product> products = productService.getAllProducts();
		    	res.put("Data", products);
		        return ResponseEntity.ok(res);
		    }
	    	catch(Exception e){
	    		System.out.println(e);
	    		return ResponseControllerMapper.errorResponse(404, "Error in requesting database");
	    	}
	    }

	    @GetMapping
	    public ResponseEntity<Object> getProductById(@RequestParam Long id) {
	    	try {
		        Product product = productService.getProductById(id);
		        Map<String,Object> res = ResponseControllerMapper.successResponseMap();
		        res.put("Message", "Product found");
	//	        System.out.println(product);
		        res.put("Data", product);
		        return product != null ? ResponseEntity.ok(res) : ResponseControllerMapper.errorResponse(404, "Resource you are requesting doesn't exist");
	    	}
	    	catch(Exception e){
	    		System.out.println(e);
	    		return ResponseControllerMapper.errorResponse(404, "Resource you are requesting doesn't exist");
	    	}
	    }

	    @PostMapping
	    public ResponseEntity<Object> createProduct(@RequestBody Product product) {
	    	Map<String,Object> res = ResponseControllerMapper.successResponseMap();
	    	Product dataProduct = productService.createProduct(product);
	        res.put("Message", "Product Created Successfully");
	        res.put("Data", dataProduct);	        
	        return ResponseEntity.ok(res);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Object> updateProduct(@PathVariable Long id, @RequestBody Product productDetails) {
	    	try {
		    	Map<String,Object> res = ResponseControllerMapper.successResponseMap();
		        Product updatedProduct = productService.updateProduct(id, productDetails);
		        res.put("Message", "Product Updated Successfully");
		        res.put("Data", updatedProduct);
		        return updatedProduct != null ? ResponseEntity.ok(res) : ResponseEntity.notFound().build();
	    	}
	    	catch(Exception e){
	    		System.out.println(e);
	    		return ResponseControllerMapper.errorResponse(404, "Resource you are requesting doesn't exist");
	    	}
	    }

	    @DeleteMapping
	    public ResponseEntity<Object> deleteProduct(@RequestParam Long id) {
	        try {
		    	productService.deleteProduct(id);
		    	Map<String,Object> res = ResponseControllerMapper.successResponseMap();
		    	res.put("Messgae","Product deleted successfully");
		        return ResponseEntity.ok(res);
	        }
	        catch(Exception e){
	    		System.out.println(e);
	    		return ResponseControllerMapper.errorResponse(404, "Resource you are requesting doesn't exist");
	    	}
	        
	    }
}

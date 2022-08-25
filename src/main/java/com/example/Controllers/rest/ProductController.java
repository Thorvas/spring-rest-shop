package com.example.Controllers.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Entities.Product;
import com.example.Service.ProductService;

@RestController
@RequestMapping("/api")
public class ProductController {
	
	@Autowired
	ProductService productService;
	
	@GetMapping("/product/{productId}")
	public Product getProduct(@PathVariable int productId) {
		return productService.findById(productId);
	}
	
	@GetMapping("/product")
	public List<Product> getAllProducts() {
		return productService.listAll();
	}
	
	@PostMapping("/product")
	public Product saveProduct(@RequestBody Product newProduct) {
		productService.save(newProduct);
		return newProduct;
	}
	
	@DeleteMapping("/product/{productId}")
	public String deleteProduct(@PathVariable int productId) {
		Product foundProduct = productService.findById(productId);
		
		if (foundProduct == null) {
			throw new RuntimeException("Product could not be found.");
		}
		productService.delete(productId);
		return "Product of id: "+productId+" has been deleted.";
	}
}

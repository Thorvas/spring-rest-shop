package com.example.Controllers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.Entities.Customer;
import com.example.Entities.Order;
import com.example.Entities.Product;
import com.example.Service.CustomerService;
import com.example.Service.OrderService;
import com.example.Service.ProductService;

@Controller
@RequestMapping("/home")
public class WebController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	ProductService productService;
	
	@PostConstruct
	public void initEntities() {
		Customer newCustomer = new Customer();
		Product newProduct = new Product();
		
		newCustomer.setFirstName("John");
		newCustomer.setLastName("Doe");
		newCustomer.setEmail("JohnDoe@gmail.com");
		
		newProduct.setProductName("Boost to level 60");
		newProduct.setProductDescription("Allows you to boost your character's level to 60.");
		newProduct.setProductCategory("CHARACTER_SERVICE");
		newProduct.setProductOwner(newCustomer);
		newProduct.setProductCost(50);
		
		List<Product> newProductList = new ArrayList<Product>();
		
		newCustomer.setOwnedProducts(newProductList);
		
		customerService.save(newCustomer);
		productService.save(newProduct);
	}
	
	@GetMapping("")
	public String getView(Model theModel) {
		List<Product> retrievedProducts = productService.listAll();
		theModel.addAttribute("products", retrievedProducts);
		return "helloworld";
	}	
}

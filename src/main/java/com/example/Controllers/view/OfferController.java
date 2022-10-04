package com.example.Controllers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.Entities.Customer;
import com.example.Entities.Product;
import com.example.Security.configuration.MyUserDetails;
import com.example.Service.CustomerService;
import com.example.Service.ProductService;

@Controller
@RequestMapping("/home")
public class OfferController {

	@Autowired
	CustomerService customerService;
	
	@Autowired
	ProductService productService;
	
	@GetMapping("")
	public String getView(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		List<Product> retrievedProducts = productService.listAll();
		theModel.addAttribute("products", retrievedProducts);
		theModel.addAttribute("currUser", user);
		theModel.addAttribute("currCustomer", foundCustomer);
		return "helloworld";
	}
	
	@GetMapping("/showOffer")
	public String getOffer(@RequestParam("offerId") int offerId, Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer currentCustomer = customerService.findById(user.getCustomer().getId());
		Product retrievedProduct = productService.findById(offerId);
		customerService.processPayment(currentCustomer, retrievedProduct);
		return "redirect:/home";
	}

	@GetMapping("/listProducts")
	public String getProducts(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		List<Product> ownedProducts = customerService.listOwnerProducts(user.getCustomer());
		theModel.addAttribute("productList", ownedProducts);
		return "ownedproducts";
	}
	
	@GetMapping("/postOffer")
	public String postForm(Model theModel) {
		Product newProduct = new Product();
		theModel.addAttribute("product", newProduct);
		return "postform";
	}
	
	@PostMapping("/postOffer")
	public String submitForm(@ModelAttribute Product product, Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		productService.processPost(foundCustomer, product);
		customerService.processPost(foundCustomer, product);
		return "redirect:/home";
	}
	
	@GetMapping("/deleteOffer")
	public String deleteOffer(@RequestParam("offerId") int offerId, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		Product foundProduct = productService.findById(offerId);
		customerService.deleteProduct(foundCustomer, foundProduct);
		return "redirect:/home";
	}
}

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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
		List<Product> retrievedProducts = productService.listAll();
		theModel.addAttribute("products", retrievedProducts);
		theModel.addAttribute("currUser", user);
		return "helloworld";
	}
	
	@GetMapping("/showOffer/{offerId}")
	public String getOffer(@PathVariable int offerId, Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Product retrievedProduct = productService.findById(offerId);
		if (user.getCustomer().getBalance() >= retrievedProduct.getProductCost())
		{
			Customer retrievedProductOwner = retrievedProduct.getProductOwner();
			user.getCustomer().setBalance(user.getCustomer().getBalance()-retrievedProduct.getProductCost());
			productService.delete(retrievedProduct.getId());
			retrievedProduct.getProductOwner().getOwnedProducts().clear();
			retrievedProduct.getProductOwner().setOwnedProducts(productService.listOwnerProducts(retrievedProduct.getProductOwner()));
		}
		else {
			System.out.println("Insufficient funds!");
		}
		return "redirect:/home";
	}

	@GetMapping("/listProducts")
	public String getProducts(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		List<Product> ownedProducts = productService.listOwnerProducts(user.getCustomer());
		System.out.println(user.getCustomer().getBalance());
		for (Product p : ownedProducts) {
			System.out.println(p.getProductName());
		}
		theModel.addAttribute("productList", ownedProducts);
		return "ownedproducts";
	}
}

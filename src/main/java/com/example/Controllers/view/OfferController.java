package com.example.Controllers.view;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

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
	public String getView(@PathVariable int offerId, Model theModel) {
		Product retrievedProduct = productService.findById(offerId);
		theModel.addAttribute("product", retrievedProduct);
		return "showoffers";
	}
}

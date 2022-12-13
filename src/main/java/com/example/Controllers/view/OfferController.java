package com.example.Controllers.view;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
	
	@PostMapping("/addMoney")
	public String processIncrease(@RequestParam("id") int id, @RequestParam("toAdd") int toAdd) {
		Customer foundCustomer = customerService.findById(id);
		foundCustomer.setBalance(toAdd);
		customerService.save(foundCustomer);
		return "redirect:/home";
	}
	
	@PostMapping("/addFriend")
	public String getPost(@RequestParam(value="friendId", required=false) int friendId, @RequestParam(value="customerId", required=false) int customerId) {
		Customer friend = customerService.findById(friendId);
		Customer currentUser = customerService.findById(customerId);
		Set<Customer> friends = new HashSet<Customer>();
		Set<Customer> friendof = new HashSet<Customer>();
		friends.add(friend);
		friendof.add(currentUser);
		currentUser.setFriends(friends);
		friend.setFriendOf(friendof);
		customerService.save(currentUser);
		
		return "redirect:/home";
	}
	
	@GetMapping("")
	public String getView(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		List<Product> retrievedProducts = productService.listAll();
		theModel.addAttribute("products", retrievedProducts);
		theModel.addAttribute("currUser", user);
		theModel.addAttribute("currCustomer", foundCustomer);
		theModel.addAttribute("customerFriends", foundCustomer.getFriends());
		return "helloworld";
	}
	
	@GetMapping("/showOffer")
	public String getOffer(@RequestParam("offerId") int offerId, Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer currentCustomer = customerService.findById(user.getCustomer().getId());
		Product retrievedProduct = productService.findById(offerId);
		if (customerService.processPayment(currentCustomer, retrievedProduct)) {
			productService.delete(retrievedProduct.getId());
		}
		return "redirect:/home";
	}

	@GetMapping("/listProducts")
	public String getProducts(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		theModel.addAttribute("customer", foundCustomer);
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
		productService.delete(foundProduct.getId());
		return "redirect:/home";
	}
	
	@GetMapping("/showBalance")
	public String showBalance(Model theModel, @AuthenticationPrincipal MyUserDetails user) {
		Customer foundCustomer = customerService.findById(user.getCustomer().getId());
		theModel.addAttribute("customer", foundCustomer);
		return "showbalance";
	}
	
	@GetMapping("/customer/{customerId}")
	public String getCustomer(Model theModel, @PathVariable int customerId, @AuthenticationPrincipal MyUserDetails user) {
		Customer currUser = customerService.findById(user.getCustomer().getId());
		Customer foundCustomer = customerService.findById(customerId);
		theModel.addAttribute("friend", foundCustomer);
		theModel.addAttribute("customer", currUser);
		return "customerdetails";
		
	}
}

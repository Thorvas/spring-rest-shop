package com.example.Controllers.view;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class WebController {
	
	@GetMapping("")
	public String getView(Model theModel) {
		theModel.addAttribute("localTime", new java.util.Date());
		return "helloworld";
	}	
}

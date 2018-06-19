package com.oz.tailor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oz.tailor.model.Customer;
import com.oz.tailor.repository.CustomerRepository;

@Controller
public class IndexController {
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/")
	//@ResponseBody
	public String index() {
		return "index";
	}
	
	@GetMapping("/customers")
	public String customers() {
		return "customerList";
	}
	
	@GetMapping("/customer")
	public String customer(Model model) {
		model.addAttribute("customer", new Customer());
		return "customer";
	}
	
	@GetMapping("/customer/{id}")
	public String customerUpdate(@PathVariable("id") long id, Model model) {
		Customer customer =  customerRepository.findById(id).get();
		model.addAttribute("customer", customer);
		return "customer";
	}
}

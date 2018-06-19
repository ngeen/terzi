package com.oz.tailor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oz.tailor.model.Customer;
import com.oz.tailor.model.DressModel;
import com.oz.tailor.model.Fabric;
import com.oz.tailor.repository.CustomerRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.DressTypeRepository;
import com.oz.tailor.repository.FabricRepository;

@Controller
public class IndexController {
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	DressTypeRepository dressTypeRepository;
	
	@Autowired
	FabricRepository fabricRepository;
	
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
	
	@GetMapping("/dressModels")
	public String dressModels() {
		return "dressModelList";
	}
	
	@GetMapping("/dressModel")
	public String dressModel(Model model) {
		model.addAttribute("dressModel", new DressModel());
		model.addAttribute("dressTypeRepository",dressTypeRepository.findAll());
		return "dressModel";
	}
	
	@GetMapping("/dressModel/{id}")
	public String dressModelUpdate(@PathVariable("id") long id, Model model) {
		DressModel dressModel =  dressModelRepository.findById(id).get();
		model.addAttribute("dressModel", dressModel);
		model.addAttribute("dressTypeRepository",dressTypeRepository.findAll());
		return "dressModel";
	}
	
	@GetMapping("/fabrics")
	public String fabrics() {
		return "fabricList";
	}
	
	@GetMapping("/fabric")
	public String fabric(Model model) {
		model.addAttribute("fabric", new Fabric());
		return "fabric";
	}
	
	@GetMapping("/fabric/{id}")
	public String fabricUpdate(@PathVariable("id") long id, Model model) {
		Fabric fabric =  fabricRepository.findById(id).get();
		model.addAttribute("fabric", fabric);
		return "fabric";
	}
}

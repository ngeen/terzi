package com.oz.tailor.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

	@GetMapping("/")
	//@ResponseBody
	public String index() {
		return "index";
	}
	
	@GetMapping("/customers")
	public String customers() {
		return "customers";
	}
}

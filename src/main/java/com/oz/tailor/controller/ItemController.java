package com.oz.tailor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.GomlekDTO;
import com.oz.tailor.model.Fabric;
import com.oz.tailor.model.Gomlek;
import com.oz.tailor.model.Item;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.ItemRepository;
import com.oz.tailor.repository.ShirtRepository;

@Controller
public class ItemController {
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	ShirtRepository shirtRepository;
	
	@GetMapping("/saveShirt/{basketId}")
	public String addShirt(@PathVariable("basketId") long basketId, Model model) {
		model.addAttribute("basketId", basketId);
		model.addAttribute("shirt",new GomlekDTO());
		model.addAttribute("dressModel", dressModelRepository.findAll());
		return "shirt";
	}
	
	@GetMapping("/saveShirt/{basketId}/{shirtId}")
	public String updateShirt(@PathVariable("basketId") long basketId, @PathVariable("shirtId") long shirtId, Model model) {
		Gomlek gomlek = shirtRepository.findById(shirtId).get();
		GomlekDTO gomlekDTO = new GomlekDTO();
		gomlekDTO.setBasen(gomlek.getBasen());
		gomlekDTO.setBoy(gomlek.getBoy());
		gomlekDTO.setGobek(gomlek.getGobek());
		gomlekDTO.setGogus(gomlek.getGogus());
		gomlekDTO.setIspala(gomlek.getIspala());
		gomlekDTO.setKolBoyu(gomlek.getKolBoyu());
		gomlekDTO.setManset(gomlek.getManset());
		gomlekDTO.setNakis(gomlek.getNakis());
		gomlekDTO.setPazu(gomlek.getPazu());
		gomlekDTO.setRoba(gomlek.getRoba());
		gomlekDTO.setYaka(gomlek.getYaka());
		gomlekDTO.setDressModelId(gomlek.getDressModel().getId());
		gomlekDTO.setBasketId(gomlek.getBasket().getId());
		
		model.addAttribute("basketId", basketId);
		model.addAttribute("shirt", gomlekDTO);
		model.addAttribute("dressModel", dressModelRepository.findAll());
		return "shirt";
	}
	

}

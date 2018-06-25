package com.oz.tailor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.CeketDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Ceket;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.ItemRepository;
import com.oz.tailor.repository.JacketRepository;

@RestController
public class JacketController {
	@Autowired
	JacketRepository jacketRepository;
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	BasketRepository basketRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserController userController;
	
	@PostMapping("/saveJacket")
    public ResponseEntity<?> createUser(@RequestBody CeketDTO ceketDTO) {
		Ceket ceket = new Ceket();
		ceket.setBoy(ceketDTO.getBoy());
		ceket.setGogus(ceketDTO.getGogus());
		ceket.setIspala(ceketDTO.getIspala());
		ceket.setBelOrtasi(ceketDTO.getBelOrtasi());
		ceket.setAyna(ceketDTO.getAyna());
		ceket.setKol(ceketDTO.getKol());
		ceket.setPazu(ceketDTO.getPazu());
		ceket.setDressModel(dressModelRepository.findById(ceketDTO.getDressModelId()).get());
		ceket.setBasket(basketRepository.findById(ceketDTO.getBasketId()).get());
		ceket.setUser(userController.getAuthUser());
		jacketRepository.save(ceket);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteJacket/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        Ceket ceket = jacketRepository.findById(id).get();
        if (ceket == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        jacketRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

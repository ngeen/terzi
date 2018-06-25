package com.oz.tailor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.PantolonDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Pantolon;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.ItemRepository;
import com.oz.tailor.repository.PantRepository;

@RestController
public class PantController {
	@Autowired
	PantRepository pantRepository;
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	BasketRepository basketRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	UserController userController;
	
	@PostMapping("/savePant")
    public ResponseEntity<?> createUser(@RequestBody PantolonDTO pantolonDTO) {
		Pantolon pantolon = new Pantolon();
		pantolon.setAg(pantolonDTO.getAg());
		pantolon.setBasen(pantolonDTO.getBasen());
		pantolon.setBel(pantolonDTO.getBel());
		pantolon.setDizGenisligi(pantolonDTO.getDizGenisligi());
		pantolon.setKavala(pantolonDTO.getKavala());
		pantolon.setPaca(pantolonDTO.getPaca());
		pantolon.setDressModel(dressModelRepository.findById(pantolonDTO.getDressModelId()).get());
		pantolon.setBasket(basketRepository.findById(pantolonDTO.getBasketId()).get());
		pantolon.setUser(userController.getAuthUser());
		pantRepository.save(pantolon);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deletePant/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        Pantolon pantolon = pantRepository.findById(id).get();
        if (pantolon == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        pantRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

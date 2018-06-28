package com.oz.tailor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.YelekDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Yelek;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.CustomerRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.ItemRepository;
import com.oz.tailor.repository.WaistRepository;

@RestController
public class WaistController {
	@Autowired
	WaistRepository waistRepository;
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	BasketRepository basketRepository;
	
	@Autowired
	ItemRepository itemRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	@Autowired
	UserController userController;
	
	@PostMapping("/saveWaist")
    public ResponseEntity<?> createUser(@RequestBody YelekDTO yelekDTO) {
		Yelek yelek = new Yelek();
		yelek.setBoy(yelekDTO.getBoy());
		yelek.setGobek(yelekDTO.getGobek());
		yelek.setGogus(yelekDTO.getGogus());
		yelek.setAyna(yelekDTO.getAyna());
		yelek.setDressModel(dressModelRepository.findById(yelekDTO.getDressModelId()).get());
		yelek.setBasket(basketRepository.findById(yelekDTO.getBasketId()).get());
		yelek.setId(yelekDTO.getId());
		
		yelek.setUser(userController.getAuthUser());
		waistRepository.save(yelek);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteWaist/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        Yelek yelek = waistRepository.findById(id).get();
        if (yelek == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        waistRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
	
	@GetMapping("/waistForCustomer/{waistId}")
    public ResponseEntity<?> waistForCustomer(@PathVariable("waistId") long waistId) {
		
        Yelek tempYelek = waistRepository.findById(waistId).get();
        long customerId = tempYelek.getBasket().getCustomer().getId();
        Yelek realYelek = waistRepository.findByCustomerId(customerId);
        if (realYelek != null) {
        	realYelek.setCustomer(null);
        	waistRepository.save(realYelek);
        }
        

        tempYelek.setCustomer(customerRepository.findById(customerId).get());
        waistRepository.save(tempYelek);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

package com.oz.tailor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.BasketDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Basket;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.CustomerRepository;
import com.oz.tailor.repository.FabricRepository;

@RestController
public class BasketController {
	@Autowired
	BasketRepository basketRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	FabricRepository fabricRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	UserController userController;

	@GetMapping("/listBaskets")
	public ResponseEntity<List<Basket>> listBaskets(HttpServletRequest request, HttpServletResponse response) {
		List<Basket> baskets = (List<Basket>) basketRepository.findAll();
		if (baskets.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Basket>>(baskets, HttpStatus.OK);
	}

	@GetMapping("/getBasket/{id}")
	public ResponseEntity<Basket> getBaskets(@PathVariable("id") long id) {
		Basket basket = basketRepository.findById(id).get();
		return new ResponseEntity<Basket>(basket, HttpStatus.OK);
	}

	@PostMapping("/saveBasket")
	public ResponseEntity<?> saveBasket(@RequestBody BasketDTO basketDTO) {
		try {
			Converter<BasketDTO, Basket> converter = context -> {
				Basket entity = context.getDestination();
				
				if (context.getSource().getCustomerId() > 0)
					entity.setCustomer(customerRepository.findById(context.getSource().getCustomerId()).get());

				if (context.getSource().getFabricId() > 0)
					entity.setFabric(fabricRepository.findById(context.getSource().getFabricId()).get());

				return entity;
			};
			modelMapper.createTypeMap(BasketDTO.class, Basket.class).setPostConverter(converter);
			
			Basket basket = modelMapper.map(basketDTO, Basket.class);
			basket.setUser(userController.getAuthUser());

			Basket b = basketRepository.save(basket);

			return new ResponseEntity<String>("{\"result\":" + b.getId() + "}", HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<String>("{\"result\": false, error: \"" +e.getStackTrace().toString()+"\"}", HttpStatus.CREATED);
		}

	}

	// ------------------- Delete a User-----------------------------------------

	@GetMapping("/deleteBasket/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Basket basket = basketRepository.findById(id).get();
		if (basket == null) {

			return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
		}
		basketRepository.deleteById(id);
		return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
	}
}

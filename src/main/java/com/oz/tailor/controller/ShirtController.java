package com.oz.tailor.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.DTO.GomlekDTO;
import com.oz.tailor.DTO.ItemDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Gomlek;
import com.oz.tailor.model.Item;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.ItemRepository;
import com.oz.tailor.repository.ShirtRepository;

@RestController
public class ShirtController {
	@Autowired
	ShirtRepository shirtRepository;

	@Autowired
	DressModelRepository dressModelRepository;

	@Autowired
	BasketRepository basketRepository;

	@Autowired
	ItemRepository itemRepository;

	@Autowired
	UserController userController;

	@PostMapping("/saveShirt")
	public ResponseEntity<?> createUser(@RequestBody GomlekDTO gomlekDTO) {
		Gomlek gomlek = new Gomlek();
		gomlek.setBasen(gomlekDTO.getBasen());
		gomlek.setBoy(gomlekDTO.getBoy());
		gomlek.setGobek(gomlekDTO.getGobek());
		gomlek.setGogus(gomlekDTO.getGogus());
		gomlek.setIspala(gomlekDTO.getIspala());
		gomlek.setKolBoyu(gomlekDTO.getKolBoyu());
		gomlek.setManset(gomlekDTO.getManset());
		gomlek.setNakis(gomlekDTO.getNakis());
		gomlek.setPazu(gomlekDTO.getPazu());
		gomlek.setRoba(gomlekDTO.getRoba());
		gomlek.setYaka(gomlekDTO.getYaka());
		gomlek.setDressModel(dressModelRepository.findById(gomlekDTO.getDressModelId()).get());
		gomlek.setBasket(basketRepository.findById(gomlekDTO.getBasketId()).get());
		
		
		gomlek.setUser(userController.getAuthUser());
		shirtRepository.save(gomlek);

		return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
	}

	// ------------------- Delete a User-----------------------------------------

	@GetMapping("/deleteShirt/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Gomlek gomlek = shirtRepository.findById(id).get();
		if (gomlek == null) {

			return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
		}
		shirtRepository.deleteById(id);
		return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
	}

	@GetMapping("/listItems/{basketId}")
	public ResponseEntity<List<ItemDTO>> listItems(@PathVariable("basketId") long basketId) {
		List<ItemDTO> items = new ArrayList<ItemDTO>();
		for (Item item : itemRepository.findAllByBasketId(basketId)) {
			ItemDTO itemDTO = new ItemDTO();
			itemDTO.setId(item.getId());
			itemDTO.setDressModelId(item.getDressModel().getId());
			itemDTO.setBasketId(item.getBasket().getId());
			itemDTO.setItemType(item.getClass().getSimpleName());
			items.add(itemDTO);
		}
		if (items.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ItemDTO>>(items, HttpStatus.OK);
	}
}

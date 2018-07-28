package com.oz.tailor.controller;

import java.util.ArrayList;
import java.util.List;

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
import com.oz.tailor.model.Ceket;
import com.oz.tailor.model.Gomlek;
import com.oz.tailor.model.Item;
import com.oz.tailor.model.Pantolon;
import com.oz.tailor.model.Yelek;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.CustomerRepository;
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
	CustomerRepository customerRepository;

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
		gomlek.setId(gomlekDTO.getId());
		
		
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
	
	@GetMapping("/getItemDetail/{itemId}")
	public ResponseEntity<String> getItemDetails(@PathVariable("itemId") long itemId) {
		Item item = itemRepository.findById(itemId).get();
		if(item == null) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
		}
		
		StringBuilder sb = new StringBuilder();
		switch (item.getClass().getSimpleName()) {
		case "Gomlek":
			Gomlek gomlek = (Gomlek)item;
			sb.append("Basen : "+gomlek.getBasen());
			sb.append(" Boy : "+gomlek.getBoy());
			sb.append(" Göbek : "+gomlek.getGobek());
			sb.append(" Göğüs : "+gomlek.getGogus());
			sb.append(" İspala : "+gomlek.getIspala());
			sb.append(" Kol Boyu : "+gomlek.getKolBoyu());
			sb.append(" Manşet : "+gomlek.getManset());
			sb.append(" Nakış : "+gomlek.getNakis());
			sb.append(" Pazu : "+gomlek.getPazu());
			sb.append(" Roba : "+gomlek.getRoba());
			sb.append(" Yaka : "+gomlek.getYaka());
			break;
		case "Yelek":
			Yelek yelek = (Yelek)item;
			sb.append("Ayna : "+yelek.getAyna());
			sb.append(" Boy : "+yelek.getBoy());
			sb.append(" Göbek : "+yelek.getGobek());
			sb.append(" Göğüs : "+yelek.getGogus());
			
			break;
		case "Ceket":
			Ceket ceket = (Ceket)item;
			sb.append("Ayna : " + ceket.getAyna());
			sb.append(" Bel Ortası : " + ceket.getBelOrtasi());
			sb.append(" Boy : " + ceket.getBoy());
			sb.append(" Göğüs : " + ceket.getGogus());
			sb.append(" İspala : " + ceket.getIspala());
			sb.append(" Kol : " + ceket.getKol());
			sb.append(" Pazu : " + ceket.getPazu());
			
			break;
		case "Pantolon":
			Pantolon pantolon = (Pantolon)item;
			sb.append("Ağ : " + pantolon.getAg());
			sb.append(" Basen : " + pantolon.getBasen());
			sb.append(" Bel : " + pantolon.getBel());
			sb.append(" Diz Genişliği : " + pantolon.getDizGenisligi());
			sb.append(" Kavala : " + pantolon.getKavala());
			sb.append(" Paça : " + pantolon.getPaca());
			
			break;
		default:
			break;
		}
		
		return new ResponseEntity<String>(sb.toString(), HttpStatus.CREATED);
	}
	
	@GetMapping("/shirtForCustomer/{shirtId}")
    public ResponseEntity<?> shirtForCustomer(@PathVariable("shirtId") long shirtId) {
		
        Gomlek tempGomlek = shirtRepository.findById(shirtId).get();
        long customerId = tempGomlek.getBasket().getCustomer().getId();
        Gomlek realGomlek = shirtRepository.findByCustomerId(customerId);
        if (realGomlek != null) {
        	realGomlek.setCustomer(null);
        	shirtRepository.save(realGomlek);
        }
        

        tempGomlek.setCustomer(customerRepository.findById(customerId).get());
        shirtRepository.save(tempGomlek);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

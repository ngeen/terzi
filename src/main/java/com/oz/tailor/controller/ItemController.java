package com.oz.tailor.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.oz.tailor.DTO.CeketDTO;
import com.oz.tailor.DTO.GomlekDTO;
import com.oz.tailor.DTO.PantolonDTO;
import com.oz.tailor.DTO.YelekDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Basket;
import com.oz.tailor.model.Ceket;
import com.oz.tailor.model.Gomlek;
import com.oz.tailor.model.Pantolon;
import com.oz.tailor.model.Yelek;
import com.oz.tailor.repository.BasketRepository;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.JacketRepository;
import com.oz.tailor.repository.PantRepository;
import com.oz.tailor.repository.ShirtRepository;
import com.oz.tailor.repository.WaistRepository;

@Controller
public class ItemController {
	
	@Autowired
	DressModelRepository dressModelRepository;
	
	@Autowired
	ShirtRepository shirtRepository;
	
	@Autowired
	WaistRepository waistRepository;
	
	@Autowired
	PantRepository pantRepository;
	
	@Autowired
	JacketRepository jacketRepository;
	
	@Autowired
	BasketRepository basketRepository;
	
	@Autowired
	UserController userController;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@GetMapping("/saveShirt/{basketId}")
	public String addShirt(@PathVariable("basketId") long basketId, Model model) {
		Basket basket = basketRepository.findById(basketId).get();
		GomlekDTO gomlekDTO = new GomlekDTO();
		Gomlek gomlek = shirtRepository.findByCustomerId(basket.getCustomer().getId());
		if(gomlek != null) {
			gomlekDTO = modelMapper.map(gomlek, GomlekDTO.class);
		}
		model.addAttribute("basketId", basketId);
		model.addAttribute("shirt", gomlekDTO);
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "shirt";
	}
	
	@GetMapping("/saveShirt/{basketId}/{shirtId}")
	public String updateShirt(@PathVariable("basketId") long basketId, @PathVariable("shirtId") long shirtId, Model model) {
		Gomlek gomlek = shirtRepository.findById(shirtId).get();
		GomlekDTO gomlekDTO = modelMapper.map(gomlek, GomlekDTO.class);
		/*GomlekDTO gomlekDTO = new GomlekDTO();
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
		gomlekDTO.setBasketId(gomlek.getBasket().getId());*/
		
		model.addAttribute("basketId", basketId);
		model.addAttribute("shirt", gomlekDTO);
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "shirt";
	}
	
	@GetMapping("/saveWaist/{basketId}")
	public String addWaist(@PathVariable("basketId") long basketId, Model model) {
		model.addAttribute("basketId", basketId);
		model.addAttribute("waist",new YelekDTO());
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "waist";
	}
	
	@GetMapping("/saveWaist/{basketId}/{waistId}")
	public String updateWaist(@PathVariable("basketId") long basketId, @PathVariable("waistId") long waistId, Model model) {
		Yelek yelek = waistRepository.findById(waistId).get();
		YelekDTO yelekDTO = modelMapper.map(yelek, YelekDTO.class);
		/*YelekDTO yelekDTO = new YelekDTO();
		yelekDTO.setBoy(yelek.getBoy());
		yelekDTO.setGobek(yelek.getGobek());
		yelekDTO.setGogus(yelek.getGogus());
		yelekDTO.setAyna(yelek.getAyna());
		yelekDTO.setDressModelId(yelek.getDressModel().getId());
		yelekDTO.setBasketId(yelek.getBasket().getId());*/
		
		model.addAttribute("basketId", basketId);
		model.addAttribute("waist", yelekDTO);
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "waist";
	}
	
	@GetMapping("/savePant/{basketId}")
	public String addPant(@PathVariable("basketId") long basketId, Model model) {
		model.addAttribute("basketId", basketId);
		model.addAttribute("pant",new PantolonDTO());
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "pant";
	}
	
	@GetMapping("/savePant/{basketId}/{pantId}")
	public String updatePant(@PathVariable("basketId") long basketId, @PathVariable("pantId") long pantId, Model model) {
		Pantolon pantolon = pantRepository.findById(pantId).get();
		PantolonDTO pantolonDTO = modelMapper.map(pantolon, PantolonDTO.class);
		/*PantolonDTO pantolonDTO = new PantolonDTO();
		pantolonDTO.setAg(pantolon.getAg());
		pantolonDTO.setBasen(pantolon.getBasen());
		pantolonDTO.setBel(pantolon.getBel());
		pantolonDTO.setDizGenisligi(pantolon.getDizGenisligi());
		pantolonDTO.setKavala(pantolon.getKavala());
		pantolonDTO.setPaca(pantolon.getPaca());
		pantolonDTO.setDressModelId(pantolon.getDressModel().getId());
		pantolonDTO.setBasketId(pantolon.getBasket().getId());*/
		
		model.addAttribute("basketId", basketId);
		model.addAttribute("pant", pantolonDTO);
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "pant";
	}
	
	@GetMapping("/saveJacket/{basketId}")
	public String addJacket(@PathVariable("basketId") long basketId, Model model) {
		model.addAttribute("basketId", basketId);
		model.addAttribute("jacket",new CeketDTO());
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "jacket";
	}
	
	@GetMapping("/saveJacket/{basketId}/{jacketId}")
	public String updateJacket(@PathVariable("basketId") long basketId, @PathVariable("jacketId") long jacketId, Model model) {
		Ceket ceket = jacketRepository.findById(jacketId).get();
		CeketDTO ceketDTO = modelMapper.map(ceket, CeketDTO.class);
		/*CeketDTO ceketDTO = new CeketDTO();
		ceketDTO.setBoy(ceket.getBoy());
		ceketDTO.setGogus(ceket.getGogus());
		ceketDTO.setIspala(ceket.getIspala());
		ceketDTO.setPazu(ceket.getPazu());
		ceketDTO.setAyna(ceket.getAyna());
		ceketDTO.setKol(ceket.getKol());
		ceketDTO.setBelOrtasi(ceket.getBelOrtasi());
		ceketDTO.setDressModelId(ceket.getDressModel().getId());
		ceketDTO.setBasketId(ceket.getBasket().getId());*/
		
		model.addAttribute("basketId", basketId);
		model.addAttribute("jacket", ceketDTO);
		model.addAttribute("dressModel", dressModelRepository.findAllByUserId(userController.getAuthUser().getId()));
		return "jacket";
	}
	

}

package com.oz.tailor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.DressModel;
import com.oz.tailor.model.DressType;
import com.oz.tailor.repository.DressModelRepository;
import com.oz.tailor.repository.DressTypeRepository;

@RestController
public class DressModelController {
	@Autowired
	private DressModelRepository dressModelRepository;
	
	@Autowired
	private DressTypeRepository dressTypeRepository;
	
	@Autowired 
	UserController userController;
	
	@GetMapping("/listDressModels")
	public ResponseEntity<List<DressModel>> listModels(HttpServletRequest request, HttpServletResponse response){
		List<DressModel> models = (List<DressModel>) dressModelRepository.findAllByUserId(userController.getAuthUser().getId());
        if (models.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DressModel>>(models, HttpStatus.OK);
	}
	
	@GetMapping("/listDressTypes")
	public ResponseEntity<List<DressType>> listTypes(HttpServletRequest request, HttpServletResponse response){
		List<DressType> types = (List<DressType>) dressTypeRepository.findAll();
        if (types.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<DressType>>(types, HttpStatus.OK);
	}
	
	@GetMapping("/getDressModel/{id}")
	public ResponseEntity<DressModel> getModel(@PathVariable("id") long id){
		DressModel dressModel =  dressModelRepository.findById(id).get();
        return new ResponseEntity<DressModel>(dressModel, HttpStatus.OK);
	}
	
	@GetMapping("/saveDressModel/{dressModelId}/{dressTypeId}/{dressModelName}")
    public ResponseEntity<?> create(@PathVariable long dressModelId, @PathVariable long dressTypeId, @PathVariable String dressModelName) {
		DressModel dressModel = dressModelRepository.findById(dressModelId).orElse(new DressModel());
		dressModel.setDressType(dressTypeRepository.findById(dressTypeId).get());
		dressModel.setDressModel(dressModelName);
		dressModel.setUser(userController.getAuthUser());
		dressModelRepository.save(dressModel);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteDressModel/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
 
		DressModel dressModel = dressModelRepository.findById(id).get();
        if (dressModel == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        dressModelRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

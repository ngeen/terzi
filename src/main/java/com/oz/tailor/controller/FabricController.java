package com.oz.tailor.controller;

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

import com.oz.tailor.model.Fabric;
import com.oz.tailor.repository.FabricRepository;

@RestController
public class FabricController {
	@Autowired
	FabricRepository fabricRepository;
	
	@GetMapping("/listFabrics")
	public ResponseEntity<List<Fabric>> listFabrics(HttpServletRequest request, HttpServletResponse response){
		List<Fabric> fabrics = (List<Fabric>) fabricRepository.findAll();
        if (fabrics.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Fabric>>(fabrics, HttpStatus.OK);
	}
	
	@GetMapping("/getFabric/{id}")
	public ResponseEntity<Fabric> getCustomers(@PathVariable("id") long id){
		Fabric fabric =  fabricRepository.findById(id).get();
        return new ResponseEntity<Fabric>(fabric, HttpStatus.OK);
	}
	
	@PostMapping("/saveFabric")
    public ResponseEntity<?> createUser(@RequestBody Fabric fabric) {

        fabricRepository.save(fabric);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteFabric/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        Fabric fabric = fabricRepository.findById(id).get();
        if (fabric == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        fabricRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

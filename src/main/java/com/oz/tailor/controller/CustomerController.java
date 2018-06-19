package com.oz.tailor.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.oz.tailor.model.Customer;
import com.oz.tailor.repository.CustomerRepository;

@RestController
public class CustomerController {
	@Autowired
	CustomerRepository customerRepository;
	
	@GetMapping("/listCustomers")
	public ResponseEntity<List<Customer>> listCustomers(HttpServletRequest request, HttpServletResponse response){
		List<Customer> customers = (List<Customer>) customerRepository.findAll();
        if (customers.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);
	}
	
	@GetMapping("/getCustomer/{id}")
	public ResponseEntity<Customer> getCustomers(@PathVariable("id") long id){
		Customer customer =  customerRepository.findById(id).get();
        return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	
	@PostMapping("/saveCustomer")
    public ResponseEntity<?> createUser(@RequestBody Customer customer) {

        customerRepository.save(customer);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteCustomer/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        Customer customer = customerRepository.findById(id).get();
        if (customer == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        customerRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

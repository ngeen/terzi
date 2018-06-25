package com.oz.tailor.controller;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.reflect.TypeToken;
import com.oz.tailor.DTO.ReceiptDTO;
import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.Customer;
import com.oz.tailor.model.Receipt;
import com.oz.tailor.repository.CustomerRepository;
import com.oz.tailor.repository.ReceiptRepository;
import com.oz.tailor.repository.ReceiptTypeRespository;

@RestController
public class ReceiptController {
	@Autowired
	ReceiptRepository receiptRepository;

	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	ReceiptTypeRespository receiptTypeRespository;
	
	@Autowired
	UserController userController;
	
	@Autowired
	ModelMapper modelMapper;

	@GetMapping("/listReceipts")
	public ResponseEntity<List<ReceiptDTO>> listReceipts(HttpServletRequest request, HttpServletResponse response) {
		List<Receipt> receipts = (List<Receipt>) receiptRepository.findAllByUserId(userController.getAuthUser().getId());
		Type listType = new TypeToken<List<ReceiptDTO>>() {}.getType();
		List<ReceiptDTO> receiptDTOs = modelMapper.map(receipts, listType);
		receiptDTOs.forEach(x -> x.setCustomerDebt(userDebt(x.getCustomer().getId())));
		
	
		if (receipts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ReceiptDTO>>(receiptDTOs, HttpStatus.OK);
	}

	@GetMapping("/getReceipt/{id}")
	public ResponseEntity<Receipt> getReceipts(@PathVariable("id") long id) {
		Receipt receipt = receiptRepository.findById(id).get();
		return new ResponseEntity<Receipt>(receipt, HttpStatus.OK);
	}

	@PostMapping("/saveReceipt")
	public ResponseEntity<?> saveReceipt(@RequestBody ReceiptDTO receiptDTO) {

		Receipt receipt = receiptRepository.findById(receiptDTO.getId()).orElse(new Receipt());

		receipt.setReceiptAmount(receiptDTO.getReceiptAmount());

		if (receiptDTO.getReceiptTypeId() > 0)
			receipt.setReceiptType(receiptTypeRespository.findById(receiptDTO.getReceiptTypeId()).get());

		if (receiptDTO.getCustomerId() > 0)
			receipt.setCustomer(customerRepository.findById(receiptDTO.getCustomerId()).get());
		
		receipt.setUser(userController.getAuthUser());

		Receipt b = receiptRepository.save(receipt);

		return new ResponseEntity<String>("{\"result\":" + b.getId() + "}", HttpStatus.CREATED);
	}
	
	public double userDebt(long userId) {

		Customer customer = customerRepository.findById(userId).get();
		if (customer == null) {

			return 0;
		}
		double sumBaskets = customer.getBaskets().stream().mapToDouble(n-> n.getAmount()).sum();
		double sumReceipts = customer.getReceipts().stream().mapToDouble(n-> n.getReceiptAmount()).sum();
		double totalDebt = sumBaskets-sumReceipts;
		return totalDebt;
	}

	// ------------------- Delete a User-----------------------------------------

	@GetMapping("/deleteReceipt/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {

		Receipt receipt = receiptRepository.findById(id).get();
		if (receipt == null) {

			return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
		}
		receiptRepository.deleteById(id);
		return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
	}
}

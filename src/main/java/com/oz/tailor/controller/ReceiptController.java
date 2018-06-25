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

	@GetMapping("/listReceipts")
	public ResponseEntity<List<Receipt>> listReceipts(HttpServletRequest request, HttpServletResponse response) {
		List<Receipt> receipts = (List<Receipt>) receiptRepository.findAllByUserId(userController.getAuthUser().getId());
		if (receipts.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<Receipt>>(receipts, HttpStatus.OK);
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

		Receipt b = receiptRepository.save(receipt);

		return new ResponseEntity<String>("{\"result\":" + b.getId() + "}", HttpStatus.CREATED);
	}
	
	@GetMapping("/getUserDebt/{userId}")
	public ResponseEntity<?> userDebt(@PathVariable("userId") long userId) {

		Customer customer = customerRepository.findById(userId).get();
		if (customer == null) {

			return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
		}
		double sumBaskets = customer.getBaskets().stream().mapToDouble(n-> n.getAmount()).sum();
		double sumReceipts = customer.getReceipts().stream().mapToDouble(n-> n.getReceiptAmount()).sum();
		double totalDebt = sumBaskets-sumReceipts;
		return new ResponseEntity<String>("{\"result\":\""+totalDebt+"\"}", HttpStatus.CREATED);
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

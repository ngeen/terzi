package com.oz.tailor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oz.tailor.model.DressType;
import com.oz.tailor.model.Receipt;
import com.oz.tailor.model.ReceiptType;
import com.oz.tailor.model.User;
import com.oz.tailor.repository.DressTypeRepository;
import com.oz.tailor.repository.ReceiptTypeRespository;
import com.oz.tailor.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

	private UserRepository userRepository;
	private DressTypeRepository dressTypeRepository;
	private ReceiptTypeRespository receiptTypeRespository;

	@Autowired
	public DataLoader(UserRepository userRepository, DressTypeRepository dressTypeRepository,
			ReceiptTypeRespository receiptTypeRespository) {
		this.userRepository = userRepository;
		this.dressTypeRepository = dressTypeRepository;
		this.receiptTypeRespository = receiptTypeRespository;
	}

	public void run(ApplicationArguments args) {
		User user = userRepository.findByUserName("admin");
		if (user == null) {
			BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
			userRepository.save(new User("admin", bCryptPasswordEncoder.encode("admin"), "ADMIN"));
		}

		if (dressTypeRepository.count() <= 0) {
			dressTypeRepository.save(new DressType("Ceket/Elbise"));
			dressTypeRepository.save(new DressType("Yelek"));
			dressTypeRepository.save(new DressType("Gömlek"));
			dressTypeRepository.save(new DressType("Pantolon/Etek"));
		}

		if (receiptTypeRespository.count() <= 0) {
			receiptTypeRespository.save(new ReceiptType("Kredi Kartı"));
			receiptTypeRespository.save(new ReceiptType("Havale"));
			receiptTypeRespository.save(new ReceiptType("Eft"));
			receiptTypeRespository.save(new ReceiptType("Nakit"));

		}
	}
}
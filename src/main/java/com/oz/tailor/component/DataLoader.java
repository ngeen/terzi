package com.oz.tailor.component;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.oz.tailor.model.DressType;
import com.oz.tailor.model.User;
import com.oz.tailor.repository.DressTypeRepository;
import com.oz.tailor.repository.UserRepository;

@Component
public class DataLoader implements ApplicationRunner {

    private UserRepository userRepository;
    private DressTypeRepository dressTypeRepository;

    @Autowired
    public DataLoader(UserRepository userRepository, DressTypeRepository dressTypeRepository) {
        this.userRepository = userRepository;
        this.dressTypeRepository = dressTypeRepository;
    }

    public void run(ApplicationArguments args) {
    	User user = userRepository.findByUserName("admin");
    	if(user == null) {
        	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            userRepository.save(new User("admin", bCryptPasswordEncoder.encode("admin"),"ADMIN"));	
    	}
    	
    	if(dressTypeRepository.count() <= 0) {
    		dressTypeRepository.save(new DressType("Ceket/Elbise"));
    		dressTypeRepository.save(new DressType("Yelek"));
    		dressTypeRepository.save(new DressType("Gömlek"));
    		dressTypeRepository.save(new DressType("Pantolon/Etek"));
    	}
    }
}
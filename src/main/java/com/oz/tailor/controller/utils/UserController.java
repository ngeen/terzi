package com.oz.tailor.controller.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oz.tailor.model.User;
import com.oz.tailor.repository.UserRepository;

@Controller
public class UserController {
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	UserController userController;
	
	public User getAuthUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByUserName(authentication.getName());
	}
	
	@GetMapping("/listUsers")
	public ResponseEntity<List<User>> listUsers(HttpServletRequest request, HttpServletResponse response){
		List<User> users = (List<User>) userRepository.findAll();
        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<User>>(users, HttpStatus.OK);
	}
	
	@GetMapping("/getUser/{id}")
	public ResponseEntity<User> getCustomers(@PathVariable("id") long id){
		User user =  userRepository.findById(id).get();
        return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@PostMapping("/saveUser")
    public ResponseEntity<?> createUser(@RequestBody User user) {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setRoles(new String[] {"USER"});
        userRepository.save(user);
 
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
	
    @PostMapping("/changePass")
    public String singleFileUpload(@RequestBody String pass,RedirectAttributes redirectAttributes) throws IOException {

        User user = userController.getAuthUser();
		
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		user.setPassword(bCryptPasswordEncoder.encode(pass));
		
		userRepository.save(user);
		
		redirectAttributes.addFlashAttribute("message",
		        "Profil Fotoğrafınız Başarıyla Güncellenmiştir");

        return "redirect:/uploadImage";
    }
 
    // ------------------- Delete a User-----------------------------------------
 
	@GetMapping("/deleteUser/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
 
        User user = userRepository.findById(id).get();
        if (user == null) {
            
        	return new ResponseEntity<String>("{\"result\":\"Kayıt Bulunamadı\"}", HttpStatus.NOT_FOUND);
        }
        
        if(Arrays.stream(user.getRoles()).anyMatch(x -> x.equals("ADMIN"))) {
        	return new ResponseEntity<String>("{\"result\":\"ADMIN kullanıcısı silinemez.\"}", HttpStatus.NOT_FOUND);
        }
        
        userRepository.deleteById(id);
        return new ResponseEntity<String>("{\"result\":\"success\"}", HttpStatus.CREATED);
    }
}

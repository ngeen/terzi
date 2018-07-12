package com.oz.tailor.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.oz.tailor.controller.utils.UserController;
import com.oz.tailor.model.User;
import com.oz.tailor.repository.UserRepository;

@Controller
public class UploadController {

    //Save the uploaded file to this folder
	@Value("${image.path}")
	private String imagePath;
    
	@Autowired
	UserController userController;
	
	@Autowired
	UserRepository userRepository;

    @GetMapping("/uploadImage")
    public String index() {
        return "upload";
    }

    @PostMapping("/upload") // //new annotation since 4.3
    public String singleFileUpload(@RequestParam("file") MultipartFile file,
                                   RedirectAttributes redirectAttributes) {

        if (file.isEmpty()) {
            redirectAttributes.addFlashAttribute("message", "Lütfen Gönderilecek Dosyayı Seçiniz");
            return "redirect:uploadImage";
        }

        try {
        	User user = userController.getAuthUser();
        	
            if(user.getImageName() != null)
            	try {
            		Files.delete(Paths.get(imagePath + user.getImageName()));	
				} catch (Exception e) {  }
            	
            
            // Get the file and save it somewhere
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imagePath + file.getOriginalFilename());
            Files.write(path, bytes);

            user.setImageName(file.getOriginalFilename());
            
            userRepository.save(user);
            
            redirectAttributes.addFlashAttribute("message",
                    "Profil Fotoğrafınız Başarıyla Güncellenmiştir");

        } catch (IOException e) {
        	redirectAttributes.addFlashAttribute("message",
                    "İşlemde Hata :" + e.getMessage());
        }

        return "redirect:/uploadImage";
    }

}
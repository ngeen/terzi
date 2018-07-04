package com.oz.tailor.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.oz.tailor.repository.UserRepository;

@Service
public class UserServiceImpl implements UserDetailsService {
	
	@Autowired
	private UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        com.oz.tailor.model.User user = userRepository.findByUserName(s);
 
        //check if this user with this username exist, if not, throw an exception
        // and stop the login process
        if (user == null) {
            throw new UsernameNotFoundException("User details not found with this username: " + s);
        }
 
        String username = user.getUserName();
        String password = user.getPassword();
        List<SimpleGrantedAuthority> authList = getAuthorities("user");
        if(Arrays.stream(user.getRoles()).anyMatch(x -> x.equals("ADMIN"))) {
        	authList = getAuthorities("admin");
        } 
 
        
 
        //get the encoded password
        //BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        //String encodedPassword = bCryptPasswordEncoder.encode(password);
 
        User loadUser = new User(username, password, authList);
 
        return loadUser;
    }
    
    private List<SimpleGrantedAuthority> getAuthorities(String role) {
        List<SimpleGrantedAuthority> authList = new ArrayList<>();
        authList.add(new SimpleGrantedAuthority("ROLE_USER"));
 
        //you can also add different roles here
        //for example, the user is also an admin of the site, then you can add ROLE_ADMIN
        //so that he can view pages that are ROLE_ADMIN specific
        if (role != null && role.trim().length() > 0) {
            if (role.equals("admin")) {
                authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
            }
        }
 
        return authList;
    }
    
	public com.oz.tailor.model.User getAuthUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return userRepository.findByUserName(authentication.getName());
	}

}

package com.oz.tailor.security;

import com.oz.tailor.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private UserServiceImpl userServiceImpl;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests().antMatchers("/dist/**", "/plugins/**").permitAll().antMatchers("/admin").hasRole("ADMIN")
				.anyRequest().authenticated().and().formLogin().loginPage("/login").permitAll().and().logout()
				.permitAll();
		http.exceptionHandling().accessDeniedPage("/403");
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userServiceImpl).passwordEncoder(passwordEncoder());
	}
}

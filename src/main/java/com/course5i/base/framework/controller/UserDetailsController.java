package com.course5i.base.framework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.course5i.base.framework.service.UserDetailsService;

@RestController
@RequestMapping(path = "/udetails")
public class UserDetailsController {

	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping(path = "/udetails", produces = "application/json")
	@PreAuthorize("hasRole('USER')")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity getUserDetails() {
		try {
			System.out.println("before findall");
			userDetailsService.findAll();
			System.out.println("after findall");
			
			
			 UserDetails user = User.builder()
					  .username("user")
					  .password("user")
					  .roles("USER")
					  .build();
					System.out.println("alok"+user.getPassword());

		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return new ResponseEntity(HttpStatus.OK) ;
	}
	
	@GetMapping(path = "/private", produces = "application/json")
	@ResponseStatus(HttpStatus.OK)
	public String getUserAdminDetails() {
		try {
			System.out.println("before findall");

		} catch(Exception e) {
			e.printStackTrace();
		}
		return "Hello Admin";

	}
	
}

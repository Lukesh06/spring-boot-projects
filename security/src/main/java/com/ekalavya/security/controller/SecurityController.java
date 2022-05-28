package com.ekalavya.security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("hello")
public class SecurityController {
	
	@GetMapping("user")
	@PreAuthorize("hasAuthority('user')")
	public String helloUser() {
		return "Hello User";
	}
	
	@GetMapping("admin")
	@PreAuthorize("hasAuthority('admin')")
	public String helloAdmin() {
		return "Hello Admin";
	}

}

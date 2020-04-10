package org.raccoon.com.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.java.Log;

@Log
@Controller
public class LoginController {
    
	@GetMapping("/login")
	public String login() {
        log.info("hello");
        return "login";
	}

	@GetMapping("/accessDenied")
	public void accessDenied() {

	}

	@GetMapping("/logout")
	public void logout() {

	}
}
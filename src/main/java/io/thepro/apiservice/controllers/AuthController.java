package io.thepro.apiservice.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import io.thepro.apiservice.security.SecurityService;
import io.thepro.apiservice.security.models.User;

@RestController
public class AuthController {

	@Autowired
	SecurityService securityService;

	@GetMapping("/me")
	public User login() {
		System.out.println("Request received ");
		return securityService.getUser();
	}

	@GetMapping("/protected/data")
	public Map<String, Object> protectedData() {
		Map<String, Object> response = new HashMap<>();
		response.put("message", "Hello " + securityService.getUser().getGivenName() + ", message from Rest Backend ");
		response.put("issuer", securityService.getUser().getIssuer());
		response.put("locale", securityService.getUser().getLocale());
		return response;
	}
}

package io.thepro.apiservice.security;

import java.io.IOException;
import java.security.GeneralSecurityException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.util.WebUtils;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken.Payload;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;

import io.thepro.apiservice.security.models.Credentials;
import io.thepro.apiservice.security.models.Credentials.CredentialType;
import io.thepro.apiservice.security.models.SecurityProperties;
import io.thepro.apiservice.security.models.User;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class SecurityService {

	@Autowired
	HttpServletRequest httpServletRequest;

	@Autowired
	SecurityProperties securityProps;

	@Autowired
	GoogleIdTokenVerifier googleIdTokenVerifier;

	public User getUser() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return (User) securityContext.getAuthentication().getPrincipal();
	}

	public Credentials getCredentials() {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		return (Credentials) securityContext.getAuthentication().getCredentials();
	}

	private String getBearerToken() {
		String bearerToken = null;
		String authorization = httpServletRequest.getHeader("Authorization");
		if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
			bearerToken = authorization.substring(7, authorization.length());
		}
		return bearerToken;
	}

	private User googleTokenToUserDto(GoogleIdToken decodedToken) {
		User user = null;
		if (decodedToken != null) {
			Payload payload = decodedToken.getPayload();
			user = new User();
			String email = payload.getEmail();
			user.setEmail(email);
			user.setEmailVerified(Boolean.valueOf(payload.getEmailVerified()));
			user.setName((String) payload.get("name"));
			user.setFamilyName((String) payload.get("family_name"));
			user.setGivenName((String) payload.get("given_name"));
			user.setPicture((String) payload.get("picture"));
			user.setIssuer(payload.getIssuer());
			user.setLocale((String) payload.get("locale"));
		}
		return user;
	}

	public void verifyToken() {
		GoogleIdToken decodedToken = null;
		CredentialType type = null;
		String idToken = null;
		String bearerToken = getBearerToken();
		Cookie tokenCookie = WebUtils.getCookie(httpServletRequest, "token");
		if (tokenCookie != null) {
			idToken = tokenCookie.getValue();
		} else if (bearerToken != null) {
			idToken = bearerToken;
		}
		try {
			if (idToken != null) {
				decodedToken = googleIdTokenVerifier.verify(idToken);
			}
		} catch (GeneralSecurityException | IOException e) {
			log.error(e.getLocalizedMessage(), e);
		}
		User user = googleTokenToUserDto(decodedToken);
		if (user != null) {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user,
					new Credentials(type, decodedToken), null);
			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
	}

}

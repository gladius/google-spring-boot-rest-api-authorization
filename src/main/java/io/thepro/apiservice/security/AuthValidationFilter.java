package io.thepro.apiservice.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.thepro.apiservice.security.models.SecurityProperties;

@Component
public class AuthValidationFilter extends OncePerRequestFilter {

	@Autowired
	SecurityService securityUtils;

	@Autowired
	SecurityProperties restSecProps;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		String path = request.getRequestURI();
		if (!restSecProps.getAllowedPublicApis().contains(path)) {
			securityUtils.verifyToken();
		}
		filterChain.doFilter(request, response);
	}

}

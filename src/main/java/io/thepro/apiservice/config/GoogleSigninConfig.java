package io.thepro.apiservice.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;

import io.thepro.apiservice.security.models.SecurityProperties;

@Configuration
public class GoogleSigninConfig {

	@Autowired
	SecurityProperties securityProps;

	@Autowired
	JacksonFactory jacksonFactory;

	@Autowired
	HttpTransport transport;

	@Bean
	GoogleIdTokenVerifier googleTokenVerifier() {
		return new GoogleIdTokenVerifier.Builder(transport, jacksonFactory)
				.setAudience(securityProps.getGoogleProps().getClientIds()).build();
	}

}

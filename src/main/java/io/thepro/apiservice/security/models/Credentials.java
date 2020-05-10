package io.thepro.apiservice.security.models;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Credentials {

	public enum CredentialType {
		ID_TOKEN, SESSION
	}

	private CredentialType type;
	 private GoogleIdToken decodedToken;

}

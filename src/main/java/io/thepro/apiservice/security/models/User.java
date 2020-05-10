package io.thepro.apiservice.security.models;

import lombok.Data;

@Data
public class User {

	private Long userId;
	private String name;
	private String familyName;
	private String givenName;
	private String email;
	private boolean isEmailVerified;
	private String issuer;
	private String picture;
	private String locale;

}

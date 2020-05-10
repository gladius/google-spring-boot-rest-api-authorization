package io.thepro.apiservice.exception;

import org.springframework.http.HttpStatus;

public class TheProException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    private HttpStatus code;

	public TheProException(HttpStatus code, String message) {
		super(message);
		this.code = code;
	}

	public HttpStatus getCode() {
		return code;
	}

	public void setCode(HttpStatus code) {
		this.code = code;
	}

}

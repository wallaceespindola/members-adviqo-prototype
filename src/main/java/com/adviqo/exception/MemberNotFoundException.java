package com.adviqo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MemberNotFoundException extends RuntimeException {

	/**
	 * Serial UID
	 */
	private static final long serialVersionUID = 1L;

	public MemberNotFoundException(Long id) {
		super("could not find member '" + id + "'.");
	}
}
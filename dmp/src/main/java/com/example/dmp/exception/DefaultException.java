package com.example.dmp.exception;

public class DefaultException extends RuntimeException {

	private static final long serialVersionUID = -8634700792767837033L;

	public int errorCode;

	public DefaultException(int errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
	}
	
	public DefaultException(String message) {
		super(message);
	}
}

package com.projeto.spring.api.exception;

public class EntidadeEmUsoException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	public EntidadeEmUsoException(String msg) {
		super(msg);
	}

}

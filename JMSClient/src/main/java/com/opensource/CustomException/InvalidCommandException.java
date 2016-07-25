package com.opensource.CustomException;

public class InvalidCommandException extends Exception{

	/**
	 * Invalid command
	 */
	private static final long serialVersionUID = 1L;
	public InvalidCommandException(){
		super();
	}
	
	public InvalidCommandException(String message){
		super(message);
	}
	
}

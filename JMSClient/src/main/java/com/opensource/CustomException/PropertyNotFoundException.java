package com.opensource.CustomException;

public class PropertyNotFoundException extends Exception{

	/**
	 * This exception should be thrown when the property is not found in the config file
	 */
	private static final long serialVersionUID = 1L;
	public PropertyNotFoundException() {
		super();
	}
	
	public PropertyNotFoundException(String message) {
		super(message);
	}

}

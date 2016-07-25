package com.opensource.commands;

import com.opensource.utilities.ResourceHandler;

public class Exit implements Command{

	public boolean execute(Context context, String input) {
		// Call the database
		System.out.println(">> Thank you for using our application. Happy chatting!");
		ResourceHandler resourceHandler = ResourceHandler.getResourceHandler();
		resourceHandler.close();
		// TO DO : log
		System.exit(0);
		return false;
	}

}

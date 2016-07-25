package com.opensource.commands;

public class Logout implements Command {

	public boolean execute(Context context, String input) {
		System.out.println(">> Logging out...");
		context.setUserId(null);
		context.setLoggedIn(false);
		// Update the database
		return true;
	}

}

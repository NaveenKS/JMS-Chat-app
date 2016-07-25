package com.opensource.commands;

public class Help implements Command{

	public boolean execute(Context context,String input) {
		System.out.println(">> To login : mp -login");
		System.out.println(">> To logout : mp -logout");
		System.out.println(">> To chat : mp -chat <receiver>");
		System.out.println(">> To exit : mp -exit ");
		return true;
	}
}

package com.opensource.commands;

import java.io.BufferedReader;
import java.io.IOException;

import com.opensource.JMSClient.JMSConsumer;
import com.opensource.utilities.ResourceHandler;

public class Login implements Command{

	public boolean execute(Context context,String input) {
		ResourceHandler resourceHandler = ResourceHandler.getResourceHandler();
		BufferedReader reader = resourceHandler.getReader();
		
		String username = null;
		String password = null;
		try {
			System.out.print(">>"+" Username : ");
			username = reader.readLine();
			System.out.print(">>"+" Password : ");
			password = reader.readLine();
			if(!username.isEmpty()&& !password.isEmpty()){
				System.out.println(">> Logging in...");
				context.setUserId(username);
				context.setLoggedIn(true);
				
				//contact the server for authentication
				
				System.out.println(">> Successfully loged in!!");
				//Start consumer thread
				JMSConsumer consumer = new JMSConsumer();
				consumer.startConsumer(username);
				// 
			}else{
				System.out.println("> Username or password is wrong. Please try again.");
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
		
	}
}

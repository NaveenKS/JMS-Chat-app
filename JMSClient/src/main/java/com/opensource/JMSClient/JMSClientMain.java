package com.opensource.JMSClient;

import java.io.BufferedReader;
import java.io.IOException;

import com.opensource.CustomException.InvalidCommandException;
import com.opensource.commandUtilities.CommandIdentifier;
import com.opensource.commandUtilities.CommandManager;
import com.opensource.commandUtilities.CommandNames;
import com.opensource.commands.Chat;
import com.opensource.commands.Command;
import com.opensource.commands.Context;
import com.opensource.commands.Exit;
import com.opensource.commands.Help;
import com.opensource.commands.Login;
import com.opensource.commands.Logout;
import com.opensource.utilities.ResourceHandler;


public class JMSClientMain{
	
	public static void main(String[] args){
		//Shutdown hook to update the database before closing
		Runtime.getRuntime().addShutdownHook(new Thread() {

		    @Override
		    public void run() {
		        // Release the resources
		    	if(!ResourceHandler.getResourceHandler().isClosed()){
		    		ResourceHandler.getResourceHandler().close();
		    	}
		    }
		});
		//Register all the commands
		CommandManager commandManager = registerAllTheCommands();
		
		//Set the context
		Context context = new Context();
		
		CommandIdentifier commandIdentifier = new CommandIdentifier();
		
		System.out.println("Welcome to command-line chat application. For help, type mp -help");
		ResourceHandler resourceHandler = ResourceHandler.getResourceHandler();
		BufferedReader reader = resourceHandler.getReader();
		
		while(true){
			String input = null;
			try {
				System.out.print(">> ");
				input = reader.readLine();
				Command command = commandIdentifier.identify(input,commandManager);
				command.execute(context,input);
			} catch (InvalidCommandException e) {
				// Add logs
				//e.printStackTrace();
				System.out.println(">> Invalid command!");
				commandManager.getCommand(CommandNames.HELP).execute(context, input);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static CommandManager registerAllTheCommands(){
		CommandManager commandManager = new CommandManager();
		commandManager.registerCommands(CommandNames.HELP, new Help());
		commandManager.registerCommands(CommandNames.LOGIN, new Login());
		commandManager.registerCommands(CommandNames.LOGOUT, new Logout());
		commandManager.registerCommands(CommandNames.CHAT, new Chat());
		commandManager.registerCommands(CommandNames.EXIT, new Exit());
		// TO DO : Add other commands
		return commandManager;
	}
}

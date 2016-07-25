package com.opensource.commandUtilities;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.opensource.CustomException.InvalidCommandException;
import com.opensource.commands.Command;

public class CommandIdentifier {
	public static final String regex = "mp.*?-([a-zA-Z]*)?\\s*(.*)";
	public static Pattern pattern = Pattern.compile(regex);
	public Command identify(String input, CommandManager commandManager) throws InvalidCommandException{
		Matcher matcher = pattern.matcher(input);
		Command command = null;
		if(matcher.find()){
			String commandName = matcher.group(1);
			if(commandName==null || !commandManager.hasCommand(commandName)){
				throw new InvalidCommandException();
			}else{
				command = commandManager.getCommand(commandName);
			}
		}else{
			throw new InvalidCommandException();
		}
		return command;
		
	}
}

package com.opensource.commandUtilities;

import java.util.HashMap;
import java.util.Map;

import com.opensource.commands.Command;

public class CommandManager {
	Map<String, Command> commandPool;
	public void registerCommands(String commandName,Command command){
		commandPool.put(commandName, command);
	}
	
	public CommandManager() {
		commandPool = new HashMap<String, Command>();
	}
	
	public void deRegisterCommands(String commandName){
		commandPool.remove(commandName);
	}
	
	public Map<String, Command> getCommandPool(){
		return commandPool;
	}
	
	public boolean hasCommand(String commandName){
		if(commandPool.containsKey(commandName)){
			return true;
		}else{
			return false;
		}
	}
	
	public Command getCommand(String commandName){
		return commandPool.get(commandName);
	}
}

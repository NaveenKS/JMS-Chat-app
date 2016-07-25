package com.opensource.utilities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class ResourceHandler {
	private static ResourceHandler resourceHandler;
	private BufferedReader bufferedReader;
	private boolean isClosed;
	private ResourceHandler(){
		// Singleton
	}
	
	public static ResourceHandler getResourceHandler(){
		if(resourceHandler==null){
			resourceHandler = new ResourceHandler();
			resourceHandler.setInputStream();
		}
		return resourceHandler;
	}
	
	public void setInputStream(){
		bufferedReader = new BufferedReader(new InputStreamReader(System.in));
	}
	
	public BufferedReader getReader(){
		return bufferedReader;
	}
	
	public boolean isClosed(){
		return isClosed;
	}
	public void close() {
		try {
			bufferedReader.close();
			isClosed = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

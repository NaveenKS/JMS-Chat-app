package com.opensource.utilities;

import java.io.IOException;
import java.util.Properties;

import com.opensource.CustomException.PropertyNotFoundException;

public class Config {
	private static Config config;
	private Properties configProperties;
	public static final String BROKER_URL= "BROKER_URL";
	public static final String CONFIG_FILE = "/config.properties";
	private Config(){
		// Cannot be created from outside
	}
	
	public static Config getConfig(String configPropertyFile) throws IOException{
		//singleton implementation
		if(config==null){
			config = new Config();
			config.setConfigProps(configPropertyFile);
		}
		return config;
	}
	
	private void setConfigProps(String configPropertyFile) throws IOException{
		Properties properties = new Properties();
		properties.load(getClass().getResourceAsStream(configPropertyFile));
		configProperties = properties;
	}
	
	public Properties getProperties(){
		return this.configProperties;
	}
	
	
	public String getProperty(String key) throws PropertyNotFoundException {
		if(configProperties.containsKey(key)){
			return (String) configProperties.get(key);
		}else {
			throw new PropertyNotFoundException("Property : "+key+" not found in the config file");
		}
	}
}

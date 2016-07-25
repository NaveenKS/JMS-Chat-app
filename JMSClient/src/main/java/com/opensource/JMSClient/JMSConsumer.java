package com.opensource.JMSClient;

import java.io.IOException;

import javax.jms.Connection;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.opensource.CustomException.PropertyNotFoundException;
import com.opensource.utilities.Config;

public class JMSConsumer implements MessageListener{
	public void startConsumer(String senderId){
		String brokerURL;
		try {
			Config config = Config.getConfig(Config.CONFIG_FILE);
			brokerURL = config.getProperty(Config.BROKER_URL);
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			Connection connection;
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(senderId);
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		} catch (PropertyNotFoundException e1) {
			// TO DO : Add logs
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onMessage(Message message) {
		try {
			String senderId = (String)message.getObjectProperty("senderId");
			String text = (String)message.getObjectProperty("text");
			System.out.println("\n"+">> "+senderId+" : "+text);
		} catch (JMSException e) {
			//TO DO : Add logs
			e.printStackTrace();
		}
		
		
	}
	
	
}

package com.opensource.commands;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

import com.opensource.CustomException.PropertyNotFoundException;
import com.opensource.commandUtilities.CommandIdentifier;
import com.opensource.utilities.Config;
import com.opensource.utilities.ResourceHandler;

public class Chat implements Command {

	public boolean execute(Context context, String input) {
		// TO DO : implement chat
		if(!context.isLoggedIn()){
			System.out.println(">> Please login to chat. To login : mp -login");
			return false;
		}
		
		String receiverId = getRevceiverId(input);
		
		if(receiverId==null || receiverId.isEmpty()){
			System.out.println(">> Invalid command! Usage : mp -chat <receiverId>");
			return false;
		}
		
		Connection connection = null;
		Session session = null;
		MessageProducer producer = null;
		
		try {
			Config config = Config.getConfig(Config.CONFIG_FILE);
			String brokerURL = config.getProperty(Config.BROKER_URL);
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(
					brokerURL);
			connection = connectionFactory.createConnection();
			connection.start();
			session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			
			Destination destination = session.createQueue(receiverId);
			producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			ResourceHandler resourceHandler = ResourceHandler.getResourceHandler();
			BufferedReader reader = resourceHandler.getReader();
			
			System.out
					.println(">> ----You can type exit to come out of the chat----");
			while (true) {
				try {
					System.out.print(">> " + context.getUserId() + "-"
							+ receiverId + " : ");
					input = reader.readLine();
					if (input.equals("exit")) {
						System.out.println(">> ----End-----");
						break;
					}

					Message message = session.createMessage();
					message.setObjectProperty("senderId", context.getUserId());
					message.setObjectProperty("receiverId", receiverId);
					message.setObjectProperty("text", input);
					producer.send(message);
				} catch (IOException e) {
					// TO DO : Add logs
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			// TO DO : Can apply retry strategy and fail after considerable
			// amount of retries
			e.printStackTrace();
		} catch (PropertyNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			try {
				producer.close();
				session.close();
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}

		}
		return true;
	}

	private String getRevceiverId(String input) {
		String receiverId = null;
		Matcher matcher = CommandIdentifier.pattern.matcher(input);
		if (matcher.find()) {
			receiverId = matcher.group(2);
		}
		return receiverId;
	}

}

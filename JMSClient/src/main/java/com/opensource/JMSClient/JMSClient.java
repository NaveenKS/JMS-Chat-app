package com.opensource.JMSClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.Session;

import org.apache.activemq.ActiveMQConnectionFactory;

public class JMSClient implements MessageListener{
	public static final String brokerURL  = "tcp://localhost:61616";
	public void producer(String senderId,String receiverId){
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
			Connection connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(receiverId);
			MessageProducer producer = session.createProducer(destination);
			producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			while(true){
				try {
					System.out.print(senderId+" : ");
					String input = reader.readLine();
					if(input.equals("exit")){
						System.out.println("Closing chat application");
						System.exit(0);
					}
					
					Message message = session.createMessage();
					message.setObjectProperty("senderId", senderId);
					message.setObjectProperty("receiverId", receiverId);
					message.setObjectProperty("text", input);
					producer.send(message);
				} catch (IOException e) {
					//TO DO : Add logs
					e.printStackTrace();
				}
			}
		} catch (JMSException e) {
			//TO DO : Can apply retry strategy and fail after considerable amount of retries
			e.printStackTrace();
		}
		
	
	}

	
	public void consumer(String senderId){
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(brokerURL);
		Connection connection;
		try {
			connection = connectionFactory.createConnection();
			connection.start();
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
			Destination destination = session.createQueue(senderId);
			MessageConsumer consumer = session.createConsumer(destination);
			consumer.setMessageListener(this);
		} catch (JMSException e) {
			e.printStackTrace();
		}
		
	}
	
	public void onMessage(Message message) {
		try {
			String senderId = (String)message.getObjectProperty("senderId");
			String text = (String)message.getObjectProperty("text");
			String receiver = (String)message.getObjectProperty("receiverId");
			System.out.println("\n"+senderId+" : "+text);
			System.out.print(receiver+" : ");
		} catch (JMSException e) {
			//TO DO : Add logs
			e.printStackTrace();
		}
		
		
	}
	
	public void start(String senderId,String receiverId){
		consumer(senderId);
		producer(senderId,receiverId);
	}
}

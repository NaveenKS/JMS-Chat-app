package com.opensource.JMSClient;


import javax.jms.Connection;
import javax.jms.DeliveryMode;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.Test;

public class TestJMS{
	@Test
	public void produceMessages() throws JMSException{
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin","admin","tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("TEST");
		MessageProducer messageProducer = session.createProducer(destination);
		messageProducer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
		String text = "This is a test message";
		TextMessage message = session.createTextMessage(text);
		messageProducer.send(message);
		messageProducer.close();
		session.close();
		connection.close();
	}
	
	@Test 
	public void consumeMessages() throws JMSException {
		ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616");
		Connection connection = connectionFactory.createConnection();
		connection.start();
		Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
		Destination destination = session.createQueue("TEST");
		MessageConsumer consumer = session.createConsumer(destination);
		Message message = consumer.receive(1000);
		
		if(message instanceof Message){
			TextMessage textMessage = (TextMessage) message;
			String text = textMessage.getText();
			System.out.println("Message received");
		}
		consumer.close();
		session.close();
		connection.close();
	}
}

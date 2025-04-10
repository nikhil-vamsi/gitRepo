package com.activeMQ.ActiveMQ;


import java.util.Enumeration;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSException;
import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ActiveMqApplication {

	public static void main(String[] args) throws NamingException, JMSException {
		
		InitialContext context = new InitialContext();
		ConnectionFactory cf = (ConnectionFactory) context.lookup("ConnectionFactory");
		Connection con = cf.createConnection();
		Session session = con.createSession();
		
		Queue queue = (Queue) context.lookup("myQueue");
		
		MessageProducer producer = session.createProducer(queue);
		
		TextMessage message = session.createTextMessage("first message");
		
		QueueBrowser browser = session.createBrowser(queue);
		Enumeration en =  browser.getEnumeration();
		while(en.hasMoreElements()) {
			
		}
		
		producer.send(message);
		producer.send(message);
		
		con.start();
		
		MessageConsumer consumer = session.createConsumer(queue);
		TextMessage messageRecieved = (TextMessage) consumer.receive(5000);
		System.out.println(messageRecieved.getText());
		
		
	}

}

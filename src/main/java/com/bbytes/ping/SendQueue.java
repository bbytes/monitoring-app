package com.bbytes.ping;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

@Component
public class SendQueue {
	
	 private final static String QUEUE_NAME = "hello";
        public boolean ping(String ip, String port) throws java.io.IOException,
        java.lang.InterruptedException{
        	
        	try{
        		ConnectionFactory factory = new ConnectionFactory();
            	factory.setHost(ip);
                Connection connection = factory.newConnection();
                Channel channel = connection.createChannel();
                channel.queueDeclare(QUEUE_NAME, false, false, false, null);
                String message = "Ping message";
                channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
                System.out.println(" [x] Sent '" + message + "'");
                channel.close();
                connection.close();
                return true;
        	}
        	catch(Exception e){
        		e.printStackTrace();
        		return false;
        	}
        }
}

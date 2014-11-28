package com.bbytes.ping;

import java.io.IOException;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class PingQueue {

	 private final static String QUEUE_NAME = "hello";
     public boolean ping(String ip, String port){
     	
    	Connection connection = null;
 		Channel channel = null;
     	try{
     		ConnectionFactory factory = new ConnectionFactory();
         	factory.setHost(ip);
             connection = factory.newConnection();
             channel = connection.createChannel();
             channel.queueDeclare(QUEUE_NAME, false, false, false, null);
             String message = "Ping";
             channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
             
             Thread.sleep(60);
             
             QueueingConsumer consumer = new QueueingConsumer(channel);
 			channel.basicConsume(QUEUE_NAME, true, consumer);
 			while (true) {
 				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
 				String reply = new String(delivery.getBody());
 				if (reply != null && reply.equals(message))
 					return true;
 				else
 					return false;
 			}
     	}
     	catch (IOException ie) {
			return false;
		} catch (Exception e) {
			return false;
		} finally {
			try {
				if (channel != null && channel.isOpen())
					channel.close();
				if (connection != null && connection.isOpen())
					connection.close();
			} catch (IOException e) {
				return false;
			}

		}

	}

}

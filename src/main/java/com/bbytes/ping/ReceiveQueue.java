package com.bbytes.ping;

import org.springframework.stereotype.Component;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.QueueingConsumer;

@Component
public class ReceiveQueue {

	private final static String QUEUE_NAME = "hello";

	public boolean ping(String ip, String port) throws Exception {
		try{
			ConnectionFactory factory = new ConnectionFactory();
			factory.setHost(ip);
			Connection connection = factory.newConnection();
			Channel channel = connection.createChannel();
			channel.queueDeclare(QUEUE_NAME, false, false, false, null);
			System.out.println(" [*] Waiting for messages. To exit press CTRL+C");
			QueueingConsumer consumer = new QueueingConsumer(channel);
			channel.basicConsume(QUEUE_NAME, true, consumer);
			while (true) {
				QueueingConsumer.Delivery delivery = consumer.nextDelivery();
				String message = new String(delivery.getBody());
				System.out.println(" [x] Received '" + message + "'");
				return true;
			}
		}
		catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
}
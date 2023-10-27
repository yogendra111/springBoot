package app.kafka.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import app.kafka.payloads.User;

@Service
public class JsonKafkaConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaConsumer.class);

	@KafkaListener(topics = "newTopic", groupId = "myGroup")
	public void consume(User user) {
		LOGGER.info(String.format("Json Message Received -> %s", user.toString()));
	}
	
}

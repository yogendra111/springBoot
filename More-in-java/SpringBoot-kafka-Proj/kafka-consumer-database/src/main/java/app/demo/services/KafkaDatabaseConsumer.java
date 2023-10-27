package app.demo.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaDatabaseConsumer {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(KafkaDatabaseConsumer.class);
	
	@KafkaListener(topics = "wikimedia_recent_changes", groupId = "myGroup")
	public void consume(String message) {
		LOGGER.info(String.format("Event Message Received -> %s", message));
	}
}

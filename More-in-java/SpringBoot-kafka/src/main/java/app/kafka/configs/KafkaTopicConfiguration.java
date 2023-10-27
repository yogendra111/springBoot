package app.kafka.configs;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfiguration {

	@Bean
	NewTopic createMyTopic() {
		return TopicBuilder.name("myTopic").build();
	}

	@Bean
	NewTopic createNewTopic() {
		return TopicBuilder.name("newTopic").build();
	}
	
}

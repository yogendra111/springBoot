package app.wikimedia.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {

	@Bean
	NewTopic topic() {
		return TopicBuilder.name("wikimedia_recent_changes").build();
	}
}

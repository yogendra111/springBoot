package app.kafka.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.kafka.payloads.User;
import app.kafka.services.JsonKafkaProducer;

@RestController
@RequestMapping("/api/v1/kafka")
public class JsonMessageController {
	
	@Autowired
	private JsonKafkaProducer kafkaProducer;
	
	@PostMapping("/publish")
	public ResponseEntity<String> publish(@RequestBody User user) {
		kafkaProducer.sendMessage(user);
		return ResponseEntity.ok("Json message sent to the kafka topic.");
	}
	
}

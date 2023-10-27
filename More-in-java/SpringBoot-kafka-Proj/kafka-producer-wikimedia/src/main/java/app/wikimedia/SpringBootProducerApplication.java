package app.wikimedia;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import app.wikimedia.services.WikimediaChangesProducer;

@SpringBootApplication
public class SpringBootProducerApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(SpringBootProducerApplication.class);
	}
	
	@Autowired
	WikimediaChangesProducer wikimediaChangesProducer;

	@Override
	public void run(String... args) throws Exception {
		wikimediaChangesProducer.sendMessage();
	}
	
}

package app.wikimedia.services;

import java.net.URI;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.launchdarkly.eventsource.EventSource;
import com.launchdarkly.eventsource.StreamException;
import com.launchdarkly.eventsource.background.BackgroundEventHandler;
import com.launchdarkly.eventsource.background.BackgroundEventSource;

import app.wikimedia.handlers.WikimediaChangesHandler;

@Service
public class WikimediaChangesProducer {

//	private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);
	
	@Autowired
	private KafkaTemplate<String, String> kafkaTemplate;
	
	public void sendMessage() throws InterruptedException, StreamException {
		String topic = "wikimedia_recent_changes";
		
		//to read the real time data from wikimedia, we use event source
		BackgroundEventHandler eventHandler = new WikimediaChangesHandler(kafkaTemplate, topic);
		String url = "https://stream.wikimedia.org/v2/stream/recentchange";
		EventSource.Builder eventBuilder = new EventSource.Builder(URI.create(url));
//		EventSource eventSource = builder.build();
//		eventSource.start();
		BackgroundEventSource.Builder builder = new BackgroundEventSource.Builder(eventHandler, eventBuilder);
		BackgroundEventSource eventSource = builder.build();
		eventSource.start();
		
		//to stop the thread for 2 second to consume all message between that time.
//		TimeUnit.SECONDS.sleep(2);
		
	}
	
}

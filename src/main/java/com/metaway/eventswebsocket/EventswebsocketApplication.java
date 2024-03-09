package com.metaway.eventswebsocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;

@SpringBootApplication
@EnableMongoRepositories
@EnableWebSocketMessageBroker
public class EventswebsocketApplication {

	public static void main(String[] args) {
		SpringApplication.run(EventswebsocketApplication.class, args);
	}

}

package com.metaway.eventswebsocket.repositories;

import com.metaway.eventswebsocket.entities.ComponentEvent;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ComponentEventRepository extends MongoRepository<ComponentEvent, String> {
}

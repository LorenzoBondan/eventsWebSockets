package com.metaway.eventswebsocket.events;

import com.metaway.eventswebsocket.entities.ComponentEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class CreateComponentEventPublisher {

    private final ApplicationEventPublisher eventPublisher;

    public CreateComponentEventPublisher(ApplicationEventPublisher eventPublisher){
        this.eventPublisher = eventPublisher;
    }

    @Async
    public void publishEvent(ComponentEvent componentEvent){
        CreateComponentEventEvent event = new CreateComponentEventEvent(this, componentEvent);
        eventPublisher.publishEvent(event);
    }
}

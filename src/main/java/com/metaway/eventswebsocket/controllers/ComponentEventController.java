package com.metaway.eventswebsocket.controllers;

import com.metaway.eventswebsocket.entities.ComponentEvent;
import com.metaway.eventswebsocket.events.CreateComponentEventPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;

@Controller
public class ComponentEventController {

    @Autowired
    private CreateComponentEventPublisher publisher;

    @MessageMapping("/componentInteracted")
    @SendTo("/topic/public")
    public ComponentEvent componentClicked(@Payload ComponentEvent componentEvent){
        ComponentEvent component = new ComponentEvent(
                componentEvent.getUsername(),
                LocalDateTime.now().minusHours(3), // MongoDB stores times in UTC by default, and converts any local time representations into this form.
                componentEvent.getType(),
                componentEvent.getMessage()
        );
        publisher.publishEvent(component); // evento de salvar assíncrono
        return component;
    }

    /* JS
    // exemplo: clicou no botão

    function click(event){
        var socket = new SockJS('/ws');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, onClickButton, onError);
        event.preventDefault();
    }

    function onClickButton(){
        stompClient.subscribe('/topic/public');
        stompClient.send("/app/componentInteracted", {}, JSON.stringify({username: 'INSERIR USUÁRIO AUTENTICADO', type: 'clicar', message: `${username} clicou no botão`})); // payload do controller
    }

    function onError(error) {
        connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
        connectingElement.style.color = 'red';
    }

    messageButton.addEventListener('click', click, true)

    */
}

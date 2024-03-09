package com.metaway.eventswebsocket.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.user.SimpUserRegistry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

@RestController
public class MonitoringController {

    @Autowired
    private WebSocketMessageBrokerStats webSocketMessageBrokerStats;

    @Autowired
    private SimpUserRegistry userRegistry;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @GetMapping("/session-stats")
    public ResponseEntity<String> getSessionStats(){
        return ResponseEntity.ok().body("Session stats: " + webSocketMessageBrokerStats.getWebSocketSessionStatsInfo());
    }

    @GetMapping("/client-inbound")
    public ResponseEntity<String> getClientInboundStats(){
        return ResponseEntity.ok().body("Client Inbound stats: " + webSocketMessageBrokerStats.getClientInboundExecutorStatsInfo());
    }

    @GetMapping("/client-outbound")
    public ResponseEntity<String> getClientOutboundStats(){
        return ResponseEntity.ok().body("Client Outbound stats: " + webSocketMessageBrokerStats.getClientOutboundExecutorStatsInfo());
    }

    @GetMapping("/sockjs")
    public ResponseEntity<String> getSockJsTaskShedulerStats(){
        return ResponseEntity.ok().body("SockJS Task Scheduler stats: " + webSocketMessageBrokerStats.getSockJsTaskSchedulerStatsInfo());
    }

    @GetMapping(value = "/active-connections")
    public ResponseEntity<String> getActiveConnections(){
        int activeConnections = userRegistry.getUserCount();
        messagingTemplate.convertAndSend("/topic/active-connections", "Número de conexões ativas: " + activeConnections);
        return ResponseEntity.ok().body("Número de conexões ativas: " + activeConnections);
    }
}

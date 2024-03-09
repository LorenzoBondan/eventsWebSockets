package com.metaway.eventswebsocket.actuator;

import org.springframework.boot.actuate.audit.AuditEventRepository;
import org.springframework.boot.actuate.audit.InMemoryAuditEventRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuditEventConfig {

    @Bean
    public AuditEventRepository auditEventRepository(){
        return new InMemoryAuditEventRepository();
    }
}
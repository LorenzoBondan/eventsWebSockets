package com.metaway.eventswebsocket.config;

import com.metaway.eventswebsocket.config.filter.ApiKeyAuthenticationFilter;
import com.metaway.eventswebsocket.config.provider.ApiKeyAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.web.socket.config.WebSocketMessageBrokerStats;

import java.util.Collections;

@Configuration
public class SecurityConfig {

    @Autowired
    private ApiKeyAuthenticationProvider apiKeyAuthenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .addFilterBefore(new ApiKeyAuthenticationFilter(authenticationManager()), AnonymousAuthenticationFilter.class);

        http.sessionManagement(session ->
                session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                        .maximumSessions(2147483647)
                        .sessionRegistry(sessionRegistry())
        ); // actuator

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager() {
        return new ProviderManager(Collections.singletonList(apiKeyAuthenticationProvider));
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }
}

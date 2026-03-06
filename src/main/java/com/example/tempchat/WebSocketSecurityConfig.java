package com.example.tempchat;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.Message;
import org.springframework.security.authorization.AuthorizationManager;
import org.springframework.security.config.annotation.web.socket.EnableWebSocketSecurity;
import org.springframework.security.messaging.access.intercept.MessageMatcherDelegatingAuthorizationManager;

@Configuration
@EnableWebSocketSecurity
public class WebSocketSecurityConfig {

    @Bean
    AuthorizationManager<Message<?>> messageAuthorizationManager(){
        return MessageMatcherDelegatingAuthorizationManager.builder()
                .simpDestMatchers("app/chat").authenticated()
                .simpSubscribeDestMatchers("topic/**").authenticated()
                .anyMessage().denyAll()
                .build();
    }
}

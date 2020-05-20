package com.websocket.echoclient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;

@Configuration
public class WebSocketConfig {

    @Bean
    public WebSocketStompClient webSocketStompClient() {
        WebSocketClient client = new StandardWebSocketClient();
        return new WebSocketStompClient(client);
    }

}

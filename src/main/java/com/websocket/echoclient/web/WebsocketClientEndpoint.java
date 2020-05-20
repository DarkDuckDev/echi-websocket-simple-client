package com.websocket.echoclient.web;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import javax.websocket.ClientEndpoint;
import javax.websocket.ContainerProvider;
import javax.websocket.DeploymentException;
import javax.websocket.MessageHandler;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.WebSocketContainer;
import java.io.IOException;
import java.net.URI;
import java.util.concurrent.atomic.AtomicInteger;

@ClientEndpoint
@Slf4j
public class WebsocketClientEndpoint {

    private Session session;

    @Setter
    private MessageHandler messageHandler;

    public WebsocketClientEndpoint(String url) {
        WebSocketContainer container = ContainerProvider.getWebSocketContainer();
        try {
            container.connectToServer(this, URI.create(url));
        } catch (DeploymentException | IOException e) {
            log.error("", e);
        }
    }

    @OnOpen
    public void onOpen(Session userSession) {
        log.info("Opened: " + userSession.getId());
        this.session = userSession;
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("Message received: " + message);
    }

    public void sendMessage(String message) {
        log.info("Sending message");
        this.session.getAsyncRemote().sendText(message);
    }


}

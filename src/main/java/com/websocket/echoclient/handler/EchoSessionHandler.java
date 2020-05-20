package com.websocket.echoclient.handler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandler;

import java.lang.reflect.Type;

@RequiredArgsConstructor
@Slf4j
public class EchoSessionHandler implements StompSessionHandler {

    private final String URL;

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        session.subscribe(URL, this);
        log.info("Session id: " + session.getSessionId() + " subscribed to " + URL);
    }

    @Override
    public void handleException(StompSession session, StompCommand command, StompHeaders headers, byte[] payload, Throwable exception) {
        log.error("Handle exception, sessionId: " + session.getSessionId());
    }

    @Override
    public void handleTransportError(StompSession session, Throwable exception) {
        log.error("Transport exception, sessionId: " + session.getSessionId());
    }

    @Override
    public Type getPayloadType(StompHeaders headers) {
        return String.class;
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("Received: " + payload);
    }
}

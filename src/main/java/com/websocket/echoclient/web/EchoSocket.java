package com.websocket.echoclient.web;

import com.websocket.echoclient.handler.EchoSessionHandler;
import com.websocket.echoclient.handler.EchoWebSocketHandler;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.RestTemplateXhrTransport;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;

import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping("ws")
@RequiredArgsConstructor
@Slf4j
public class EchoSocket {

    private StompSession session;
    private final WebSocketStompClient client;
    private static final String URL = "wss://echo.websocket.org/";


    @GetMapping("connect")
    public void connect() throws ExecutionException, InterruptedException {

//        StandardWebSocketClient standardWebSocketClient = new StandardWebSocketClient();
        //Init
        List<Transport> transports = new ArrayList<>(2);
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
//        transports.add(new RestTemplateXhrTransport());
        SockJsClient sockJsClient = new SockJsClient(transports);
        ListenableFuture<WebSocketSession> webSocketSessionListenableFuture = sockJsClient.doHandshake(new EchoWebSocketHandler(), URL);
        Thread.sleep(2000);
        webSocketSessionListenableFuture.get();
//        WebSocketStompClient webSocketStompClient = new WebSocketStompClient(sockJsClient);
//
//        StompSession stompSession = client.connect(URL, new EchoSessionHandler(URL)).get();
//        session = stompSession;

        int i = 0;
    }

//    @Scheduled(fixedDelay = 5_000)
    public void schedule() {
        if(session != null) {
//            session.send("/echo", "Test");
            log.info("Sended");
        }
    }

}

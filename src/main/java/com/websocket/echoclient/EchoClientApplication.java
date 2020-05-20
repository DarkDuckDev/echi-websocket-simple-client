package com.websocket.echoclient;

import com.websocket.echoclient.web.WebsocketClientEndpoint;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//@EnableWebSocket
//@EnableScheduling
@SpringBootApplication
public class EchoClientApplication implements CommandLineRunner {

	private static final String URL = "wss://echo.websocket.org";

	public static void main(String[] args) {
		SpringApplication.run(EchoClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		WebsocketClientEndpoint client = new WebsocketClientEndpoint(URL);
		client.sendMessage("Echo test");
		Thread.sleep(5_000);
	}
}

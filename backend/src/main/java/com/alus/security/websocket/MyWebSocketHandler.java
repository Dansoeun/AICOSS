package com.alus.security.websocket;


import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

public class MyWebSocketHandler extends TextWebSocketHandler {

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        String payload = message.getPayload();
        System.out.println("Received message: " + payload);

        // 여기서 AI 응답 등을 처리하여 클라이언트로 전송합니다.
        String response = "Server response: " + payload;
        session.sendMessage(new TextMessage(response));
    }
}
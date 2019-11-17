package com.example.demo.socket;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public class SendSocket {

    @GetMapping("/sendAllWebSocket")
    public String test(String number) {
        WebSocket webSocket = new WebSocket();
        webSocket.sendAllMessage(number);
        return number;
    }
    @GetMapping("/sendOneWebSocket/{userName}")

    public String sendOneWebSocket(@PathVariable("userName") String userName,String carNumber) {
        String text=carNumber;
        WebSocket webSocket = new WebSocket();
        webSocket.sendOneMessage(userName,text);
        return text;
    }
}

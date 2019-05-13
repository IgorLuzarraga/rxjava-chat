package com.example.springboot.vaadin.rxjavachat;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;
import io.reactivex.subjects.ReplaySubject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDateTime;

@Configuration
public class AppConfiguration {
    private final String CHAT_MSG_1 = "Welcome to the Chat!";
    private final String USER_ADMIN = "Admin";

    @Bean
    ReplaySubject<ChatMessage> observable() {
        ReplaySubject<ChatMessage> obs = ReplaySubject.create();

        // send the welcome msg
        obs.onNext(new ChatMessage(LocalDateTime.now(), USER_ADMIN, CHAT_MSG_1));

        return obs;
    }
}
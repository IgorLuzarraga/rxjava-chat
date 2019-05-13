package com.example.springboot.vaadin.rxjavachat.services;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;
import io.reactivex.subjects.ReplaySubject;
import io.reactivex.functions.Consumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChatServiceImpl implements ChatService {

    private final ReplaySubject<ChatMessage> obs;

    @Autowired
    public ChatServiceImpl(ReplaySubject<ChatMessage> obs){
        this.obs = obs;
    }

    @Override
    public void onNext(ChatMessage chatMessage){
        obs.onNext(chatMessage);
    }

    @Override
    public void subscribe(Consumer<? super ChatMessage> onNext) {
        obs.subscribe(onNext);
    }
}
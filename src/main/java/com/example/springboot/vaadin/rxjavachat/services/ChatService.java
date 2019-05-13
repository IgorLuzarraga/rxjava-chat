package com.example.springboot.vaadin.rxjavachat.services;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;

import io.reactivex.functions.Consumer;

public interface ChatService {
    public void onNext(ChatMessage chatMessage);
    public void subscribe(Consumer<? super ChatMessage> onNext);
}
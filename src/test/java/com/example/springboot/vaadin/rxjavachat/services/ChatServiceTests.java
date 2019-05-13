package com.example.springboot.vaadin.rxjavachat.services;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;
import io.reactivex.subjects.ReplaySubject;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ChatServiceTests {
    private final String CHAT_MSG_SEND = "Welcome to the Chat!";
    private final String USER_ADMIN = "Admin";

    private ChatService chatService;
    private List<ChatMessage> chatMessageList;
    private ReplaySubject<ChatMessage> obs;

    @Before
    public void setup(){
        obs = ReplaySubject.create();
        chatService = new ChatServiceImpl(obs);
        chatMessageList = new ArrayList<>();
    }

    @Test
    public void givenChatMsg_whenChatMsgSend_ThenTheReceiveChatMsgIsTheSame(){

        // given a chat message
        ChatMessage chatMessageSend = new ChatMessage(LocalDateTime.now(), USER_ADMIN, CHAT_MSG_SEND);

        // when the chat message is send
        chatService.onNext(chatMessageSend);

        // then the receive Chat message is the same one
        chatService.subscribe(chatMessageList::add);
        ChatMessage chatMessageRecive = chatMessageList.get(0);

        assertEquals(chatMessageSend, chatMessageRecive);
    }

}

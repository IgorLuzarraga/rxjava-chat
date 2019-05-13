package com.example.springboot.vaadin.rxjavachat.frontend;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;
import com.example.springboot.vaadin.rxjavachat.services.ChatService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class BuildNewsLayoutTests {

    private final String CHAT_MSG = "Hello World!";
    private final String USER_ADMIN = "Admin";

    @Mock
    ChatService chatService;

    private BuildChatLayout buildChatLayout;

    @Before
    public void setup() {
        buildChatLayout = new BuildChatLayout(chatService);

        buildChatLayout.startChat(USER_ADMIN);
    }

    @Test
    public void givenChatMsg_whenButtonSendClicked_ThenCallServiceOnNextToSendTheNews() {

        // given a chat message
        Optional<String> sendChatMsg = Optional.of(CHAT_MSG);

        //when send the chat msg
        buildChatLayout.sendChatMessageTest(sendChatMsg);

        // then call service.onNext to send the chat msg
        Optional<ChatMessage> chatMessageSend = buildChatLayout.receiveChatMessageTest();
        then(chatService).should().onNext(chatMessageSend.get());
    }

    @Test
    public void givenBuildNewsStarted_ThenCallServiceSubscribeToReceiveTheNews() {

        // then call service.subscribe to  receive the Chat msg
        then(chatService).should().subscribe(Mockito.any());
    }
}

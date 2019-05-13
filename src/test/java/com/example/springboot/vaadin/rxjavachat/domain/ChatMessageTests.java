package com.example.springboot.vaadin.rxjavachat.domain;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.Assert.assertEquals;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RunWith(SpringRunner.class)
public class ChatMessageTests {

    private final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final static String CHAT_MESSAGE = "Hello World!";
    private final String USER_ADMIN = "Admin";
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_PATTERN);
    private LocalDateTime localDateTime;
    private ChatMessage chatMessage;
    private String formatDateTime;
    private String expectedChatMsg;

    @Before
    public void setup(){
        localDateTime = LocalDateTime.now();
        formatDateTime = localDateTime.format(formatter);
        expectedChatMsg = formatDateTime + " "
                            + USER_ADMIN + " "
                            + CHAT_MESSAGE;

        chatMessage = new ChatMessage(localDateTime, USER_ADMIN, CHAT_MESSAGE);
    }

    @Test
    public void CheckTheChatMsgIsCorrect(){
        assertEquals(expectedChatMsg, chatMessage.getChatMessage());
    }

    @Test
    public void CheckTheMsgIsCorrect(){
        assertEquals(CHAT_MESSAGE, chatMessage.getMessage());
    }

    @Test
    public void CheckTheDateTimeIsCorrect(){
        assertEquals(formatDateTime, chatMessage.getDateTime());
    }
}

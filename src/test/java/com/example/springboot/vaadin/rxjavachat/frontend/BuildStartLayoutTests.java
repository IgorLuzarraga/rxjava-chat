package com.example.springboot.vaadin.rxjavachat.frontend;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.BDDMockito.then;

@RunWith(MockitoJUnitRunner.class)
public class BuildStartLayoutTests {

    @Mock
    private BuildChatLayout buildChatLayout;

    @InjectMocks
    private BuildStartLayout buildStartLayout;

    @Test
    public void whenButtonStartClickedThenCallStartChat(){
        //when
        buildStartLayout.eventClickStartButton();

        //then the startNews function from buildChatLayout should be called
        // to start the Chat
        then(buildChatLayout).should().startChat(Mockito.any());
    }
}

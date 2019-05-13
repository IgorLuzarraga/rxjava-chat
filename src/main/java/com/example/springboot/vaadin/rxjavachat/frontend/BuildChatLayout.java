package com.example.springboot.vaadin.rxjavachat.frontend;

import com.example.springboot.vaadin.rxjavachat.domain.ChatMessage;
import com.example.springboot.vaadin.rxjavachat.services.ChatService;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.provider.ListDataProvider;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import io.reactivex.functions.Consumer;


@SpringComponent
@UIScope
public class BuildChatLayout extends VerticalLayout implements IBuildChatLayout{
    private final String TEXT_SEND_BUTTON = "Send";
    private final String TEXT_MESSAGE_FIELD = "Write the message you want to send";
    private final String GRID_COLUMN_ONE = "dateTime";
    private final String GRID_COLUMN_TWO = "user";
    private final String GRID_COLUMN_THREE = "message";
    private VerticalLayout chatLayout;
    private HorizontalLayout fieldButtonLayout;
    private Grid<ChatMessage> grid;
    private TextField messageField;
    private Button sendButton;
    private ChatService chatService;
    private Optional<ChatMessage> chatMessageToSend;
    private String userName;

    @Autowired
    public BuildChatLayout(ChatService chatService){
        this.chatService = chatService;
    }

    @Override
    public void startChat(String userName) {
        this.userName = userName;

        buildLayout();
        addListeners();

        // Receive the Chat msgs
        setChatMsgsReceiver();
    }

    public Optional<ChatMessage> receiveChatMessageTest(){
        return chatMessageToSend;
    }

    public void sendChatMessageTest(Optional<String> chatMessage){
        chatMessage.ifPresent(chatMsg -> {
            messageField.setValue(chatMsg);
            sendButton.click();
        });
    }

    private void setChatMsgsReceiver(){
        chatService.subscribe(SetChatMsgsToTheGrid());
    }

    private Consumer<? super ChatMessage> SetChatMsgsToTheGrid(){
        return chatMessage ->
                getUI().ifPresent(ui ->
                        ui.access(() -> grid.setItems(setChatInList(chatMessage)))
                );
    }

    private Consumer<? super ChatMessage> SetChatMsgsToTheGrid2(){
        return chatMessage -> grid.setItems(setChatInList(chatMessage));
    }

    // Send the News msgs
    private void sendNewsMsg(){
        createChatMessage()
                .filter(chatMsg -> !chatMsg.getMessage().equals(""))
                .ifPresent(chatMsg ->  chatService.onNext(chatMsg));
        messageField.clear();
        messageField.focus();
    }

    private List<ChatMessage> getChatInGrid() {
        ListDataProvider<ChatMessage> ldp = (ListDataProvider) grid.getDataProvider();
        return new ArrayList<>(ldp.getItems());
    }

    private List<ChatMessage> setChatInList(ChatMessage msg){
        List<ChatMessage> chatList = getChatInGrid();
        chatList.add(msg);
        return chatList;
    }

    private void buildLayout() {
        chatLayout = new VerticalLayout();
        fieldButtonLayout = new HorizontalLayout();
        grid = new Grid<>(ChatMessage.class);
        messageField = new TextField();
        sendButton = new Button(TEXT_SEND_BUTTON);

        messageField.setPlaceholder(TEXT_MESSAGE_FIELD);

        grid.setHeight("300px");
        grid.setColumns(GRID_COLUMN_ONE, GRID_COLUMN_TWO, GRID_COLUMN_THREE);
        grid.getColumnByKey(GRID_COLUMN_ONE).setWidth("25%").setFlexGrow(0);
        grid.getColumnByKey(GRID_COLUMN_TWO).setWidth("25%").setFlexGrow(0);

        sendButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        fieldButtonLayout.add(messageField, sendButton);
        fieldButtonLayout.setWidth("100%");
        fieldButtonLayout.expand(messageField);

        messageField.focus();

        chatLayout.add(fieldButtonLayout, grid);

        add(chatLayout);
    }

    private void addListeners(){
        messageField.addKeyPressListener(Key.ENTER,
                click -> sendNewsMsg());

        sendButton.addClickListener(click -> sendNewsMsg());
    }

    private Optional<ChatMessage> createChatMessage(){
        chatMessageToSend = Optional.ofNullable(new ChatMessage(LocalDateTime.now(), userName, messageField.getValue()));
        return chatMessageToSend;
    }
}








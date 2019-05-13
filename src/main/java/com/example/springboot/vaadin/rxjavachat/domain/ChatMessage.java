package com.example.springboot.vaadin.rxjavachat.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class ChatMessage {
    private String dateTime;
    private String user;
    private String message;
    private LocalDateTime localDateTime;

    public ChatMessage(LocalDateTime localDateTime, String user, String message) {
        this.localDateTime = localDateTime;
        this.user = user;
        this.message = message;

        formatDateTime();
    }

    public String getChatMessage(){
        return (getDateTime() + " "
                + getUser() + " "
                + getMessage());
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setUser(String user){ this.user = user; }

    public String getUser(){
        return user;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    private void formatDateTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        dateTime = localDateTime.format(formatter);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChatMessage that = (ChatMessage) o;
        return Objects.equals(dateTime, that.dateTime) &&
                Objects.equals(user, that.user) &&
                Objects.equals(message, that.message) &&
                Objects.equals(localDateTime, that.localDateTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, user, message, localDateTime);
    }

    @Override
    public String toString() {
        return "ChatMessage{" +
                "dateTime='" + dateTime + '\'' +
                ", user='" + user + '\'' +
                ", message='" + message + '\'' +
                ", localDateTime=" + localDateTime +
                '}';
    }
}

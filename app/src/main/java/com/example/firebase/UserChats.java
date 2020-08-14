package com.example.firebase;

public class UserChats {
    private String Message_Chats;
    private String Sender;
    private String receiver;

    public String getMessage_Chats() {
        return Message_Chats;
    }

    public void setMessage_Chats(String message_Chats) {
        Message_Chats = message_Chats;
    }

    public String getSender() {
        return Sender;
    }

    public void setSender(String sender) {
        Sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}

package com.example.firebase;

import android.os.Message;

public class MessageItem {
    String Message_Chat,Message_Sender,Message_Receiver ;
    public MessageItem(String Message_Chat,String Message_Sender,String Message_Receiver){
        this.Message_Chat = Message_Chat;
        this.Message_Sender = Message_Sender;
        this.Message_Receiver = Message_Receiver;
    }

    public String getMessage_Chat() {
        return Message_Chat;
    }

    public void setMessage_Chat(String message_Chat) {
        Message_Chat = message_Chat;
    }

    public String getMessage_Sender() {
        return Message_Sender;
    }

    public void setMessage_Sender(String message_Sender) {
        Message_Sender = message_Sender;
    }

    public String getMessage_Receiver() {
        return Message_Receiver;
    }

    public void setMessage_Receiver(String message_Receiver) {
        Message_Receiver = message_Receiver;
    }
}

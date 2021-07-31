package com.example.demo.tools;

public class Message {
    private String message;
    private String link;

    public Message(String message, String link) {
        this.message = message;
        this.link = link;
    }

    public Message() {
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

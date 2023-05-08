package com.example.paygoal.dtos;

public class MessageDto {
    private String text;

    public MessageDto(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

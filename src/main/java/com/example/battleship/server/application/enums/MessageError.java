package com.example.battleship.server.application.enums;

public enum MessageError {
    INVALID_INT_TYPE(100, "An integer value is expected"),
    INVALID_INT_RANGE(101, "The {0} must be between {1} and {2}."),
    DUPLICATE_DATA(102, "The {0} already does exist."),
    USER_NOT_ABLE(103, "User not able to play");
    private int code;
    private String messageTemplate;

    MessageError(int code, String messageTemplate){
        this.code = code;
        this.messageTemplate = messageTemplate;
    }

    public int getCode(){
        return this.code;
    }

    public String getMessageTemplate(){
        return this.messageTemplate;
    }

}

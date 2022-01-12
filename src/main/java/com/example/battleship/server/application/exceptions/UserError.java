package com.example.battleship.server.application.exceptions;

import com.example.battleship.server.application.enums.MessageError;

import java.text.MessageFormat;

public class UserError extends VerifyError{
    int errorCode;
    String messageTemplate;
    String message;

    public UserError(MessageError messageError, Object... params){
        this.errorCode = messageError.getCode();
        this.messageTemplate = messageError.getMessageTemplate();
        this.message = MessageFormat.format(this.messageTemplate, params);
    }

    public String getMessage(){
        return this.message;
    }
}

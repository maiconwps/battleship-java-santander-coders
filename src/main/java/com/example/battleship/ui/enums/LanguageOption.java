package com.example.battleship.ui.enums;

public enum LanguageOption {
    PT(1),
    EN(2);

    private int option;

    private LanguageOption(int option){
        this.option = option;
    }

    public int getOption(){
        return option;
    }
}

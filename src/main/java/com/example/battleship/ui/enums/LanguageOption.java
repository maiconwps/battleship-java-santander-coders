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

    public static LanguageOption valueOf(int option){
        for(LanguageOption lp: LanguageOption.values()){
            if(lp.getOption() == option){
                return lp;
            }
        }
        return null;
    }
}

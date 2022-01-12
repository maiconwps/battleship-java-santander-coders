package com.example.battleship.server.domain.enums;

public enum ShipStatus {
    HIDDEN(1),
    SUNK(0);

    private int value;

    private ShipStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}

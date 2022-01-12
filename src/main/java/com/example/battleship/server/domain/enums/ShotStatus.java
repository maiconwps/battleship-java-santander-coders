package com.example.battleship.server.domain.enums;

public enum ShotStatus {
    HIT(1),
    MISS(0);

    private int value;

    private ShotStatus(int value){
        this.value = value;
    }

    public int getValue(){
        return this.value;
    }
}
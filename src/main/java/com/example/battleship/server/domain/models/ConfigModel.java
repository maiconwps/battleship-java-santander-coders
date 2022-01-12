package com.example.battleship.server.domain.models;

import com.example.battleship.server.domain.enums.GameType;

public class ConfigModel {
    private int sizeBoard;
    private int numberOfShips;
    private GameType type;

    public ConfigModel(int sizeBoard, int numberOfShips, GameType type){
        this.sizeBoard = sizeBoard;
        this.type = type;
        this.numberOfShips = numberOfShips;
    }

    public int getSizeBoard() {
        return sizeBoard;
    }

    public int getNumberOfShips() {
        return numberOfShips;
    }

    public GameType getType() {
        return type;
    }
}

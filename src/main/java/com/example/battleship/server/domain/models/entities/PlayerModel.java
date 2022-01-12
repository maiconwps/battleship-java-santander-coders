package com.example.battleship.server.domain.models.entities;

import com.example.battleship.server.domain.enums.PlayerType;


public class PlayerModel {
    private int id;
    private String name;
    private PlayerType type;

    public PlayerModel(int id, String name, PlayerType type){
        this.id = id;
        this.name = name;
        this.type = type;
    }

    public int getId(){
        return this.id;
    }

    public String getName(){
        return this.name;
    }

    public PlayerType getType(){return this.type;}
}

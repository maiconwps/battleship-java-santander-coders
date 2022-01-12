package com.example.battleship.server.domain.models.entities;

import java.util.ArrayList;

public class BoardModel {
    private int id;
    private int size;
    private int battleCommandId;
    private ArrayList<FleetShipModel> fleet;

    public BoardModel(int id, int size, int battleCommandId){
        this.id = id;
        this.size = size;
        this.fleet = new ArrayList<>();
        this.battleCommandId = battleCommandId;
    }

    public int getId(){
        return this.id;
    }

    public int getSize(){
        return this.size;
    }

    public int getBattleCommandId() {
        return battleCommandId;
    }

    public ArrayList<FleetShipModel> getFleet(){
        return this.fleet;
    }
}

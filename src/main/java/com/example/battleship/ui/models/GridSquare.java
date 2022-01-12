package com.example.battleship.ui.models;

import com.example.battleship.server.domain.enums.ShipStatus;
import com.example.battleship.server.domain.enums.ShotStatus;

public class GridSquare {
    private ShipStatus shipStatus;
    private ShotStatus shotStatus;
    private String[][] marks = {
            {"", "-", "*"},
            {"N", "n", "X"}};


    public void setShipStatus(ShipStatus shipStatus) {
        this.shipStatus = shipStatus;
    }

    public void setShotStatus(ShotStatus shotStatus) {
        this.shotStatus = shotStatus;
    }

    public String getMark(){
        int x = this.shipStatus == null? 0: 1;
        int y = this.shotStatus == null? 0: this.shotStatus.getValue() + 1;

        return this.marks[x][y];
    }
}

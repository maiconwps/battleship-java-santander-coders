package com.example.battleship.server.domain.models.entities;

import com.example.battleship.server.domain.enums.ShipStatus;
import com.example.battleship.server.domain.enums.ShipType;
import com.example.battleship.server.domain.models.value_objects.SquareModel;

public class FleetShipModel {
    private int id;
    private SquareModel origin;
    private ShipType shipType;
    private ShipStatus status;

    public FleetShipModel(int id, SquareModel origin, ShipType shipType){
        this.id = id;
        this.origin = origin;
        this.shipType = shipType;
        this.status = ShipStatus.HIDDEN;
    }

    public int getId(){
        return this.id;
    }

    public SquareModel getOrigin(){
        return this.origin;
    }

    public ShipStatus getStatus(){
        return this.status;
    }
}

package com.example.battleship.server.domain.models.entities;

import com.example.battleship.server.domain.enums.ShotStatus;
import com.example.battleship.server.domain.models.value_objects.SquareModel;

public class ShotModel {
    private int id;
    private int battleId;
    private SquareModel coordinates;
    private ShotStatus status;

    public ShotModel(int id, int battleId, SquareModel coordinates, ShotStatus status) {
        this.id = id;
        this.battleId = battleId;
        this.coordinates = coordinates;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public int getBattleId() {
        return battleId;
    }

    public SquareModel getCoordinates() {
        return coordinates;
    }

    public ShotStatus getStatus() {
        return status;
    }
}

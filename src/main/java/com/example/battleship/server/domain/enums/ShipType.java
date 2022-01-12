package com.example.battleship.server.domain.enums;

public enum ShipType {
    SUBMARINE(1),
    DESTROYER(2),
    CRUISER(3),
    BATTLESHIP(4),
    CARRIER(5);

    private int length;

    private ShipType(int length){
        this.length = length;
    }

    public int getLength() {
        return length;
    }
}

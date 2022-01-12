package com.example.battleship.server.domain.models.value_objects;

import com.example.battleship.server.domain.enums.TurnPlayerType;

public class TurnModel {
    private int round;
    private TurnPlayerType currentPlayer;

    public TurnModel() {
        this.round = 1;
        this.currentPlayer = TurnPlayerType.PRIMARY;
    }

    public int getRound() {
        return round;
    }

    public TurnPlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public void initNewRound(){
        switch (this.currentPlayer){
            case PRIMARY:
                this.currentPlayer = TurnPlayerType.SECONDARY;
                break;
            case SECONDARY:
                this.round += 1;
                this.currentPlayer = TurnPlayerType.PRIMARY;
        }
    }
}

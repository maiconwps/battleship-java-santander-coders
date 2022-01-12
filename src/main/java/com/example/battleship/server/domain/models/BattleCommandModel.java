package com.example.battleship.server.domain.models;

import com.example.battleship.server.domain.enums.TurnPlayerType;
import com.example.battleship.server.domain.enums.WinStatus;

public class BattleCommandModel {
    private int id;
    private int gameId;
    private int playerId;
    private TurnPlayerType turnPlayer;
    private WinStatus winStatus;

    public BattleCommandModel(int id, int playerId, int gameId, TurnPlayerType turnPlayer){
        this.id = id;
        this.playerId = playerId;
        this.gameId = gameId;
        this.turnPlayer = turnPlayer;
    }

    public int getId() {
        return id;
    }

    public int getGameId() {
        return gameId;
    }

    public int getPlayerId() {
        return playerId;
    }

    public WinStatus getVictoryStatus() {
        return winStatus;
    }

    public TurnPlayerType getTurnPlayer() {
        return turnPlayer;
    }

    public void setVictoryStatus(WinStatus winStatus) {
        this.winStatus = winStatus;
    }
}

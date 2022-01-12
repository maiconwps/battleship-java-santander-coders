package com.example.battleship.server.domain.models;

public class GameModel {
    private int id;
    private ConfigModel config;
    private TurnModel currentTurn;

    public GameModel(int id, ConfigModel config) {
        this.id = id;
        this.config = config;
        this.currentTurn = new TurnModel();
    }

    public int getId() {
        return id;
    }

    public ConfigModel getConfig() {
        return config;
    }

    public TurnModel getCurrentTurn() {
        return currentTurn;
    }

    public void nextRound() {
        this.currentTurn.initNewRound();
    }

}

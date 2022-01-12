package com.example.battleship.ui;

import com.example.battleship.server.domain.models.entities.PlayerModel;
import com.example.battleship.ui.config.GameConfig;
import com.example.battleship.ui.controlers.*;
import com.example.battleship.ui.workflows.SingleGameWorkflow;


public class BattleshipClient {
    public void playSinglePlayerGame(){
        PlayerModel player = PlayerController.crateNewRealPlayer(GameConfig.defaultNamePlayer);
        new SingleGameWorkflow(player).play();
    }
}

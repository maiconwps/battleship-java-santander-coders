package com.example.battleship.ui.controlers;

import com.example.battleship.server.application.services.PlayerApplicationService;
import com.example.battleship.server.domain.enums.PlayerType;
import com.example.battleship.server.domain.models.PlayerModel;

public class PlayerController {
    public static PlayerModel crateNewRealPlayer(String name){
        return PlayerApplicationService.createNewPlayer(name, PlayerType.REAL);
    }

    public static PlayerModel crateNewVirtualPlayer(String name){
        return PlayerApplicationService.createNewPlayer(name, PlayerType.VIRTUAL);
    }
}

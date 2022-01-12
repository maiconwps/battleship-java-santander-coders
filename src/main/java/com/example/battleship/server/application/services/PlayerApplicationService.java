package com.example.battleship.server.application.services;

import com.example.battleship.server.domain.enums.PlayerType;
import com.example.battleship.server.domain.models.entities.PlayerModel;
import com.example.battleship.server.domain.repository.PlayerRepository;

public class PlayerApplicationService {
    private static PlayerRepository playerRepository;

    public static PlayerModel getPlayer(int playerId){
        return playerRepository.getPlayer(playerId);
    }

    public static void initService(PlayerRepository playerRepository){
        PlayerApplicationService.playerRepository = playerRepository;
    }

    public static PlayerModel createNewPlayer(String name, PlayerType type){
        int playerId = playerRepository.create(name, type);
        return playerRepository.getPlayer(playerId);
    }
}

package com.example.battleship.server.domain.repository;

import com.example.battleship.server.domain.enums.PlayerType;
import com.example.battleship.server.domain.models.entities.PlayerModel;

public interface PlayerRepository {
    int create(String name, PlayerType type);
    PlayerModel getPlayer(int playerId);
}

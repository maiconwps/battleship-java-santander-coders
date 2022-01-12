package com.example.battleship.server.infra.repository;

import com.example.battleship.server.domain.enums.PlayerType;
import com.example.battleship.server.domain.models.PlayerModel;
import com.example.battleship.server.domain.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.Optional;

public class PlayerRepositoryMemory implements PlayerRepository {
    private ArrayList<PlayerModel> players;

    public PlayerRepositoryMemory( ArrayList<PlayerModel> players){
        this.players = players;
    }

    @Override
    public PlayerModel getPlayer(int playerId) {
        Optional<PlayerModel> player = this.players.
                stream().
                filter(p -> p.getId() == playerId).
                findFirst();
        if(player.isPresent()){
            return player.get();
        }else{
            throw new NullPointerException("Player doesn't exist");
        }
    }

    @Override
    public int create(String name, PlayerType type) {
        int playerId = this.players.size() + 1;
        PlayerModel player = new PlayerModel(playerId, name, type);
        this.players.add(player);
        return playerId;
    }
}

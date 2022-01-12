package com.example.battleship.server;

import com.example.battleship.server.application.services.GameApplicationService;
import com.example.battleship.server.application.services.BoardApplicationService;
import com.example.battleship.server.application.services.PlayerApplicationService;
import com.example.battleship.server.domain.models.entities.*;
import com.example.battleship.server.infra.repository.BoardRepositoryMemory;
import com.example.battleship.server.infra.repository.GameRepositoryMemory;
import com.example.battleship.server.infra.repository.PlayerRepositoryMemory;

import java.util.ArrayList;

public class BattleshipServer {
    private static ArrayList<PlayerModel> players;
    private static ArrayList<GameModel> games;
    private static ArrayList<BattleCommandModel> battleCommands;
    private static ArrayList<BoardModel> boards;
    private static ArrayList<ShotModel> shots;

    public static void initServer(){
       initDataBases();
       initServices();
    }

    private static void initDataBases(){
        players = new ArrayList<>();
        games = new ArrayList<>();
        battleCommands = new ArrayList<>();
        boards = new ArrayList<>();
        shots = new ArrayList<>();
    }

    private static void initServices(){
        PlayerApplicationService.initService(
                new PlayerRepositoryMemory(players)
            );
        GameApplicationService.initService(
                new GameRepositoryMemory(games, battleCommands, boards, shots)
            );
        BoardApplicationService.initService(
                new BoardRepositoryMemory(boards)
            );
    }
}
